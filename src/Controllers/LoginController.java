package Controllers;

import DatabaseWork.InspectionDAO;
import Exceptions.NoInternetException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    public Button btnSubmit;
    public Button btnCancel;
    public ImageView flagBiH;
    public ImageView flagUK;
    public GridPane loginGrid;
    public TextField userName;
    public PasswordField password;
    private InspectionDAO dao = null;
    private static boolean bosnianPicked = false;
    private static boolean choosenLanguage = false;

    @FXML
    public void initialize() {
        dao = InspectionDAO.getInstance();
        Image flagOfBiH = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Flag_of_Bosnia_and_Herzegovina.svg/300px-Flag_of_Bosnia_and_Herzegovina.svg.png");
        flagBiH.setImage(flagOfBiH);
        Image flagOfUK = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/1280px-Flag_of_the_United_Kingdom_%283-5%29.svg.png");
        flagUK.setImage(flagOfUK);
        btnSubmit.setDefaultButton(true);
        flagBiH.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            bosnianPicked = true;
            choosenLanguage = true;
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
                bosnianPicked = false;
                choosenLanguage = true;
                loadScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        userName.textProperty().addListener(((observableValue, s, t1) -> {
            if (t1.length() >= 4) {
                userName.getStyleClass().removeAll("inputNOTOK");
                userName.getStyleClass().add("inputOK");
            } else {
                userName.getStyleClass().removeAll("inputOK");
                userName.getStyleClass().add("inputNOTOK");
            }
        }));
        password.textProperty().addListener(((observableValue, s, t1) -> {
            if (t1.length() >= 3) {
                password.getStyleClass().removeAll("inputNOTOK");
                password.getStyleClass().add("inputOK");
            } else {
                password.getStyleClass().removeAll("inputOK");
                password.getStyleClass().add("inputNOTOK");
            }
        }));
    }

    public void openMainWindow(ActionEvent actionEvent) {
        if(NoInternetException.haveInternetConnectivity()) {
            if (userName.getText().isEmpty()) {
                userName.getStyleClass().removeAll("inputOK");
                userName.getStyleClass().add("inputNOTOK");
                if (!password.getText().isEmpty()) {
                    password.getStyleClass().removeAll("inputNOTOK");
                    password.getStyleClass().add("inputOK");
                }
            } else if (password.getText().isEmpty()) {
                userName.getStyleClass().removeAll("inputNOTOK");
                userName.getStyleClass().add("inputOK");
                password.getStyleClass().removeAll("inputOK");
                password.getStyleClass().add("inputNOTOK");
            } else {
                Thread thread = new Thread(() -> {
                    if (!dao.loginCheck(userName.getText(), password.getText())) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setAlertType(Alert.AlertType.WARNING);
                                if (bosnianPicked) {
                                    a.setTitle("Upozorenje");
                                    a.setHeaderText("Provjerite Vaše podatke!");
                                    a.setContentText("Unešeni podaci su neispravni ili nemate privilegije pristupa sistemu!");
                                } else {
                                    a.setTitle("Warning");
                                    a.setHeaderText("Check your inputs!");
                                    a.setContentText("Input data are not correct or you have no privileges to enter.");
                                }
                                a.show();
                            }
                        });
                        userName.getStyleClass().removeAll("inputOK");
                        userName.getStyleClass().add("inputNOTOK");
                        password.getStyleClass().removeAll("inputOK");
                        password.getStyleClass().add("inputNOTOK");
                    } else {
                        userName.getStyleClass().removeAll("inputNOTOK");
                        userName.getStyleClass().add("inputOK");
                        password.getStyleClass().removeAll("inputNOTOK");
                        password.getStyleClass().add("inputOK");
                        Platform.runLater(() -> {
                            closeLoginWindow(actionEvent);
                            try {
                                if(!choosenLanguage) Locale.setDefault(new Locale("bs","BA"));
                                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"), bundle);
                                Parent root = fxmlLoader.load();
                                Stage newStage = new Stage();
                                newStage.setTitle(ResourceBundle.getBundle("Translation").getString("caldealership"));
                                newStage.setScene(new Scene(root));
                                newStage.setResizable(false);
                                newStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                });
                thread.start();
            }
        } else {
            NoInternetException.showAlert();
        }
    }

    public static boolean languageChoosen() {
        if (choosenLanguage && bosnianPicked) return true;
        return false;
    }

    public static void setLanguage (String language) {
        if (language.equals("Bosanski")) {
            choosenLanguage = true;
            bosnianPicked = true;
        } else {
            bosnianPicked = false;
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
