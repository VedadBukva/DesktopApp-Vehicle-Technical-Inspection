package TechnicalInspection;

import DatabaseWork.InspectionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ResourceBundle;
import Enum.*;

public class Main extends Application {
    private InspectionDAO dao;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image icon = new Image("/images/favicon.ico");
        primaryStage.getIcons().add(icon);
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"), bundle);
        primaryStage.setTitle("Auto kuća Ada");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        /*dao = InspectionDAO.getInstance();
        User user = new User("Haris", "Kicin", "0701000163307", LocalDate.of(2000, 1, 7), "Isaka Samokovlije", "71000", "haris.kicin20@gmail.com", "061277327", "hkicin1", "haris123", RoleType.ADMIN);
        dao.addUser(user);
        System.out.println("gotovo");*/
        /*ArrayList<Malfunction> malfunctions = dao.malfunctions();
        for (int i = 0; i < malfunctions.size(); i++) {
            System.out.printf("Malfunction " + malfunctions.get(i).getMalfunctionName() + "\n");
            System.out.printf("Malfunction date " +  malfunctions.get(i).getEmergenceDate() + "\n");
            System.out.printf("Malfunction repair " + malfunctions.get(i).getRepairDate() + "\n");

        }*/
        /*Vehicle vehicle = new Vehicle("Haris Kicin", "Mercedes S63", VehicleType.PASSENGER_VEHICLE, "11223344", 2020, LocalDate.now(), LocalDate.now());
        dao.addVehicle(vehicle);
        Malfunction malfunction = new Malfunction("dizne", vehicle.getId(), LocalDate.now(), LocalDate.now());
        dao.addMalfunction(malfunction);*/
        /*ArrayList<Equipment> equipment = dao.equipment();
        for (int i = 0; i < equipment.size(); i++) {
            System.out.println();
            System.out.printf("Equipment ID " + equipment.get(i).getId() + "\n");
            System.out.printf("Equipment name " +  equipment.get(i).getEquipmentName() + "\n");
            System.out.printf("Equipment availability " + equipment.get(i).getAvailability() + "\n");
        }*/
        /*ArrayList<User> users = dao.users();
        for (int i = 0; i < users.size(); i++) {
            System.out.println();
            System.out.printf("User ID " + users.get(i).getId() + "\n");
            System.out.printf("User name " +  users.get(i).getName() + "\n");
            System.out.printf("User surname " + users.get(i).getSurname() + "\n");
            System.out.printf("User address " + users.get(i).getAddress() + "\n");
            System.out.printf("User JMBG " + users.get(i).getJmbg() + "\n");
            System.out.printf("User mail " + users.get(i).getMail() + "\n");
            System.out.printf("User user name " + users.get(i).getUserName() + "\n");
            System.out.printf("User password " + users.get(i).getPassword() + "\n");
            System.out.printf("User postal number " + users.get(i).getPostalNumber() + "\n");
            System.out.printf("User birth date " + users.get(i).getBirthDate() + "\n");
            System.out.printf("User role " + users.get(i).getRole() + "\n");
        }
        System.out.println();*/
        /*ArrayList<TechnicalInspection> inspections = dao.inspections();
        for (int i = 0; i < inspections.size(); i++) {
            System.out.println("Inspection ID " + inspections.get(i).getId());
            System.out.println("Inspection state " + inspections.get(i).getInspectionType());
            System.out.println("Inspection kind " + inspections.get(i).getWarrantState());
            System.out.println("Inspection employee " + inspections.get(i).getUser().getName());
            System.out.println("Inspection vehicle " + inspections.get(i).getVehicle().getBrand());
        }*/
        /*User user = new User("Haris", "Kicin", "0701000163307", LocalDate.of(2000, 1, 7), "Isaka Samokovlije", "71000", "haris.kicin20@gmail.com", "061277327", "hkicin1", "neki", RoleType.ADMIN);
        dao.addUser(user);
        Vehicle vehicle = dao.getVehicle(1);
        dao.addInspection(new TechnicalInspection(InspectionType.REGULAR, user, vehicle, WarrantState.IN_PROGRESS));
        System.out.println("Gotovo");*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
