package DatabaseWork;

import Exceptions.NoInternetException;
import TechnicalInspection.Malfunction;
import TechnicalInspection.Vehicle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import Enum.VehicleType;

public class InspectionDAO {
    private static InspectionDAO instance;
    private Connection conn;

    private PreparedStatement listAllVehiclesQuery;

    public static InspectionDAO getInstance() {
        if (instance == null) instance = new InspectionDAO();
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private InspectionDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/dbinspection", "root", "root");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

       /* try {
            listAllVehiclesQuery = conn.prepareStatement("");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                listAllVehiclesQuery = conn.prepareStatement("");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }*/


    }

    private void regenerateDatabase() {
    }

    public ArrayList<Vehicle> vehicles() {
        ArrayList<Vehicle> result = new ArrayList<>();
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/vehicle");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                String date = jo.getString("date_of_use");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                LocalDate releaseDate = LocalDate.parse(date, formatter);

                String previousInspectionDate = jo.getString("previous_inspection");
                LocalDate previousInspection = LocalDate.parse(previousInspectionDate, formatter);
                int id = jo.getInt("id");
                ArrayList<Malfunction> malfunctions = getVehicleMalfunctions(id);
                VehicleType type = VehicleType.getVehicleType(jo.getString("type"));
                Vehicle vehicle = new Vehicle(jo.getString("owner_name"), jo.getString("brand"), type,
                        jo.getString("serial_number"), jo.getInt("production_year"), releaseDate, previousInspection, malfunctions);
                result.add(vehicle);
            }
        } catch (IOException e) {
            new NoInternetException();
        }
        return result;
    }

    private ArrayList<Malfunction> getVehicleMalfunctions(int id) {
        ArrayList<Malfunction> result = new ArrayList<>();
        URL url = null;

        try {
            url = new URL("http://localhost:8080/api/failure");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                int idCheck = jo.getInt("vehicle");
                if (idCheck == id) {
                    Malfunction malfunction = new Malfunction(jo.getString("name"));
                    result.add(malfunction);
                }
            }
        } catch (IOException e) {
            new NoInternetException();
        }
        return result;
    }
}
