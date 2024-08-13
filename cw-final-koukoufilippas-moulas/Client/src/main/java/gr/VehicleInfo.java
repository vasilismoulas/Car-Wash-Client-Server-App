<<<<<<< HEAD
package gr;

import java.io.Serializable; //This is necessary to indicate that objects of this class can be serialized.
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

//**It is connected with the SetOnAction of the Buttons(Kind of Vehicle)**
public class VehicleInfo implements EventHandler<ActionEvent>,Serializable{
   
    private Label licensePlateLabel;
    private Label calculatecosLabel;
    private ArrayList<RadioButton> servicesList;     //All the Services/RadioButtons
    private ArrayList<ToggleButton> categoriesList;  //Contains all the Categories 
    private String VehiclesKind;
    private boolean isSelected = false;
    private String currentDateTime;

    public VehicleInfo(ArrayList<RadioButton> servicesList,Label calculatecosLabel,Label licensePlateLabel,ArrayList<ToggleButton> categoriesList){
        this.servicesList = servicesList;
        this.calculatecosLabel = calculatecosLabel;
        this.licensePlateLabel = licensePlateLabel;
        this.categoriesList = categoriesList;
    }

    public String getVehicleKind() {
       return VehiclesKind;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public String getLicensePlateLabel() {
       return  licensePlateLabel.getText();
    }

    public String getCalculatecosLabel() {
       return calculatecosLabel.getText();
    }

    public ArrayList<String> getServices() {
        ArrayList<String> services = new ArrayList<>();
        for (int i = 0; i < servicesList.size(); i++) {
            if(servicesList.get(i).isSelected())
            services.add(servicesList.get(i).getText());
        }
        return services;
    }

    public void setcurrentDateTime(String currentDateTime) {
        this.currentDateTime=currentDateTime;
    }

    @Override
    public void handle(ActionEvent event) {
        // Get the source of the event, which is the clicked/unclicked button
        ToggleButton selectedvehicle = (ToggleButton) event.getSource();
      
        this.VehiclesKind = selectedvehicle.getText();//The Kind of Vehicle the User selected
         
        //System.out.println(licensePlateLabel.getText()+"\n");
        //Enabling Services as the User selected a Vehicle
        if (this.isSelected == false) {
            this.isSelected = true;
            // System.out.println(VehiclesKind+" got selected!");

        } else {
            this.isSelected = false;
            // System.out.println(VehiclesKind+" got unselected!");
        }

        int[] motorCaseEnable = {0, 3, 7, 8};

        if (this.isSelected == true) {
            if (VehiclesKind != "MotorCycle") {
                for (int i = 0; i < servicesList.size(); i++) {
                    servicesList.get(i).setDisable(false);
                }
            } else {
                for (int index : motorCaseEnable) {
                    servicesList.get(index).setDisable(false);
                }
            }
            for (int i = 0; i < categoriesList.size(); i++) {
              if (categoriesList.get(i).getText() != VehiclesKind) {
                  categoriesList.get(i).setDisable(true);
              }
            }
        } else {
            this.VehiclesKind = "none";
            
            for (int i = 0; i < servicesList.size(); i++) {
                servicesList.get(i).setDisable(true);
                if (servicesList.get(i).isSelected()) {
                    servicesList.get(i).setSelected(false);
                    calculatecosLabel.setText("0");
                }
            }
            for (int i = 0; i < categoriesList.size(); i++) {
                if (categoriesList.get(i).getText()!= VehiclesKind) {
                    categoriesList.get(i).setDisable(false);
                }
            }
        }
    }
}
=======
package gr;

import java.io.Serializable; //This is necessary to indicate that objects of this class can be serialized.
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

//**It is connected with the SetOnAction of the Buttons(Kind of Vehicle)**
public class VehicleInfo implements EventHandler<ActionEvent>,Serializable{
   
    private Label licensePlateLabel;
    private Label calculatecosLabel;
    private ArrayList<RadioButton> servicesList;     //All the Services/RadioButtons
    private ArrayList<ToggleButton> categoriesList;  //Contains all the Categories 
    private String VehiclesKind;
    private boolean isSelected = false;
    private String currentDateTime;

    public VehicleInfo(ArrayList<RadioButton> servicesList,Label calculatecosLabel,Label licensePlateLabel,ArrayList<ToggleButton> categoriesList){
        this.servicesList = servicesList;
        this.calculatecosLabel = calculatecosLabel;
        this.licensePlateLabel = licensePlateLabel;
        this.categoriesList = categoriesList;
    }

    public String getVehicleKind() {
       return VehiclesKind;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public String getLicensePlateLabel() {
       return  licensePlateLabel.getText();
    }

    public String getCalculatecosLabel() {
       return calculatecosLabel.getText();
    }

    public ArrayList<String> getServices() {
        ArrayList<String> services = new ArrayList<>();
        for (int i = 0; i < servicesList.size(); i++) {
            if(servicesList.get(i).isSelected())
            services.add(servicesList.get(i).getText());
        }
        return services;
    }

    public void setcurrentDateTime(String currentDateTime) {
        this.currentDateTime=currentDateTime;
    }

    @Override
    public void handle(ActionEvent event) {
        // Get the source of the event, which is the clicked/unclicked button
        ToggleButton selectedvehicle = (ToggleButton) event.getSource();
      
        this.VehiclesKind = selectedvehicle.getText();//The Kind of Vehicle the User selected
         
        //System.out.println(licensePlateLabel.getText()+"\n");
        //Enabling Services as the User selected a Vehicle
        if (this.isSelected == false) {
            this.isSelected = true;
            // System.out.println(VehiclesKind+" got selected!");

        } else {
            this.isSelected = false;
            // System.out.println(VehiclesKind+" got unselected!");
        }

        int[] motorCaseEnable = {0, 3, 7, 8};

        if (this.isSelected == true) {
            if (VehiclesKind != "MotorCycle") {
                for (int i = 0; i < servicesList.size(); i++) {
                    servicesList.get(i).setDisable(false);
                }
            } else {
                for (int index : motorCaseEnable) {
                    servicesList.get(index).setDisable(false);
                }
            }
            for (int i = 0; i < categoriesList.size(); i++) {
              if (categoriesList.get(i).getText() != VehiclesKind) {
                  categoriesList.get(i).setDisable(true);
              }
            }
        } else {
            this.VehiclesKind = "none";
            
            for (int i = 0; i < servicesList.size(); i++) {
                servicesList.get(i).setDisable(true);
                if (servicesList.get(i).isSelected()) {
                    servicesList.get(i).setSelected(false);
                    calculatecosLabel.setText("0");
                }
            }
            for (int i = 0; i < categoriesList.size(); i++) {
                if (categoriesList.get(i).getText()!= VehiclesKind) {
                    categoriesList.get(i).setDisable(false);
                }
            }
        }
    }
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
