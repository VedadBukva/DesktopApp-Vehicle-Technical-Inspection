package Exceptions;

import javafx.scene.control.Alert;

import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

public class NoInternetException {
    public static void showAlert() {
        if(Locale.getDefault().getLanguage().equals("en")) {
            String warning = "Warning";
            String headerNoInternet = "Problem with connection";
            String noInternet = "Computer is not connected on internet.";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(warning);
            alert.setHeaderText(headerNoInternet);
            alert.setContentText(noInternet);
            alert.showAndWait();
        } else {
            String warning = "Upozorenje";
            String headerNoInternet = "Problem sa konekcijom";
            String noInternet = "Uređaj nije povezan sa internetom.";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(warning);
            alert.setHeaderText(headerNoInternet);
            alert.setContentText(noInternet);
            alert.showAndWait();
        }
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
