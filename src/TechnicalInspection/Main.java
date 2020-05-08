package TechnicalInspection;

import DatabaseWork.InspectionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

import Enum.VehicleType;
public class Main extends Application {
    private InspectionDAO dao;
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Auto kuća Ada");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();*/
        dao = InspectionDAO.getInstance();
        /*ArrayList<Vehicle> vehicles = dao.vehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.printf("Vehicle owner " + vehicles.get(i).getVehicleOwner() + "\n");
            System.out.printf("Brand " + vehicles.get(i).getBrand()+ "\n");
            System.out.printf("Type " + vehicles.get(i).getType()+ "\n");
            System.out.printf("Serial number  " + vehicles.get(i).getSerialNumber()+ "\n");
            System.out.printf("Production year " + vehicles.get(i).getProductionYear()+ "\n");
            System.out.printf("Release date " + vehicles.get(i).getReleaseDate()+ "\n");
            System.out.printf("Previous inspection " + vehicles.get(i).getPreviousInspection()+ "\n");
            for (int j = 0; j < vehicles.get(i).getMalfunctions().size(); j++) {
                System.out.printf("Malfunction " + (j + 1) + vehicles.get(i).getMalfunctions().get(j).getMalfunctionName() + "\n");
                System.out.printf("Malfunction date " + (j + 1) + vehicles.get(i).getMalfunctions().get(j).getEmergenceDate() + "\n");
                System.out.printf("Malfunction repair " + (j + 1) + vehicles.get(i).getMalfunctions().get(j).getRepairDate() + "\n");
            }
        }

        System.out.println("Gotovo dodavanje");*/
        /*ArrayList<Malfunction> malfunctions = dao.malfunctions();
        for (int i = 0; i < malfunctions.size(); i++) {
            System.out.printf("Malfunction " + malfunctions.get(i).getMalfunctionName() + "\n");
            System.out.printf("Malfunction date " +  malfunctions.get(i).getEmergenceDate() + "\n");
            System.out.printf("Malfunction repair " + malfunctions.get(i).getRepairDate() + "\n");

        }
        Vehicle vehicle = new Vehicle("Haris Kicin", "Mercedes S63", VehicleType.PASSENGER_VEHICLE, "11223344", 2020, LocalDate.now(), LocalDate.now());
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
        System.out.println();
        ArrayList<TechnicalInspection> inspections = dao.inspections();
        for (int i = 0; i < inspections.size(); i++) {
            System.out.println("Inspection ID " + inspections.get(i).getId());
            System.out.println("Inspection state " + inspections.get(i).getInspectionType());
            System.out.println("Inspection kind " + inspections.get(i).getWarrantState());
            System.out.println("Inspection employee " + inspections.get(i).getEmployeeID());
            System.out.println("Inspection vehicle " + inspections.get(i).getVehicleID());
        }*/
        User user = dao.getUser(1);
        System.out.printf("User ID " + user.getId() + "\n");
        System.out.printf("User name " +  user.getName() + "\n");
        System.out.printf("User surname " + user.getSurname() + "\n");
        System.out.printf("User address " + user.getAddress() + "\n");
        System.out.printf("User JMBG " + user.getJmbg() + "\n");
        System.out.printf("User mail " + user.getMail() + "\n");
        System.out.printf("User user name " + user.getUserName() + "\n");
        System.out.printf("User password " + user.getPassword() + "\n");
        System.out.printf("User postal number " + user.getPostalNumber() + "\n");
        System.out.printf("User birth date " + user.getBirthDate() + "\n");
        System.out.printf("User role " + user.getRole() + "\n");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
