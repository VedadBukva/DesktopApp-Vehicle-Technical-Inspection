package DatabaseWork;

import Exceptions.NoInternetException;
import TechnicalInspection.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Enum.*;

public class InspectionDAO {
    private static InspectionDAO instance;
    private static Connection conn;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
    private static RoleType usersRole;

    public static InspectionDAO getInstance() {
        if (instance == null) instance = new InspectionDAO();
        return instance;
    }

    public static Connection getConnection() {
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

    // GET request methods

    private JSONArray connectToURL(String path) {
        URL url = null;
        JSONArray jsonArray = null;
        try {
            url = new URL("http://localhost:8080/api/" + path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            if (json.isEmpty()) return null;
            jsonArray = new JSONArray(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public ArrayList<Vehicle> vehicles() {
        ArrayList<Vehicle> result = new ArrayList<>();
        JSONArray jsonArray = connectToURL("vehicle");
        if (jsonArray == null) return null;
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
        return result;
    }

    public Vehicle getVehicle(int id) {
        URL url = null;
        Vehicle vehicle = null;
        try {
            url = new URL("http://localhost:8080/api/vehicle/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            if (json.isEmpty()) return null;
            JSONObject jo = new JSONObject(json);
            VehicleType vT = VehicleType.getVehicleType(jo.getString("type"));
            String date = jo.getString("date_of_use");
            LocalDate releaseDate = LocalDate.parse(date, formatter);
            String date1 = jo.getString("previous_inspection");
            LocalDate previous = LocalDate.parse(date1, formatter);
            ArrayList<Malfunction> malfunctions = getVehicleMalfunctions(id);
            vehicle = new Vehicle(id, jo.getString("owner_name"), jo.getString("brand"), vT, jo.getString("serial_number"), jo.getInt("production_year"), releaseDate, previous, malfunctions);
        } catch (IOException e) {
            new NoInternetException();
        }
        return vehicle;
    }

    public ArrayList<Malfunction> malfunctions() {
        ArrayList<Malfunction> result = new ArrayList<>();
        JSONArray jsonArray = connectToURL("failure");
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String date = jo.getString("accurrence_date");
            LocalDate emergenceDate = LocalDate.parse(date, formatter);

            LocalDate repairDate;
            if (!jo.isNull("repair_date")) {
                String rDate = jo.getString("repair_date");
                repairDate = LocalDate.parse(rDate, formatter);
            } else repairDate = null;

            Vehicle vehicle = getVehicle(jo.getInt("vehicle"));
            Malfunction malfunction = new Malfunction(jo.getInt("id"), jo.getString("name"), vehicle, emergenceDate, repairDate);
            result.add(malfunction);
        }
        return result;
    }

    public ArrayList<Malfunction> getVehicleMalfunctions(int id) {
        ArrayList<Malfunction> result = new ArrayList<>();
        JSONArray jsonArray = connectToURL("failure");
        if (jsonArray == null) return null;
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
        return result;
    }

    public ArrayList<Equipment> equipment() {
        ArrayList<Equipment> equipment = new ArrayList<>();
        JSONArray jsonArray = connectToURL("part");
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String available = jo.getString("availability");
            boolean isAvailable = false;
            if (available.equals("DOSTUPAN")) isAvailable = true;
            Equipment eq = new Equipment(jo.getInt("id"), jo.getString("name"), isAvailable);
            equipment.add(eq);
        }
        return equipment;
    }

    public ArrayList<User> users() {
        ArrayList<User> users = new ArrayList<>();
        JSONArray jsonArray = connectToURL("user");
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String date = jo.getString("birth_date");
            LocalDate birthDate = LocalDate.parse(date, formatter);
            RoleType role = RoleType.getRoleType(jo.getString("position"));
            User user = new User(jo.getInt("id"), jo.getString("first_name"), jo.getString("last_name"), jo.getString("jmbg"), birthDate, jo.getString("adress"),jo.getString("zip_code"), jo.getString("mail"), jo.getString("phone_number"), jo.getString("user_name"), jo.getString("password"), role);
            users.add(user);
        }
        return users;
    }

    public User getUser(int id) {
        URL url = null;
        User user = null;
        try {
            url = new URL("http://localhost:8080/api/user/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            if (json.isEmpty()) return null;
            JSONObject jo = new JSONObject(json);
            String date = jo.getString("birth_date");
            LocalDate birthDate = LocalDate.parse(date, formatter);
            RoleType role = RoleType.getRoleType(jo.getString("position"));

            user = new User(jo.getInt("id"), jo.getString("first_name"), jo.getString("last_name"), jo.getString("jmbg"), birthDate, jo.getString("adress"),jo.getString("zip_code"), jo.getString("mail"), jo.getString("phone_number"), jo.getString("user_name"), jo.getString("password"), role);
        } catch (IOException e) {
            new NoInternetException();
        }
        return user;
    }

    public ArrayList<TechnicalInspection> inspections() {
        ArrayList<TechnicalInspection> inspections = new ArrayList<>();
        JSONArray jsonArray = connectToURL("review");
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            InspectionType inspectionType = InspectionType.getInspectionType(jo.getString("kind"));
            WarrantState warrantState = WarrantState.getWarrantState(jo.getString("state"));
            User user = getUser(jo.getInt("responsible_person"));
            Vehicle vehicle = getVehicle(jo.getInt("vehicle"));
            TechnicalInspection technicalInspection = new TechnicalInspection(jo.getInt("id"), inspectionType, user, vehicle, warrantState);
            inspections.add(technicalInspection);
        }
        return inspections;
    }

    public List<TechnicalInspection> inspectionsDone () {
        List<TechnicalInspection> inspections = new ArrayList<>();
        JSONArray jsonArray = connectToURL("review");
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            InspectionType inspectionType = InspectionType.getInspectionType(jo.getString("kind"));
            WarrantState warrantState = WarrantState.getWarrantState(jo.getString("state"));
            if (warrantState == WarrantState.DONE) {
                User user = getUser(jo.getInt("responsible_person"));
                Vehicle vehicle = getVehicle(jo.getInt("vehicle"));
                TechnicalInspection technicalInspection = new TechnicalInspection(jo.getInt("id"), inspectionType, user, vehicle, warrantState);
                inspections.add(technicalInspection);
            }
        }
        return inspections;
    }

    public List<TechnicalInspection> inspectionsInArchive () {
        List<TechnicalInspection> inspections = new ArrayList<>();
        JSONArray jsonArray = connectToURL("review");
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            InspectionType inspectionType = InspectionType.getInspectionType(jo.getString("kind"));
            WarrantState warrantState = WarrantState.getWarrantState(jo.getString("state"));
            if (warrantState == WarrantState.IN_ARCHIVE) {
                User user = getUser(jo.getInt("responsible_person"));
                Vehicle vehicle = getVehicle(jo.getInt("vehicle"));
                TechnicalInspection technicalInspection = new TechnicalInspection(jo.getInt("id"), inspectionType, user, vehicle, warrantState);
                inspections.add(technicalInspection);
            }
        }
        return inspections;
    }

    // POST request methods

    public void addVehicle(Vehicle vehicle) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/vehicle");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JSONObject vehicleObj = new JSONObject();
        vehicleObj.put("id", vehicle.getId());
        vehicleObj.put("owner_name", vehicle.getVehicleOwner());
        vehicleObj.put("brand", vehicle.getBrand());
        vehicleObj.put("type", vehicle.getType());
        vehicleObj.put("serial_number", vehicle.getSerialNumber());
        vehicleObj.put("production_year", vehicle.getProductionYear());
        vehicleObj.put("date_of_use", vehicle.getReleaseDate());
        vehicleObj.put("previous_inspection", vehicle.getPreviousInspection());
        int id = addViaHttp(vehicleObj, url);
        vehicle.setId(id);
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
        jsonMalfunction.put("vehicle", malfunction.getVehicle().getId());
        jsonMalfunction.put("accurrence_date", malfunction.getEmergenceDate());
        jsonMalfunction.put("repair_date", malfunction.getRepairDate());
        int id = addViaHttp(jsonMalfunction, url);
        malfunction.setId(id);
    }

    public void addUser(User user) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/user");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("id", user.getId());
        jsonUser.put("first_name", user.getName());
        jsonUser.put("last_name", user.getSurname());
        jsonUser.put("position", user.getRole());
        jsonUser.put("jmbg", user.getJmbg());
        jsonUser.put("birth_date", user.getBirthDate());
        jsonUser.put("adress", user.getAddress());
        jsonUser.put("zip_code", user.getPostalNumber());
        jsonUser.put("mail", user.getMail());
        jsonUser.put("phone_number", user.getPhoneNumber());
        jsonUser.put("user_name", user.getUserName());
        jsonUser.put("password", user.getPassword());
        int id = addViaHttp(jsonUser, url);
        user.setId(id);
    }

    public void addEquipment(Equipment equipment) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/part");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject jsonEq = new JSONObject();
        jsonEq.put("id", equipment.getId());
        jsonEq.put("name", equipment.getName());
        String available;
        if (equipment.getAvailability()) available = "DOSTUPAN";
        else available = "NEDOSTUPAN";
        jsonEq.put("availability", available);
        int id = addViaHttp(jsonEq, url);
        equipment.setId(id);
    }

    public void addInspection(TechnicalInspection inspection) {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/api/review");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject jsonInspection = new JSONObject();
        jsonInspection.put("id", inspection.getId());
        jsonInspection.put("state", inspection.getWarrantState());
        jsonInspection.put("kind", inspection.getInspectionType());
        jsonInspection.put("responsible_person", inspection.getUser().getId());
        jsonInspection.put("vehicle", inspection.getVehicle().getId());
        int id = addViaHttp(jsonInspection, url);
        inspection.setId(id);
    }

    private int addViaHttp (JSONObject jsonObject, URL url) {
        HttpURLConnection con = null;
        JSONObject jsonObject1 = null;
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
            jsonObject1 = new JSONObject(json);
            entry.close();
        } catch (IOException e) {
            new NoInternetException();
        }
        return jsonObject1.getInt("id");
    }


    // PUT request methods

    public void updateVehicle(int vehicleId, String name, String brand, InspectionType type,
                                String sNumber, int pYear, LocalDate rDate, LocalDate prevInsp) {
        if (vehicleId > vehicles().size()) return;
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/vehicle/" + vehicleId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject vehicleObj = new JSONObject();
        vehicleObj.put("owner_name", name);
        vehicleObj.put("brand", brand);
        vehicleObj.put("type", type);
        vehicleObj.put("serial_number", sNumber);
        vehicleObj.put("production_year", pYear);
        vehicleObj.put("date_of_use", rDate);
        vehicleObj.put("previous_inspection", prevInsp);
        updateViaHttp(vehicleObj, url);
    }

    public void updateInspection(int inspectionId, InspectionType type, User user, Vehicle vehicle,
                              WarrantState state) {
        if (inspectionId > inspections().size()) return;
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/review/" + inspectionId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject jsonInspection = new JSONObject();
        jsonInspection.put("state", state);
        jsonInspection.put("kind", type);
        jsonInspection.put("responsible_person", user.getId());
        jsonInspection.put("vehicle", vehicle.getId());
        updateViaHttp(jsonInspection, url);
    }

    public void updateEquipment(int id, String name, Boolean available) {
        if (id > equipment().size()) return;
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/part/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject jsonEquipment = new JSONObject();
        jsonEquipment.put("name", name);
        String isAvailable;
        if (available) isAvailable = "DOSTUPAN";
        else isAvailable = "NEDOSTUPAN";
        jsonEquipment.put("availability", isAvailable);
        updateViaHttp(jsonEquipment, url);
    }

    private void updateViaHttp (JSONObject jo, URL url) {
        HttpURLConnection con = null;
        try {
            byte[] data = jo.toString().getBytes();
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.connect();
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

    // DELETE request methods

    public void deleteUser(int id) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/user/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (id > users().size()) return;
        deleteViaHttp(id, url);
    }

    public void deleteInspection(int id) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/review/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (id > inspections().size()) return;
        deleteViaHttp(id, url);
    }

    public void deleteEquipment(int id) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/part/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (id > equipment().size()) return;
        deleteViaHttp(id, url);
    }

    public void deleteVehicle(int id) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/vehicle/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (id > vehicles().size()) return;
        deleteViaHttp(id, url);
    }

    public void deleteMalfunction(int id) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL("http://localhost:8080/api/failure/" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (id > malfunctions().size()) return;
        deleteViaHttp(id, url);
    }

    private void deleteViaHttp (int id, URL url) {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/application/json");
            con.setDoOutput(true);
            con.connect();
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(id);
            out.flush();
            out.close();

            BufferedReader entry = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            entry.close();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if exists in database

    public boolean loginCheck(String userName, String password) {
        URL url = null;
        JSONObject object = null;
        try {
            url = new URL("http://localhost:8080/api/user/" + userName + "/" + password);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader entry = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String json = "", line = "";
            while ((line = entry.readLine()) != null) {
                json = json + line;
            }
            if (json.isEmpty()) return false;
            JSONObject jo = new JSONObject(json);
            RoleType type = RoleType.getRoleType(jo.getString("position"));
            if (type != null && type.equals(RoleType.EMPLOYEE)) return false;
            setLoggedUserRole(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void setLoggedUserRole (RoleType type) {
        usersRole = type;
    }

    public static boolean checkIfLoggedUserIsAdmin () {
        return usersRole.toString().equals("ADMIN");
    }
}
