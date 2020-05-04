package DatabaseWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InspectionDAO {
    private static InspectionDAO instance;
    private Connection conn;

    private PreparedStatement listAllVehiclesQuery;

    public static InspectionDAO getInstance() {
        if (instance == null) instance = new InspectionDAO();
        return instance;
    }

    public Connection getConnection() { return conn; }

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/api", "root", "root");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            listAllVehiclesQuery = conn.prepareStatement("");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                listAllVehiclesQuery = conn.prepareStatement("");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


    }

    private void regenerateDatabase() {
    }
}
