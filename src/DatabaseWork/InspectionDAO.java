package DatabaseWork;

import Exceptions.NoInternetException;
import TechnicalInspection.Malfunction;
import TechnicalInspection.Vehicle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import Enum.VehicleType;

public class InspectionDAO {
    private static InspectionDAO instance;
    private Connection conn;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);


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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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

    public void addVehicle(Vehicle vehicle) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/vehicle");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONObject vehicleObj = new JSONObject();
        vehicleObj.put("owner_name", vehicle.getVehicleOwner());
        vehicleObj.put("brand", vehicle.getBrand());
        vehicleObj.put("type", vehicle.getType());
        vehicleObj.put("serial_number", vehicle.getSerialNumber());
        vehicleObj.put("production_year", vehicle.getProductionYear());
        vehicleObj.put("date_of_use", vehicle.getReleaseDate());
        vehicleObj.put("previous_inspection", vehicle.getPreviousInspection());
        addViaHttp(vehicleObj, url);
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
                    String date = jo.getString("accurrence_date");
                    LocalDate emergenceDate = LocalDate.parse(date, formatter);
                    LocalDate repairDate;
                    if (!jo.isNull("repair_date")) {
                        String date2 = jo.getString("repair_date");
                        repairDate = LocalDate.parse(date2, formatter);
                    } else repairDate = null;
                    Malfunction malfunction = new Malfunction(jo.getString("name"), emergenceDate, repairDate);
                    result.add(malfunction);
                }
            }
        } catch (IOException e) {
            new NoInternetException();
        }
        return result;
    }

    public ArrayList<Malfunction> malfunctions() {
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
                String date = jo.getString("accurrence_date");
                LocalDate emergenceDate = LocalDate.parse(date, formatter);

                LocalDate repairDate;
                if (!jo.isNull("repair_date")) {
                    String rDate = jo.getString("repair_date");
                    repairDate = LocalDate.parse(rDate, formatter);
                } else repairDate = null;

                Malfunction malfunction = new Malfunction(jo.getString("name"), emergenceDate, repairDate);
                result.add(malfunction);
            }
        } catch (IOException e) {
            new NoInternetException();
        }
        return result;
    }

    public void addMalfunction(Malfunction malfunction) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/failure");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject jsonMalfunction = new JSONObject();
        jsonMalfunction.put("name", malfunction.getMalfunctionName());
        jsonMalfunction.put("accurrence_date", malfunction.getEmergenceDate());
        jsonMalfunction.put("repair_date", malfunction.getRepairDate());
        addViaHttp(jsonMalfunction, url);
    }

    public void addViaHttp (JSONObject jsonObject, URL url) {
        HttpURLConnection con = null;
        try {
            byte[] data = jsonObject.toString().getBytes();
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(data);
            out.flush();
            out.close();

            BufferedReader entry = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            entry.close();
        } catch (IOException e) {
            new NoInternetException();
        }
    }
}
