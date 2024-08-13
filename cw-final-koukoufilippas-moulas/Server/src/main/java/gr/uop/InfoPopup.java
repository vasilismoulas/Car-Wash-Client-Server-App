<<<<<<< HEAD
package gr.uop;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class InfoPopup extends Dialog<Void>{

    private String licencePlate;
    private String vehiclekind;
    private String services;
    private String cost;
    private IncomeBook vehicleInfo;
    private String servicesAsAString = "";


    public InfoPopup(IncomeBook vehicleInfo, String licencePlate, String vehiclekind, String services, String cost, String time) {

        String[] servicesArray = services.split(",");

        for (int i = 0; i < servicesArray.length; i++) {
            servicesArray[i] = servicesArray[i].substring(1);
            // System.out.println(servicesArray[i]);
            int serviceCost = vehicleInfo.getPrice(servicesArray[i], vehiclekind);
            // System.out.println(serviceCost);
            servicesAsAString += servicesArray[i] + ": " + serviceCost + "€\n";
        }
        // System.out.println(servicesAsAString);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        String message = "Licence Plate: " + licencePlate + "\n" + servicesAsAString + "Total Cost: " + cost + "€";

        alert.setTitle("Confirmation");
        alert.setHeaderText(message);
        alert.setContentText("You wanna print the receipt?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            String wantedLine = vehicleInfo.searchDatabase(time);

            String[] lineArray = wantedLine.split(";");

            String zero = lineArray[0];
            String one = lineArray[1];
            String two = lineArray[2];
            String three = lineArray[3];
            String four = lineArray[4];
            String five = lineArray[5];

            CurrentDateTime currentDateTime = new CurrentDateTime();
            String currentTime = currentDateTime.GetCurrentDateTime();

            String newData = String.join("; ", zero, one, two, three, four, "YES", currentTime);

            // System.out.println(newData);

            vehicleInfo.moveToSecondFile(wantedLine);

            vehicleInfo.addToSecondFile(newData);

            vehicleInfo.moveToFirstFile();

            vehicleInfo.deleteAllContentSecondFile();

        } else if (result.get() == ButtonType.CANCEL) {
            //System.out.println("Cancel");
            alert.close();
        } else if (result.get() == ButtonType.CLOSE) {
            //System.out.println("Close");
            alert.close();
        }

    }
    
}
=======
package gr.uop;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class InfoPopup extends Dialog<Void>{

    private String licencePlate;
    private String vehiclekind;
    private String services;
    private String cost;
    private IncomeBook vehicleInfo;
    private String servicesAsAString = "";


    public InfoPopup(IncomeBook vehicleInfo, String licencePlate, String vehiclekind, String services, String cost, String time) {

        String[] servicesArray = services.split(",");

        for (int i = 0; i < servicesArray.length; i++) {
            servicesArray[i] = servicesArray[i].substring(1);
            // System.out.println(servicesArray[i]);
            int serviceCost = vehicleInfo.getPrice(servicesArray[i], vehiclekind);
            // System.out.println(serviceCost);
            servicesAsAString += servicesArray[i] + ": " + serviceCost + "€\n";
        }
        // System.out.println(servicesAsAString);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        String message = "Licence Plate: " + licencePlate + "\n" + servicesAsAString + "Total Cost: " + cost + "€";

        alert.setTitle("Confirmation");
        alert.setHeaderText(message);
        alert.setContentText("You wanna print the receipt?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            String wantedLine = vehicleInfo.searchDatabase(time);

            String[] lineArray = wantedLine.split(";");

            String zero = lineArray[0];
            String one = lineArray[1];
            String two = lineArray[2];
            String three = lineArray[3];
            String four = lineArray[4];
            String five = lineArray[5];

            CurrentDateTime currentDateTime = new CurrentDateTime();
            String currentTime = currentDateTime.GetCurrentDateTime();

            String newData = String.join("; ", zero, one, two, three, four, "YES", currentTime);

            // System.out.println(newData);

            vehicleInfo.moveToSecondFile(wantedLine);

            vehicleInfo.addToSecondFile(newData);

            vehicleInfo.moveToFirstFile();

            vehicleInfo.deleteAllContentSecondFile();

        } else if (result.get() == ButtonType.CANCEL) {
            //System.out.println("Cancel");
            alert.close();
        } else if (result.get() == ButtonType.CLOSE) {
            //System.out.println("Close");
            alert.close();
        }

    }
    
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
