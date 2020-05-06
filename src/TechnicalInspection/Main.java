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
            }
        }*/
        Vehicle vehicle = new Vehicle("Haris Kicin", "Mercedes S63", VehicleType.PASSENGER_VEHICLE, "11223344", 2020, LocalDate.now(), LocalDate.now());
        dao.addVehicle(vehicle);
        System.out.println("Gotovo dodavanje");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
