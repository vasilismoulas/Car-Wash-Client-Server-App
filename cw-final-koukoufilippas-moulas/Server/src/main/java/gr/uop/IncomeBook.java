<<<<<<< HEAD
package gr.uop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class IncomeBook {
    private Label licensePlateLabel;
    private Label calculatecosLabel;
    private String VehiclesKind;
    private String currentTime;
    private ArrayList<String> servicesList;     //All the Services
    private FileWriter writer;

    private StringProperty licencePlate = new SimpleStringProperty();
    private StringProperty time = new SimpleStringProperty();

    public IncomeBook(String licencePlate, String cost, String vehiclekind, String currentTime, ArrayList<String> services){
        this.licensePlateLabel = new Label();
        this.calculatecosLabel = new Label();
        this.VehiclesKind = vehiclekind;
        this.currentTime = currentTime;
        this.servicesList = services;

        this.licensePlateLabel.setText(licencePlate);
        this.calculatecosLabel.setText(cost);
    }

    public String getLicensePlate() {
        return licensePlateLabel.getText(); 
    }

    public String getCost() {
        return calculatecosLabel.getText();
    }

    public String getArrivalTime() {
        return currentTime;
    }

    public StringProperty getLicensePlatePropert() {
        return licencePlate;
    }

    public StringProperty getTimeProperty() {
        return time;
    }

    public void sendDataToTable() {

    }

    public void createFile() {

        try {
            this.writer = new FileWriter("database.txt");
            //this.writer.write("hahaha");
            //this.writer.append("Database: \n");
            this.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createSecondFile() {
        try {
            this.writer = new FileWriter("database2.txt");
            //this.writer.write("hahaha");
            //this.writer.append("Database: \n");
            this.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToFile(String content) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database.txt", true));

            bufferedWriter.write(content);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("Error appending to the file: " + e.getMessage());
        }
    }

    public void addToSecondFile(String newData) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database2.txt", true));

            bufferedWriter.write(newData);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("Error appending to the file: " + e.getMessage());
        }


    }

    public void addToDatabase() {
        try {
            this.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    public String searchDatabase(String timeToCheck) {

        String fileName = "database.txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String[] elements = line.split(";");
                    
                    String timeElement = elements[4];

                    if (timeElement.contains(timeToCheck)) {
                        return line;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                        
        } else {
            System.out.println("There is no database when you initiate the program");              
        }
        return null;
    }

    public void moveToSecondFile(String lineToExclude) {
        String fileName = "database.txt";
        String secondFileName = "database2.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(secondFileName))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {

                if (!currentLine.equals(lineToExclude)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToFirstFile() {
        String fileName = "database.txt";
        String secondFileName = "database2.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(secondFileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                writer.write(currentLine);
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllContentSecondFile() {
        String secondFileName = "database2.txt";

        try {
            FileWriter writer = new FileWriter(secondFileName);
            writer.write("");
            writer.close();

        } catch (Exception e) {

        }
    }

//map key->service name, value cost service, 3 maps for each vehicle

    public int getPrice(String service, String vehiclekind) {
        int cost;
        String combined = service + "/" + vehiclekind;

        switch (combined) {
//Car
            case "Exterior Wash/Car":
                cost = 7;
                break;
            case "Interior Wash/Car":
                cost = 6;
                break;
            case "Exterior(+)Interior/Car":
                cost = 12;
                break;
            case "Special Exterior Wash/Car":
                cost = 9;
                break;
            case "Special Interior Wash/Car":
                cost = 8;
                break;
            case "Special Exterior(+)Interior/Car":
                cost = 15;
                break;
            case "Biological Interior Cleaning/Car":
                cost = 80;
                break;
            case "Waxing-Polishing/Car":
                cost = 80;
                break;
            case "Engine Cleaning/Car":
                cost = 20;
                break;
            case "Chassis Wash/Car":
                cost = 3;
                break;
//Jeep
            case "Exterior Wash/Jeep":
                cost = 8;
                break;
            case "Interior Wash/Jeep":
                cost = 7;
                break;
            case "Exterior(+)Interior/Jeep":
                cost = 14;
                break;
            case "Special Exterior Wash/Jeep":
                cost = 10;
                break;
            case "Special Interior Wash/Jeep":
                cost = 9;
                break;
            case "Special Exterior(+)Interior/Jeep":
                cost = 17;
                break;
            case "Biological Interior Cleaning/Jeep":
                cost = 80;
                break;
            case "Waxing-Polishing/Jeep":
                cost = 90;
                break;
            case "Engine Cleaning/Jeep":
                cost = 20;
                break;
            case "Chassis Wash/Jeep":
                cost = 3;
                break;
//MotorCycle
            case "Exterior Wash/MotorCycle":
                cost = 6;
                break;
            case "Special Exterior Wash/MotorCycle":
                cost = 8;
                break;
            case "Waxing-Polishing/MotorCycle":
                cost = 40;
                break;
            case "Engine Cleaning/MotorCycle":
                cost = 10;
                break;
                
            default:
                cost = 0;
                break;
        }
        return cost;
    }
}
=======
package gr.uop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class IncomeBook {
    private Label licensePlateLabel;
    private Label calculatecosLabel;
    private String VehiclesKind;
    private String currentTime;
    private ArrayList<String> servicesList;     //All the Services
    private FileWriter writer;

    private StringProperty licencePlate = new SimpleStringProperty();
    private StringProperty time = new SimpleStringProperty();

    public IncomeBook(String licencePlate, String cost, String vehiclekind, String currentTime, ArrayList<String> services){
        this.licensePlateLabel = new Label();
        this.calculatecosLabel = new Label();
        this.VehiclesKind = vehiclekind;
        this.currentTime = currentTime;
        this.servicesList = services;

        this.licensePlateLabel.setText(licencePlate);
        this.calculatecosLabel.setText(cost);
    }

    public String getLicensePlate() {
        return licensePlateLabel.getText(); 
    }

    public String getCost() {
        return calculatecosLabel.getText();
    }

    public String getArrivalTime() {
        return currentTime;
    }

    public StringProperty getLicensePlatePropert() {
        return licencePlate;
    }

    public StringProperty getTimeProperty() {
        return time;
    }

    public void sendDataToTable() {

    }

    public void createFile() {

        try {
            this.writer = new FileWriter("database.txt");
            //this.writer.write("hahaha");
            //this.writer.append("Database: \n");
            this.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createSecondFile() {
        try {
            this.writer = new FileWriter("database2.txt");
            //this.writer.write("hahaha");
            //this.writer.append("Database: \n");
            this.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToFile(String content) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database.txt", true));

            bufferedWriter.write(content);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("Error appending to the file: " + e.getMessage());
        }
    }

    public void addToSecondFile(String newData) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("database2.txt", true));

            bufferedWriter.write(newData);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("Error appending to the file: " + e.getMessage());
        }


    }

    public void addToDatabase() {
        try {
            this.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    public String searchDatabase(String timeToCheck) {

        String fileName = "database.txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String[] elements = line.split(";");
                    
                    String timeElement = elements[4];

                    if (timeElement.contains(timeToCheck)) {
                        return line;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                        
        } else {
            System.out.println("There is no database when you initiate the program");              
        }
        return null;
    }

    public void moveToSecondFile(String lineToExclude) {
        String fileName = "database.txt";
        String secondFileName = "database2.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(secondFileName))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {

                if (!currentLine.equals(lineToExclude)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToFirstFile() {
        String fileName = "database.txt";
        String secondFileName = "database2.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(secondFileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                writer.write(currentLine);
                writer.newLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllContentSecondFile() {
        String secondFileName = "database2.txt";

        try {
            FileWriter writer = new FileWriter(secondFileName);
            writer.write("");
            writer.close();

        } catch (Exception e) {

        }
    }

//map key->service name, value cost service, 3 maps for each vehicle

    public int getPrice(String service, String vehiclekind) {
        int cost;
        String combined = service + "/" + vehiclekind;

        switch (combined) {
//Car
            case "Exterior Wash/Car":
                cost = 7;
                break;
            case "Interior Wash/Car":
                cost = 6;
                break;
            case "Exterior(+)Interior/Car":
                cost = 12;
                break;
            case "Special Exterior Wash/Car":
                cost = 9;
                break;
            case "Special Interior Wash/Car":
                cost = 8;
                break;
            case "Special Exterior(+)Interior/Car":
                cost = 15;
                break;
            case "Biological Interior Cleaning/Car":
                cost = 80;
                break;
            case "Waxing-Polishing/Car":
                cost = 80;
                break;
            case "Engine Cleaning/Car":
                cost = 20;
                break;
            case "Chassis Wash/Car":
                cost = 3;
                break;
//Jeep
            case "Exterior Wash/Jeep":
                cost = 8;
                break;
            case "Interior Wash/Jeep":
                cost = 7;
                break;
            case "Exterior(+)Interior/Jeep":
                cost = 14;
                break;
            case "Special Exterior Wash/Jeep":
                cost = 10;
                break;
            case "Special Interior Wash/Jeep":
                cost = 9;
                break;
            case "Special Exterior(+)Interior/Jeep":
                cost = 17;
                break;
            case "Biological Interior Cleaning/Jeep":
                cost = 80;
                break;
            case "Waxing-Polishing/Jeep":
                cost = 90;
                break;
            case "Engine Cleaning/Jeep":
                cost = 20;
                break;
            case "Chassis Wash/Jeep":
                cost = 3;
                break;
//MotorCycle
            case "Exterior Wash/MotorCycle":
                cost = 6;
                break;
            case "Special Exterior Wash/MotorCycle":
                cost = 8;
                break;
            case "Waxing-Polishing/MotorCycle":
                cost = 40;
                break;
            case "Engine Cleaning/MotorCycle":
                cost = 10;
                break;
                
            default:
                cost = 0;
                break;
        }
        return cost;
    }
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
