package TechnicalInspection;

import Exceptions.NoInternetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginController {
    public Button btnSubmit;
    public Button btnCancel;

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
}
