<<<<<<< HEAD
package gr;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import gr.uop.CurrentDateTime;
import gr.uop.Sender;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//**A Catalog with the availbale services the User can choose from**
public class DirectoryServices extends Dialog<Void> {
 
  private Label licensePlateLabel;
  
    public DirectoryServices(Label licensePlateLabel)
    {
       this.licensePlateLabel = licensePlateLabel;
       //**Containers**  
        //Setting Directory Services Pane:
        StackPane stackpane = new StackPane();
           //Panes PrefDimensions
          // stackpane.setMinWidth(560);
          // stackpan.setMinHeight(500);
          // stackpane.setPrefWidth(560);
          // stackpane.setPrefHeight(500);
        
        //**Contents** of Direcotry Services Pane   
          //Seperators:
           Separator separator1 = new Separator();
           Separator separator2 = new Separator();
           Separator separator3 = new Separator();
          //Categorizing the Kinds of Vehicles Services are being applies to
           //Labels:
           String[] vehicles = {"Car", "Jeep", "MotorCycle"};
          //Available Services:
           //Radio Buttons:
           String[] options = {"Exterior Wash","Interior Wash","Exterior(+)Interior","Special Exterior Wash","Special Interior Wash",
                               "Special Exterior(+)Interior","Biological Interior Cleaning","Waxing-Polishing","Engine Cleaning","Chassis Wash"};
          //RadioButton option1  = new RadioButton("Exterior Wash");
        
          //Cost of each service by vehicle:
          //Labels:
          String[][] costs={ {"7","8","6"},
                             {"6","7","-"},
                             {"12","14","-"},
                             {"9","10","8"},
                             {"8","9","-"},
                             {"15","17","-"},
                             {"80","80","-"},
                             {"80","90","40"},
                             {"20","20","10"},
                             {"3","3","-"}
                          };

        //Grid Pane (For the Categories)
        GridPane categoriesGridPane = new GridPane();
           ArrayList<ToggleButton> categoriesList = new ArrayList<>();
           String vehicle = ""; //What kind of vehicle the User selected.We will pass the value to this variable 
                                //through the field of Vehicle objects field that contains the kind of vehicle
                     
        //VBox (Total Container)                  
        VBox vbox = new VBox();   
           vbox.getChildren().add(separator1);
           vbox.getChildren().add(categoriesGridPane);
           vbox.getChildren().add(separator2);

        //Grid Pane (Contains all the available Service Options)
        GridPane gridPane = new GridPane();
           gridPane.setVgap(10);
           ArrayList<RadioButton> servicesList = new ArrayList<>();//Contains all the Childs of Grid Pane that represent the Available Services (RadioButtons)  
           
        //Hbox (Contains the TextField which shows the total cost)
        HBox hBox = new HBox();
           //Children
           Label totalcostlabel = new Label("Cost :");
                //Custom Font
                Font customFont = new Font("Arial", 24);
                totalcostlabel.setFont(customFont);
                Label calculatecosLabel   = new Label("0");
                calculatecosLabel.setFont(customFont);      
                //Adding them into the HBox
                hBox.getChildren().addAll(totalcostlabel,calculatecosLabel);
                hBox.setMargin(calculatecosLabel, new Insets(0,0 ,0 ,50 ));

        //**Buttons**
        Button okButton = new Button("OK");
        okButton.setDisable(true);
        Button cancelButton = new Button("Cancel");
        cancelButton.setDisable(true);

         // Create a ButtonBar with OK and Cancel buttons
        ButtonBar buttonBar = new ButtonBar();
            buttonBar.getButtons().addAll(okButton, cancelButton);
        HBox hBox2 = new HBox(buttonBar);                          //Placing the ButtonBar inside an HBox
            hBox2.setAlignment(Pos.BOTTOM_RIGHT);


            VehicleInfo vehiclesbuttons = new VehicleInfo(servicesList,calculatecosLabel,this.licensePlateLabel,categoriesList);
            MyServicesHandler myserviceshandler = new MyServicesHandler(servicesList, calculatecosLabel, vehiclesbuttons, costs, options, okButton, cancelButton);    

            //ColumnConstraints (For Categories)
            for (int i = 0; i < 4; i++) {
              ColumnConstraints columnconstraints = new ColumnConstraints();
                if (i == 0) {
                  columnconstraints.setPercentWidth(50);
                } else {
                  columnconstraints.setPercentWidth(20);
                }
                 categoriesGridPane.getColumnConstraints().add(columnconstraints);
            }

            //Adding the Elements to the GridPane
            for (int i = 0; i < 4;  i++) {
                if (i == 0) {
                    Label categorylabel = new Label("");
                    categoriesGridPane.add(categorylabel,i,0);
                } else {
                    ToggleButton categorybutton = new ToggleButton(vehicles[i-1]);
                    //Adding them to the correspondent ArrayList
                    categoriesList.add(categorybutton);
                    //Vehicle vehiclesbuttons = new Vehicle(servicesList);
                    //Action
                    categorybutton.setOnAction(vehiclesbuttons);
                    //Style
                    categorybutton.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                    // Set a style when the mouse hovers over the button
                    categorybutton.setOnMouseEntered(e -> {
                    categorybutton.setStyle("-fx-background-color: lightgray; -fx-border-width: 1; -fx-border-color: black;");
                    });

                    // Set a style when the mouse exits the button
                    categorybutton.setOnMouseExited(e -> {
                    categorybutton.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                    });

                    categoriesGridPane.add(categorybutton,i,0);
                    categoriesGridPane.setVgrow(categorybutton, Priority.ALWAYS);
                }
            }
            
            //ColumnConstraints (For Services)
            for (int i = 0; i < 4; i++) {
                ColumnConstraints columnconstraints = new ColumnConstraints();
                if (i == 0) {
                    columnconstraints.setPercentWidth(50);
                } else {
                    columnconstraints.setPercentWidth(20);
                }
  
                gridPane.getColumnConstraints().add(columnconstraints);
            }
            //RowConstraints(For Services)
            for (int i = 0; i < 10; i++) {
                RowConstraints rowconstraints = new RowConstraints();
                rowconstraints.setVgrow(Priority.ALWAYS);
                gridPane.getRowConstraints().add(rowconstraints);
            }

            //Adding the Elements to the GridPane
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 4; j++) {
                    if (j == 0) { //RadioButton
                        RadioButton servicerdbutton = new RadioButton(options[i]);  
                        servicerdbutton.setDisable(true);//In the beggining the services will be Disabled until the user selectes a Vehicle
                        gridPane.add(servicerdbutton,j,i);
                        servicesList.add(servicerdbutton);//Adding them to the list so we can modify them later 
                        //MyServicesHandler myserviceshandler = new MyServicesHandler(servicesList, calculatecosLabel);
                        servicerdbutton.setOnAction(myserviceshandler);
                    }    
                    if (j > 0) {
                        Label costlabel = new Label(costs[i][j-1]);
                        gridPane.add(costlabel,j,i);
                    }
                }
            }

        //**Final** Pane (Total)
        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(separator3);
        vbox.getChildren().add(hBox);
        vbox.setSpacing(10);
        stackpane.getChildren().add(vbox);
 
        BorderPane borderPane = new BorderPane();
          borderPane.setCenter(stackpane);
          borderPane.setBottom(hBox2);;
        //**Pane**
        getDialogPane().setContent(borderPane);
        
        //**Button Functionality**
        okButton.setOnAction((e) -> {
          Alert alert = new Alert(AlertType.CONFIRMATION);

          alert.setTitle("Confirmation");
          alert.setContentText("Do you want to send this information to the Cash Desk ?");
          alert.setHeaderText("Services Information");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //System.out.println("OK");
                alert.close();
                // Close the parent dialog
                getDialogPane().getScene().getWindow().hide();
          
                //**Get the current date and time**
                //CurrentDateTime currentDateTime = new CurrentDateTime();
                //vehiclesbuttons.setcurrentDateTime(currentDateTime.GetCurrentDateTime()); 
                //**Sending the Vehicle Informaton to the Server(CashDesk)**:
                Sender sender = new Sender();
                // Establish the connection
                //sender.establishConnection();
                // Use the sender to send the object to the server
                sender.sendData(vehiclesbuttons.getLicensePlateLabel(),vehiclesbuttons.getCalculatecosLabel(),vehiclesbuttons.getVehicleKind()/*,vehiclesbuttons.getCurrentDateTime()*/,vehiclesbuttons.getServices());
                sender.closeConnection(); 

                // Close the connection when done
                // ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                //     scheduler.schedule(() -> {
                //     sender.closeConnection(); 
                // }, 5, TimeUnit.SECONDS);
            }
            else if (result.get() == ButtonType.CANCEL) {
                //System.out.println("Cancel");
                alert.close();
            }
            else if (result.get() == ButtonType.CLOSE) {
                //System.out.println("Close");
                alert.close();
            }
        });

        cancelButton.setOnAction((e) -> {
          Alert alert = new Alert(AlertType.WARNING);

          alert.setTitle("Warning");
          alert.setContentText("Are you sure you want to cancel the process?\nCurrent data will be lost");
          alert.setHeaderText("Service Selection Process");

          alert.getButtonTypes().add(ButtonType.CANCEL);

           Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //System.out.println("OK");
                alert.close();
                // Close the parent dialog
                getDialogPane().getScene().getWindow().hide();
            }
            else if (result.get() == ButtonType.CANCEL) {
                //System.out.println("Cancel");
                alert.close();
            }
            else if (result.get() == ButtonType.CLOSE) {
                //System.out.println("Close");
                alert.close();
            }
        });

        //ButtonTypes
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);          //Making the button Invisible

        //Scenes Dimensions
        getDialogPane().setMinWidth(560);
        getDialogPane().setMinHeight(500);
        setWidth(560);
        setHeight(500);

        // Make the dialog resizable
        // Stage stage = (Stage) getDialogPane().getScene().getWindow();
        // stage.setResizable(true);

        //Title
        setTitle("Directory Services");

        showAndWait(); 
    }

}
=======
package gr;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import gr.uop.CurrentDateTime;
import gr.uop.Sender;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//**A Catalog with the availbale services the User can choose from**
public class DirectoryServices extends Dialog<Void> {
 
  private Label licensePlateLabel;
  
    public DirectoryServices(Label licensePlateLabel)
    {
       this.licensePlateLabel = licensePlateLabel;
       //**Containers**  
        //Setting Directory Services Pane:
        StackPane stackpane = new StackPane();
           //Panes PrefDimensions
          // stackpane.setMinWidth(560);
          // stackpan.setMinHeight(500);
          // stackpane.setPrefWidth(560);
          // stackpane.setPrefHeight(500);
        
        //**Contents** of Direcotry Services Pane   
          //Seperators:
           Separator separator1 = new Separator();
           Separator separator2 = new Separator();
           Separator separator3 = new Separator();
          //Categorizing the Kinds of Vehicles Services are being applies to
           //Labels:
           String[] vehicles = {"Car", "Jeep", "MotorCycle"};
          //Available Services:
           //Radio Buttons:
           String[] options = {"Exterior Wash","Interior Wash","Exterior(+)Interior","Special Exterior Wash","Special Interior Wash",
                               "Special Exterior(+)Interior","Biological Interior Cleaning","Waxing-Polishing","Engine Cleaning","Chassis Wash"};
          //RadioButton option1  = new RadioButton("Exterior Wash");
        
          //Cost of each service by vehicle:
          //Labels:
          String[][] costs={ {"7","8","6"},
                             {"6","7","-"},
                             {"12","14","-"},
                             {"9","10","8"},
                             {"8","9","-"},
                             {"15","17","-"},
                             {"80","80","-"},
                             {"80","90","40"},
                             {"20","20","10"},
                             {"3","3","-"}
                          };

        //Grid Pane (For the Categories)
        GridPane categoriesGridPane = new GridPane();
           ArrayList<ToggleButton> categoriesList = new ArrayList<>();
           String vehicle = ""; //What kind of vehicle the User selected.We will pass the value to this variable 
                                //through the field of Vehicle objects field that contains the kind of vehicle
                     
        //VBox (Total Container)                  
        VBox vbox = new VBox();   
           vbox.getChildren().add(separator1);
           vbox.getChildren().add(categoriesGridPane);
           vbox.getChildren().add(separator2);

        //Grid Pane (Contains all the available Service Options)
        GridPane gridPane = new GridPane();
           gridPane.setVgap(10);
           ArrayList<RadioButton> servicesList = new ArrayList<>();//Contains all the Childs of Grid Pane that represent the Available Services (RadioButtons)  
           
        //Hbox (Contains the TextField which shows the total cost)
        HBox hBox = new HBox();
           //Children
           Label totalcostlabel = new Label("Cost :");
                //Custom Font
                Font customFont = new Font("Arial", 24);
                totalcostlabel.setFont(customFont);
                Label calculatecosLabel   = new Label("0");
                calculatecosLabel.setFont(customFont);      
                //Adding them into the HBox
                hBox.getChildren().addAll(totalcostlabel,calculatecosLabel);
                hBox.setMargin(calculatecosLabel, new Insets(0,0 ,0 ,50 ));

        //**Buttons**
        Button okButton = new Button("OK");
        okButton.setDisable(true);
        Button cancelButton = new Button("Cancel");
        cancelButton.setDisable(true);

         // Create a ButtonBar with OK and Cancel buttons
        ButtonBar buttonBar = new ButtonBar();
            buttonBar.getButtons().addAll(okButton, cancelButton);
        HBox hBox2 = new HBox(buttonBar);                          //Placing the ButtonBar inside an HBox
            hBox2.setAlignment(Pos.BOTTOM_RIGHT);


            VehicleInfo vehiclesbuttons = new VehicleInfo(servicesList,calculatecosLabel,this.licensePlateLabel,categoriesList);
            MyServicesHandler myserviceshandler = new MyServicesHandler(servicesList, calculatecosLabel, vehiclesbuttons, costs, options, okButton, cancelButton);    

            //ColumnConstraints (For Categories)
            for (int i = 0; i < 4; i++) {
              ColumnConstraints columnconstraints = new ColumnConstraints();
                if (i == 0) {
                  columnconstraints.setPercentWidth(50);
                } else {
                  columnconstraints.setPercentWidth(20);
                }
                 categoriesGridPane.getColumnConstraints().add(columnconstraints);
            }

            //Adding the Elements to the GridPane
            for (int i = 0; i < 4;  i++) {
                if (i == 0) {
                    Label categorylabel = new Label("");
                    categoriesGridPane.add(categorylabel,i,0);
                } else {
                    ToggleButton categorybutton = new ToggleButton(vehicles[i-1]);
                    //Adding them to the correspondent ArrayList
                    categoriesList.add(categorybutton);
                    //Vehicle vehiclesbuttons = new Vehicle(servicesList);
                    //Action
                    categorybutton.setOnAction(vehiclesbuttons);
                    //Style
                    categorybutton.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                    // Set a style when the mouse hovers over the button
                    categorybutton.setOnMouseEntered(e -> {
                    categorybutton.setStyle("-fx-background-color: lightgray; -fx-border-width: 1; -fx-border-color: black;");
                    });

                    // Set a style when the mouse exits the button
                    categorybutton.setOnMouseExited(e -> {
                    categorybutton.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                    });

                    categoriesGridPane.add(categorybutton,i,0);
                    categoriesGridPane.setVgrow(categorybutton, Priority.ALWAYS);
                }
            }
            
            //ColumnConstraints (For Services)
            for (int i = 0; i < 4; i++) {
                ColumnConstraints columnconstraints = new ColumnConstraints();
                if (i == 0) {
                    columnconstraints.setPercentWidth(50);
                } else {
                    columnconstraints.setPercentWidth(20);
                }
  
                gridPane.getColumnConstraints().add(columnconstraints);
            }
            //RowConstraints(For Services)
            for (int i = 0; i < 10; i++) {
                RowConstraints rowconstraints = new RowConstraints();
                rowconstraints.setVgrow(Priority.ALWAYS);
                gridPane.getRowConstraints().add(rowconstraints);
            }

            //Adding the Elements to the GridPane
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 4; j++) {
                    if (j == 0) { //RadioButton
                        RadioButton servicerdbutton = new RadioButton(options[i]);  
                        servicerdbutton.setDisable(true);//In the beggining the services will be Disabled until the user selectes a Vehicle
                        gridPane.add(servicerdbutton,j,i);
                        servicesList.add(servicerdbutton);//Adding them to the list so we can modify them later 
                        //MyServicesHandler myserviceshandler = new MyServicesHandler(servicesList, calculatecosLabel);
                        servicerdbutton.setOnAction(myserviceshandler);
                    }    
                    if (j > 0) {
                        Label costlabel = new Label(costs[i][j-1]);
                        gridPane.add(costlabel,j,i);
                    }
                }
            }

        //**Final** Pane (Total)
        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(separator3);
        vbox.getChildren().add(hBox);
        vbox.setSpacing(10);
        stackpane.getChildren().add(vbox);
 
        BorderPane borderPane = new BorderPane();
          borderPane.setCenter(stackpane);
          borderPane.setBottom(hBox2);;
        //**Pane**
        getDialogPane().setContent(borderPane);
        
        //**Button Functionality**
        okButton.setOnAction((e) -> {
          Alert alert = new Alert(AlertType.CONFIRMATION);

          alert.setTitle("Confirmation");
          alert.setContentText("Do you want to send this information to the Cash Desk ?");
          alert.setHeaderText("Services Information");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //System.out.println("OK");
                alert.close();
                // Close the parent dialog
                getDialogPane().getScene().getWindow().hide();
          
                //**Get the current date and time**
                //CurrentDateTime currentDateTime = new CurrentDateTime();
                //vehiclesbuttons.setcurrentDateTime(currentDateTime.GetCurrentDateTime()); 
                //**Sending the Vehicle Informaton to the Server(CashDesk)**:
                Sender sender = new Sender();
                // Establish the connection
                //sender.establishConnection();
                // Use the sender to send the object to the server
                sender.sendData(vehiclesbuttons.getLicensePlateLabel(),vehiclesbuttons.getCalculatecosLabel(),vehiclesbuttons.getVehicleKind()/*,vehiclesbuttons.getCurrentDateTime()*/,vehiclesbuttons.getServices());
                sender.closeConnection(); 

                // Close the connection when done
                // ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                //     scheduler.schedule(() -> {
                //     sender.closeConnection(); 
                // }, 5, TimeUnit.SECONDS);
            }
            else if (result.get() == ButtonType.CANCEL) {
                //System.out.println("Cancel");
                alert.close();
            }
            else if (result.get() == ButtonType.CLOSE) {
                //System.out.println("Close");
                alert.close();
            }
        });

        cancelButton.setOnAction((e) -> {
          Alert alert = new Alert(AlertType.WARNING);

          alert.setTitle("Warning");
          alert.setContentText("Are you sure you want to cancel the process?\nCurrent data will be lost");
          alert.setHeaderText("Service Selection Process");

          alert.getButtonTypes().add(ButtonType.CANCEL);

           Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //System.out.println("OK");
                alert.close();
                // Close the parent dialog
                getDialogPane().getScene().getWindow().hide();
            }
            else if (result.get() == ButtonType.CANCEL) {
                //System.out.println("Cancel");
                alert.close();
            }
            else if (result.get() == ButtonType.CLOSE) {
                //System.out.println("Close");
                alert.close();
            }
        });

        //ButtonTypes
        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);          //Making the button Invisible

        //Scenes Dimensions
        getDialogPane().setMinWidth(560);
        getDialogPane().setMinHeight(500);
        setWidth(560);
        setHeight(500);

        // Make the dialog resizable
        // Stage stage = (Stage) getDialogPane().getScene().getWindow();
        // stage.setResizable(true);

        //Title
        setTitle("Directory Services");

        showAndWait(); 
    }

}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
