package Exceptions;

import javafx.scene.control.Alert;

import java.net.URL;
import java.net.URLConnection;

public class NoInternetException {
    public static void showAlert() {
        String noInternet = "Uređaj nije povezan sa internetom.";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(noInternet);
        alert.showAndWait();
    }

    public static boolean haveInternetConnectivity() {
        boolean haveInternet = false;
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Connection Successful");
            haveInternet = true;
        }
        catch (Exception e) {
            System.out.println("Internet Not Connected");
        }
        return haveInternet;
    }

}
