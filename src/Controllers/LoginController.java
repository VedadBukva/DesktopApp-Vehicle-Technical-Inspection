package Controllers;

import Exceptions.NoInternetException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    public Button btnSubmit;
    public Button btnCancel;
    public ImageView flagBiH;
    public ImageView flagUK;
    public GridPane loginGrid;

    @FXML
    public void initialize() {
        Image flagOfBiH = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Flag_of_Bosnia_and_Herzegovina.svg/300px-Flag_of_Bosnia_and_Herzegovina.svg.png");
        flagBiH.setImage(flagOfBiH);
        Image flagOfUK = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/1280px-Flag_of_the_United_Kingdom_%283-5%29.svg.png");
        flagUK.setImage(flagOfUK);

        if(isEmpty(flagBiH)) {
            System.out.println("PRAZNOOOOO");
        } else {
            System.out.println("TUU JEEEEE");
        }
        flagBiH.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Locale.setDefault(new Locale("bs", "BA"));
            try {
                loadScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        flagUK.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Locale.setDefault(new Locale("en", "US"));
            try {
                loadScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean isEmpty(ImageView imageView) {
        Image image = imageView.getImage();
        return image == null || image.isError();
    }

    public void openMainWindow(ActionEvent actionEvent) {
        if(NoInternetException.haveInternetConnectivity()) {
            closeLoginWindow(actionEvent);
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/glavni.fxml"), bundle);
                Parent root = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setTitle("Auto kuća Ada");
                newStage.setScene(new Scene(root));
                newStage.setResizable(false);
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            NoInternetException.showAlert();
        }
    }

    public void closeLoginWindow(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void loadScene() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Stage stage = (Stage) loginGrid.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/login.fxml" ), bundle);
        stage.setScene(new Scene(loader.load()));
    }
}
