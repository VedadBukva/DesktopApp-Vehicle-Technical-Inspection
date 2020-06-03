package Controllers;

import DatabaseWork.InspectionDAO;
import Reports.PrintReports;
import TechnicalInspection.TechnicalInspection;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.*;

import TechnicalInspection.*;
import Enum.*;

public class MainController {
    // Inspections done
    public TableView<TechnicalInspection> completedInspections;
    public TableColumn<TechnicalInspection, String> vehicleOwnerCol;
    public TableColumn<TechnicalInspection, String> vehicleCol;
    public TableColumn vehicleTypeCol;
    public TableColumn<TechnicalInspection, String> responsiblePersonCol;
    public ImageView langBiHflag;
    public ImageView langUKflag;
    private ObservableList<TechnicalInspection> listOfTechnicalInspections;
    // ----------------
    // Inspections in archive
    public TableView<TechnicalInspection> tableInspectionsInArchive;
    public TableColumn<TechnicalInspection, String> colOwner;
    public TableColumn<TechnicalInspection, String> colVehicle;
    public TableColumn<TechnicalInspection, String> colType;
    public TableColumn colInspectionType;
    public TableColumn<TechnicalInspection, String> colPerson;
    private ObservableList<TechnicalInspection> listOfArchivedTechnicalInspections;
    // ---------------------
    // Equipment
    public TableView<Equipment> equipmentTable;
    public TableColumn colEquipmentName;
    public TableColumn<Equipment, String> colEquipmentAvailable;
    private ObservableList<Equipment> listOfEquipment;
    // ---------------------
    // Users
    private ObservableList<User> listOfUsers;
    public TableView<User> tableOfUsers;
    public TableColumn<User, String> colUserNameSurname;
    public TableColumn colUsername;
    public TableColumn colUserMail;
    public TableColumn colUserRole;
    // ---------------------
    public TabPane tabPane;
    public Button btnAddUser;
    public Button btnDeleteUser;
    public Button archiveAccountButton;
    public Tab archiveAccounts;
    private InspectionDAO dao = null;

    public MainController() {
        dao = InspectionDAO.getInstance();
        listOfTechnicalInspections = FXCollections.observableArrayList(dao.inspectionsDone());
        listOfArchivedTechnicalInspections = FXCollections.observableArrayList(dao.inspectionsInArchive());
        listOfEquipment = FXCollections.observableArrayList(dao.equipment());
        if (InspectionDAO.checkIfLoggedUserIsAdmin()) listOfUsers = FXCollections.observableArrayList(dao.getUsersForAdmin());
        else listOfUsers = FXCollections.observableArrayList(dao.getUsersForMenager());
    }

    @FXML
    public void initialize() {
        initializeInspectionsTable();
        initializeInspectionsInArchiveTable();
        initializeEquipmentTable();
        initializeUserTable();
        if (LoginController.languageChoosen()) Locale.setDefault(new Locale("bs", "BA"));
        else Locale.setDefault(Locale.ENGLISH);
        if (InspectionDAO.checkIfLoggedUserIsAdmin()) {
            archiveAccounts.setDisable(true);
            archiveAccountButton.setDisable(true);
        } else {
            btnAddUser.setDisable(true);
            btnDeleteUser.setDisable(true);
        }
        langBiHflag.minHeight(50);
        langBiHflag.minWidth(200);
        langUKflag.minHeight(50);
        langUKflag.minWidth(200);
        Image flagOfBiH = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Flag_of_Bosnia_and_Herzegovina.svg/300px-Flag_of_Bosnia_and_Herzegovina.svg.png");
        langBiHflag.setImage(flagOfBiH);
        Image flagOfUK = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/1280px-Flag_of_the_United_Kingdom_%283-5%29.svg.png");
        langUKflag.setImage(flagOfUK);
        langBiHflag.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Locale.setDefault(new Locale("bs", "BA"));
            try {
                loadScene();
                LoginController.setLanguage("Bosanski");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        langUKflag.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Locale.setDefault(new Locale("en", "US"));
            try {
                loadScene();
                LoginController.setLanguage("Engleski");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeUserTable() {
        tableOfUsers.setItems(listOfUsers);
        colUserMail.setCellValueFactory(new PropertyValueFactory("mail"));
        colUsername.setCellValueFactory(new PropertyValueFactory("userName"));
        colUserNameSurname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName() + " " + data.getValue().getSurname()));
        colUserRole.setCellValueFactory(new PropertyValueFactory("role"));
    }

    private void initializeEquipmentTable() {
        equipmentTable.setItems(listOfEquipment);
        colEquipmentName.setCellValueFactory(new PropertyValueFactory("name"));
        colEquipmentAvailable.setCellValueFactory(data -> {
            boolean available = data.getValue().getAvailability();
            String toShow;
            if (available) toShow = "Dostupna";
            else toShow = "Nije dostupna";
            return new ReadOnlyStringWrapper(toShow);
        });
    }

    private void initializeInspectionsInArchiveTable() {
        tableInspectionsInArchive.setItems(listOfArchivedTechnicalInspections);
        colPerson.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getName() + " " + data.getValue().getUser().getSurname()));
        colOwner.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getVehicleOwner()));
        colVehicle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getBrand()));
        colInspectionType.setCellValueFactory(new PropertyValueFactory("inspectionType"));
        colType.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getType().toString()));
    }

    private void initializeInspectionsTable () {
        completedInspections.setItems(listOfTechnicalInspections);
        vehicleOwnerCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getVehicleOwner()));
        vehicleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getBrand()));
        responsiblePersonCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getName() + " " + data.getValue().getUser().getSurname()));
        vehicleTypeCol.setCellValueFactory(new PropertyValueFactory("inspectionType"));
    }

    public void addEquipmentAction(ActionEvent actionEvent) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addEquipment.fxml"), bundle);
            EquipmentController controller = new EquipmentController();
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setTitle(ResourceBundle.getBundle("Translation").getString("addEquipment"));
            newStage.setScene(new Scene(root));
            newStage.setResizable(false);
            newStage.show();
            newStage.setOnHiding( event -> {
                Equipment newEquipment = controller.getEquipment();
                if (newEquipment != null) {
                    dao.addEquipment(newEquipment);
                    listOfEquipment.setAll(dao.equipment());
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteEquipmentAction(ActionEvent actionEvent) {
        Equipment equipment = equipmentTable.getSelectionModel().getSelectedItem();
        if (equipment != null) {
            dao.deleteEquipment(equipment.getId());
            listOfEquipment.setAll(FXCollections.observableArrayList(dao.equipment()));
            equipmentTable.setItems(listOfEquipment);
        }
    }

    public void editEquipmentAction (ActionEvent actionEvent) {
        Equipment equipment = equipmentTable.getSelectionModel().getSelectedItem();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/editEquipment.fxml"), bundle);
            EquipmentController controller = new EquipmentController(equipment);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setTitle(ResourceBundle.getBundle("Translation").getString("editEquipment"));
            newStage.setScene(new Scene(root));
            newStage.setResizable(false);
            newStage.show();
            newStage.setOnHiding( event -> {
                Equipment newEquipment = controller.getEquipment();
                if (newEquipment != null) {
                    dao.updateEquipment(equipment.getId(), newEquipment.getName(), newEquipment.getAvailability());
                    listOfEquipment.setAll(dao.equipment());
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void archiveInspection(ActionEvent actionEvent) {
        TechnicalInspection inspection = completedInspections.getSelectionModel().getSelectedItem();
        if (inspection == null) return;
        dao.updateInspection(inspection.getId(), inspection.getInspectionType(), inspection.getUser(), inspection.getVehicle(), WarrantState.IN_ARCHIVE);
        listOfTechnicalInspections.setAll(FXCollections.observableArrayList(dao.inspectionsDone()));
        completedInspections.setItems(listOfTechnicalInspections);
        listOfArchivedTechnicalInspections.setAll(FXCollections.observableArrayList(dao.inspectionsInArchive()));
        tableInspectionsInArchive.setItems(listOfArchivedTechnicalInspections);
    }

    public void loadScene() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Stage stage = (Stage) tabPane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/main.fxml" ), bundle);
        stage.setScene(new Scene(loader.load()));
    }

    public void openWindowAddUser() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addUser.fxml"), bundle);
            UserController controller = new UserController();
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setTitle(ResourceBundle.getBundle("Translation").getString("adduser"));
            newStage.setScene(new Scene(root));
            newStage.setResizable(false);
            newStage.show();
            newStage.setOnHiding( event -> {
                User newUser = controller.getUser();
                if (newUser != null) {
                    dao.addUser(newUser);
                    if (InspectionDAO.checkIfLoggedUserIsAdmin()) listOfUsers = FXCollections.observableArrayList(dao.getUsersForAdmin());
                    else listOfUsers = FXCollections.observableArrayList(dao.getUsersForMenager());
                    tableOfUsers.refresh();
                    tableOfUsers.setItems(listOfUsers);
                    tableOfUsers.refresh();
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(ActionEvent actionEvent) {
        User user = tableOfUsers.getSelectionModel().getSelectedItem();
        if (user != null) {
            dao.deleteUser(user.getId());
            if (InspectionDAO.checkIfLoggedUserIsAdmin()) listOfUsers.setAll(FXCollections.observableArrayList(dao.getUsersForAdmin()));
            else listOfUsers.setAll(FXCollections.observableArrayList(dao.getUsersForMenager()));
            tableOfUsers.setItems(listOfUsers);
        }
    }

    public void logOut (ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) tabPane.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"), bundle);
        primaryStage.setTitle("Auto kuća Ada");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        Image icon = new Image("TechnicalInspection/favicon.ico");
        primaryStage.getIcons().add(icon);
    }

    public void showArchivedAccounts(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportArchivedAccounts(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showAvailableEquipment(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportAvailableEquipment(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showCommonFailures(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportCommonFailures(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showCompletedExaminationsPerWorker(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportCompletedExaminationsPerWorker(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showCompletedInspections(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportCompletedInspections(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showLastDayExaminations(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportLastDayExaminations(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showLastMonthExaminations(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportLastMonthExaminations(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showLastYearExaminations(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportLastYearExaminations(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showListOfWorker(ActionEvent actionEvent) {
        try {
            new PrintReports().showReportListOfWorker(InspectionDAO.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showPercentageOfPassingInspection(ActionEvent actionEvent) {
            try {
                new PrintReports().showReportPercentageOfPassingInspection(InspectionDAO.getConnection());
            } catch (JRException e1) {
                e1.printStackTrace();
            }
        }
}
