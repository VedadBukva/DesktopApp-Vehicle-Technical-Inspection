package Controllers;

import TechnicalInspection.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Enum.*;

public class UserController {
    private User user;
    public TextField firstName;
    public TextField lastName;
    public ChoiceBox<String> position;
    public TextField jmbg;
    public DatePicker birthDate;
    public TextField address;
    public TextField mail;
    public PasswordField password;
    public PasswordField repeatPassword;
    public TextField postalNumber;
    public TextField phoneNumber;
    public Button cancelAdding;
    public TextField userName;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_NUMBER =
            Pattern.compile("(^060[0-9]{7})|(^06[1-3]{1}[0-9]{6})", Pattern.CASE_INSENSITIVE);

    public UserController() {
        user = null;
    }

    @FXML
    public void initialize() {
        ArrayList<String> positionsEng = new ArrayList<>();
        ArrayList<String> positionsBos = new ArrayList<>();
        positionsEng.add("ADMIN");
        positionsEng.add("EMPLOYEE");
        positionsBos.add("RADNIK");
        positionsBos.add("ADMINISTRATOR");
        ObservableList<String> posBos = FXCollections.observableArrayList(positionsBos);
        ObservableList<String> posEng = FXCollections.observableArrayList(positionsEng);
        if (LoginController.languageChoosen())  {
            position.setItems(posBos);
            position.getSelectionModel().selectLast();
        }
        else {
            position.setItems(posEng);
            position.getSelectionModel().selectLast();
        }

    }

    public void addUser(ActionEvent actionEvent) {
        if (firstName.getText().trim().isEmpty()) {
            firstName.getStyleClass().removeAll("inputOK");
            firstName.getStyleClass().add("inputNOTOK");
            return;
        } else {
            firstName.getStyleClass().removeAll("inputNOTOK");
            firstName.getStyleClass().add("inputOK");
        }
        if (lastName.getText().trim().isEmpty()) {
            lastName.getStyleClass().removeAll("inputOK");
            lastName.getStyleClass().add("inputNOTOK");
            return;
        } else {
            lastName.getStyleClass().removeAll("inputNOTOK");
            lastName.getStyleClass().add("inputOK");
        }
        if (jmbg.getText().trim().isEmpty() || jmbg.getText().trim().length() != 13) {
            jmbg.getStyleClass().removeAll("inputOK");
            jmbg.getStyleClass().add("inputNOTOK");
            return;
        } else {
            jmbg.getStyleClass().removeAll("inputNOTOK");
            jmbg.getStyleClass().add("inputOK");
        }
        LocalDate date = birthDate.getValue();
        if (date == null || date.isAfter(LocalDate.now()) || date.isAfter(LocalDate.now().minusYears(18))) {
            birthDate.getStyleClass().removeAll("inputOK");
            birthDate.getStyleClass().add("inputNOTOK");
            return;
        } else {
            birthDate.getStyleClass().removeAll("inputNOTOK");
            birthDate.getStyleClass().add("inputOK");
        }

        if (!validateEmail(mail.getText().trim())) {
            mail.getStyleClass().removeAll("inputOK");
            mail.getStyleClass().add("inputNOTOK");
            return;
        } else {
            mail.getStyleClass().removeAll("inputNOTOK");
            mail.getStyleClass().add("inputOK");
        }
        if (userName.getText().trim().isEmpty()) {
            userName.getStyleClass().removeAll("inputOK");
            userName.getStyleClass().add("inputNOTOK");
            return;
        } else {
            userName.getStyleClass().removeAll("inputNOTOK");
            userName.getStyleClass().add("inputOK");
        }
        if (address.getText().trim().length() < 5 || address.getText().trim().isEmpty()) {
            address.getStyleClass().removeAll("inputOK");
            address.getStyleClass().add("inputNOTOK");
            return;
        } else {
            address.getStyleClass().removeAll("inputNOTOK");
            address.getStyleClass().add("inputOK");
        }
        if (phoneNumber.getText().trim().isEmpty() || !validatePhoneNumber(phoneNumber.getText())) {
            phoneNumber.getStyleClass().removeAll("inputOK");
            phoneNumber.getStyleClass().add("inputNOTOK");
            return;
        } else {
            phoneNumber.getStyleClass().removeAll("inputNOTOK");
            phoneNumber.getStyleClass().add("inputOK");
        }
        if (password.getText().trim().isEmpty() || !validatePassword(password.getText().trim())) {
            password.getStyleClass().removeAll("inputOK");
            password.getStyleClass().add("inputNOTOK");
            return;
        } else {
            password.getStyleClass().removeAll("inputNOTOK");
            password.getStyleClass().add("inputOK");
        }
        if (!repeatPassword.getText().trim().equals(password.getText().trim())) {
            repeatPassword.getStyleClass().removeAll("inputOK");
            repeatPassword.getStyleClass().add("inputNOTOK");
            return;
        } else {
            repeatPassword.getStyleClass().removeAll("inputNOTOK");
            repeatPassword.getStyleClass().add("inputOK");
        }
        checkPostalNumber(date);
    }

    public void cancelAddingUser(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelAdding.getScene().getWindow();
        stage.close();
    }

    private void checkPostalNumber(LocalDate date) {
        String postalCheck = "http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=" + postalNumber.getText();

        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(postalCheck);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                String check = "", line = null;
                while ((line = in.readLine()) != null)
                    check = check + line;
                in.close();
                if(check.equals("NOT OK")) {
                    postalNumber.getStyleClass().removeAll("inputOK");
                    postalNumber.getStyleClass().add("inputNOTOK");
                    return;
                } else {
                    postalNumber.getStyleClass().removeAll("inputNOTOK");
                    postalNumber.getStyleClass().add("inputOK");

                    Platform.runLater(() -> {
                        if (user == null) user = new User();
                        user.setName(firstName.getText());
                        user.setSurname(lastName.getText());
                        user.setAddress(address.getText());
                        user.setBirthDate(date);
                        user.setJmbg(jmbg.getText());
                        user.setRole(RoleType.getRoleType(position.getSelectionModel().getSelectedItem()));
                        user.setPassword(password.getText());
                        user.setMail(mail.getText());
                        user.setPostalNumber(postalNumber.getText());
                        user.setPhoneNumber(phoneNumber.getText());
                        user.setUserName(userName.getText());

                        Stage stage = (Stage) phoneNumber.getScene().getWindow();
                        stage.close();
                    });
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    private static boolean validatePassword (String password) {
        Matcher matcher = VALID_PASSWORD.matcher(password);
        return matcher.matches();
    }

    private boolean validatePhoneNumber(String text) {
        Matcher matcher = VALID_PHONE_NUMBER.matcher(text);
        return matcher.matches();
    }

    public User getUser() {
        return user;
    }
}
