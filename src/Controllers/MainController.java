package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainController {
    public TabPane tabPane;
    public ChoiceBox<String> choiceBoxLanguage;

    @FXML
    public void initialize() {
        choiceBoxLanguage.getSelectionModel().select(Locale.getDefault().getLanguage());
        choiceBoxLanguage.getItems().add("bs");
        choiceBoxLanguage.getItems().add("en");

        choiceBoxLanguage.getSelectionModel().selectedItemProperty().addListener((observableValue, oldLanguage, newLanguage) -> {
            if(newLanguage.equals("bs")) {
                Locale.setDefault(new Locale("bs", "BA"));
                try {
                    loadScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Locale.setDefault(new Locale("en", "US"));
                try {
                    loadScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void loadScene() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Stage stage = (Stage) tabPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/glavni.fxml" ), bundle);
        //loader.setController(this);
        stage.setScene(new Scene(loader.load()));
    }

}
