<<<<<<< HEAD
package gr.uop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Server extends Application {

    private static IncomeBook vehicleInfo;
    private static ObservableList<VehicleInfo> data;

    @Override
    public void start(Stage stage) {

        TableViewCreation tableView = new TableViewCreation();
        Node tableViewNode = tableView.createTableView();

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(tableViewNode);

        HBox buttonsHBox = new HBox();
        borderPane.setBottom(buttonsHBox);

        Button removeButton = new Button("Remove Vehicle");
        removeButton.setPrefHeight(40);
        removeButton.setDisable(false);

        removeButton.setOnAction(event -> {
            TableView<VehicleInfo> tableViewtemp = tableView.getTableView();
            VehicleInfo selectedVehicle = tableViewtemp.getSelectionModel().getSelectedItem();

            if (selectedVehicle != null) {
                String time = selectedVehicle.getTime();
                String line = vehicleInfo.searchDatabase(time);

                vehicleInfo.moveToSecondFile(line);
                vehicleInfo.moveToFirstFile();
                vehicleInfo.deleteAllContentSecondFile();

                data.remove(selectedVehicle); 

            } else {
                System.out.println("No row selected");
            }
        });


        Button receiptButton = new Button("Print Receipt");
        receiptButton.setPrefHeight(40);
        receiptButton.setDisable(false);

        receiptButton.setOnAction(event -> {
            TableView<VehicleInfo> tableViewtemp = tableView.getTableView();
            VehicleInfo selectedVehicle = tableViewtemp.getSelectionModel().getSelectedItem();

            if (selectedVehicle != null) {
                String time = selectedVehicle.getTime();
                String line = vehicleInfo.searchDatabase(time);

                String[] lineArray = line.split(";");

                String licencePlate = lineArray[0];
                licencePlate = licencePlate.substring(1);

                String vehiclekind = lineArray[1];
                vehiclekind = vehiclekind.substring(1);

                String cost = lineArray[2];
                cost = cost.substring(1);

                String services = lineArray[3];

                InfoPopup popup = new InfoPopup(vehicleInfo, licencePlate, vehiclekind, services, cost, time);

                data.remove(selectedVehicle);     
                

            } else {
                System.out.println("No row selected");
            }
        });

       
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                receiptButton.getOnAction().handle(new ActionEvent());          
            }
        });

        buttonsHBox.getChildren().addAll(removeButton, receiptButton);
        buttonsHBox.setPrefHeight(40);

        buttonsHBox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double buttonsHBoxWidth = newValue.doubleValue();
                int buttonWidthPercentage = 50;
                double buttonWidhth = (buttonsHBoxWidth * buttonWidthPercentage) / 100.0;

                removeButton.setPrefWidth(buttonWidhth);
                receiptButton.setPrefWidth(buttonWidhth);
            }
        });

        var scene = new Scene(borderPane, 1024, 768);

        new Thread(() -> startServer(tableView)).start();

        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);

        stage.setScene(scene);
        stage.show();
    }


    private void startServer(TableViewCreation tableView) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {

            String fileName = "database.txt";
            File file = new File(fileName);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String lastThreeChars = line.substring(line.length() - 3);

                        if (lastThreeChars.contains("NO")) {
                            String[] lineArray = line.split(";");

                            String licencePlate = lineArray[0];
                            String cost = lineArray[2];
                            String currentTime = lineArray[4];

                            data = tableView.addVehicleInfo(licencePlate, cost, currentTime);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                            
            } else {
                System.out.println("There is no database when you initiate the program");              
            }

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket, tableView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, TableViewCreation tableView) {
        try (DataInputStream inObject = new DataInputStream(clientSocket.getInputStream())) {
            
            String licencePlate;
            String cost;
            String vehiclekind;
            String servicesString;
            String currentTime;
            ArrayList<String> services;    

            CurrentDateTime currentDateTime = new CurrentDateTime();
            currentTime = currentDateTime.GetCurrentDateTime();
            System.out.println("Current time (server side): " + currentTime);

            licencePlate = inObject.readUTF();
            cost = inObject.readUTF();
            vehiclekind = inObject.readUTF();
            servicesString = inObject.readUTF();

            String[] servicesArray = servicesString.split(",");
            services = new ArrayList<>(Arrays.asList(servicesArray));

            System.out.println("Licence plate: " + licencePlate);
            System.out.println("Cost: " + cost);
            System.out.println("Vehicle kind: " + vehiclekind);
            System.out.println("Services: " + services);

            vehicleInfo = new IncomeBook(licencePlate, cost, vehiclekind, currentTime, services);
        
            data = tableView.addVehicleInfo(licencePlate, cost, currentTime); //adds the vehicle plate, cost and time in the tableview

            String combinedString = String.join("; ", licencePlate, vehiclekind, cost, String.join(", ", services), currentTime, "NO");


            String fileName = "database.txt";
            String secondFileName = "datebase2.txt";
            File file = new File(fileName);
            File secondFile = new File(secondFileName);

            if (file.exists()) {
                            
            } else {
                vehicleInfo.createFile();
            }

            if (secondFile.exists()) {

            } else {
                vehicleInfo.createSecondFile();
            }

            vehicleInfo.addToFile(combinedString);

        } catch (IOException e) {
            e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
     
    }

}
=======
package gr.uop;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Server extends Application {

    private static IncomeBook vehicleInfo;
    private static ObservableList<VehicleInfo> data;

    @Override
    public void start(Stage stage) {

        TableViewCreation tableView = new TableViewCreation();
        Node tableViewNode = tableView.createTableView();

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(tableViewNode);

        HBox buttonsHBox = new HBox();
        borderPane.setBottom(buttonsHBox);

        Button removeButton = new Button("Remove Vehicle");
        removeButton.setPrefHeight(40);
        removeButton.setDisable(false);

        removeButton.setOnAction(event -> {
            TableView<VehicleInfo> tableViewtemp = tableView.getTableView();
            VehicleInfo selectedVehicle = tableViewtemp.getSelectionModel().getSelectedItem();

            if (selectedVehicle != null) {
                String time = selectedVehicle.getTime();
                String line = vehicleInfo.searchDatabase(time);

                vehicleInfo.moveToSecondFile(line);
                vehicleInfo.moveToFirstFile();
                vehicleInfo.deleteAllContentSecondFile();

                data.remove(selectedVehicle); 

            } else {
                System.out.println("No row selected");
            }
        });


        Button receiptButton = new Button("Print Receipt");
        receiptButton.setPrefHeight(40);
        receiptButton.setDisable(false);

        receiptButton.setOnAction(event -> {
            TableView<VehicleInfo> tableViewtemp = tableView.getTableView();
            VehicleInfo selectedVehicle = tableViewtemp.getSelectionModel().getSelectedItem();

            if (selectedVehicle != null) {
                String time = selectedVehicle.getTime();
                String line = vehicleInfo.searchDatabase(time);

                String[] lineArray = line.split(";");

                String licencePlate = lineArray[0];
                licencePlate = licencePlate.substring(1);

                String vehiclekind = lineArray[1];
                vehiclekind = vehiclekind.substring(1);

                String cost = lineArray[2];
                cost = cost.substring(1);

                String services = lineArray[3];

                InfoPopup popup = new InfoPopup(vehicleInfo, licencePlate, vehiclekind, services, cost, time);

                data.remove(selectedVehicle);     
                

            } else {
                System.out.println("No row selected");
            }
        });

       
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                receiptButton.getOnAction().handle(new ActionEvent());          
            }
        });

        buttonsHBox.getChildren().addAll(removeButton, receiptButton);
        buttonsHBox.setPrefHeight(40);

        buttonsHBox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double buttonsHBoxWidth = newValue.doubleValue();
                int buttonWidthPercentage = 50;
                double buttonWidhth = (buttonsHBoxWidth * buttonWidthPercentage) / 100.0;

                removeButton.setPrefWidth(buttonWidhth);
                receiptButton.setPrefWidth(buttonWidhth);
            }
        });

        var scene = new Scene(borderPane, 1024, 768);

        new Thread(() -> startServer(tableView)).start();

        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);

        stage.setScene(scene);
        stage.show();
    }


    private void startServer(TableViewCreation tableView) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {

            String fileName = "database.txt";
            File file = new File(fileName);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String lastThreeChars = line.substring(line.length() - 3);

                        if (lastThreeChars.contains("NO")) {
                            String[] lineArray = line.split(";");

                            String licencePlate = lineArray[0];
                            String cost = lineArray[2];
                            String currentTime = lineArray[4];

                            data = tableView.addVehicleInfo(licencePlate, cost, currentTime);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                            
            } else {
                System.out.println("There is no database when you initiate the program");              
            }

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket, tableView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket, TableViewCreation tableView) {
        try (DataInputStream inObject = new DataInputStream(clientSocket.getInputStream())) {
            
            String licencePlate;
            String cost;
            String vehiclekind;
            String servicesString;
            String currentTime;
            ArrayList<String> services;    

            CurrentDateTime currentDateTime = new CurrentDateTime();
            currentTime = currentDateTime.GetCurrentDateTime();
            System.out.println("Current time (server side): " + currentTime);

            licencePlate = inObject.readUTF();
            cost = inObject.readUTF();
            vehiclekind = inObject.readUTF();
            servicesString = inObject.readUTF();

            String[] servicesArray = servicesString.split(",");
            services = new ArrayList<>(Arrays.asList(servicesArray));

            System.out.println("Licence plate: " + licencePlate);
            System.out.println("Cost: " + cost);
            System.out.println("Vehicle kind: " + vehiclekind);
            System.out.println("Services: " + services);

            vehicleInfo = new IncomeBook(licencePlate, cost, vehiclekind, currentTime, services);
        
            data = tableView.addVehicleInfo(licencePlate, cost, currentTime); //adds the vehicle plate, cost and time in the tableview

            String combinedString = String.join("; ", licencePlate, vehiclekind, cost, String.join(", ", services), currentTime, "NO");


            String fileName = "database.txt";
            String secondFileName = "datebase2.txt";
            File file = new File(fileName);
            File secondFile = new File(secondFileName);

            if (file.exists()) {
                            
            } else {
                vehicleInfo.createFile();
            }

            if (secondFile.exists()) {

            } else {
                vehicleInfo.createSecondFile();
            }

            vehicleInfo.addToFile(combinedString);

        } catch (IOException e) {
            e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
     
    }

}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
