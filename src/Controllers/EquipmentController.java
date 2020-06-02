package Controllers;

import TechnicalInspection.Equipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Locale;

public class EquipmentController {
    public TextField equipmentName;
    public TextField equipmentNameEdit;
    public ChoiceBox<String> spinnerAvailableEq;
    public ChoiceBox<String> spinnerAvailableEqEdit;
    private Equipment equipment;
    public Button cancelBtn;

    public EquipmentController() {
    }

    public EquipmentController(Equipment equipment) {
        this.equipment = equipment;
    }

    @FXML
    public void initialize() {
        ArrayList<String> onBosnian = new ArrayList<>();
        ArrayList<String> onEnglish = new ArrayList<>();
        if (LoginController.languageChoosen()) {
            Locale.setDefault(new Locale("bs", "BA"));
            onBosnian.add("DOSTUPAN");
            onBosnian.add("NEDOSTUPAN");
            ObservableList<String> list = FXCollections.observableArrayList(onBosnian);
            spinnerAvailableEq.setItems(list);
            spinnerAvailableEqEdit.setItems(list);
        }
        else {
            Locale.setDefault(Locale.ENGLISH);
            onEnglish.add("AVAILABLE");
            onEnglish.add("UNAVAILABLE");
            ObservableList<String> list = FXCollections.observableArrayList(onEnglish);
            spinnerAvailableEq.setItems(list);
            spinnerAvailableEqEdit.setItems(list);
        }
    }

    public void confirmAddingEquipment (ActionEvent actionEvent) {
        boolean sveOk = true;
        if (equipmentName.getText().trim().isEmpty()) {
            equipmentName.getStyleClass().removeAll("inputOK");
            equipmentName.getStyleClass().add("inputNOTOK");
            sveOk = false;
        } else {
            equipmentName.getStyleClass().removeAll("inputNOTOK");
            equipmentName.getStyleClass().add("inputOK");
        }
        if (!sveOk) return;

        String s = spinnerAvailableEq.getSelectionModel().getSelectedItem();
        boolean available = false;
        if (s.equals("DOSTUPAN") || s.equals("AVAILABLE")) {
            available = true;
        }
        equipment = new Equipment(equipmentName.getText(), available);
        Stage stage = (Stage) equipmentName.getScene().getWindow();
        stage.close();
    }

    public void cancelAddingEquipment (ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void confirmEditEquipment (ActionEvent actionEvent) {

    }

    public void cancelEditEquipment (ActionEvent actionEvent) {

    }

    public Equipment getEquipment() {
        return equipment;
    }
}
