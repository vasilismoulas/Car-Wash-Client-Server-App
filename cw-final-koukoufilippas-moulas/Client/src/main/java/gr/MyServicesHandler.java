<<<<<<< HEAD
package gr;
//error while submiting null values, jeep
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

//**It is connected with the SetOnAction of the RadioButtons(Available Services)**
public class MyServicesHandler implements EventHandler<ActionEvent> {
    
    private ArrayList<RadioButton> servicesList; //Passing all the Services/RadioButtons to the field of this class in order to handle them properly
    private Map<Integer, String> services = new HashMap<>();
    private ArrayList<RadioButton> selectedservices = new ArrayList<>();
    private Label calculatecosLabel;
    private VehicleInfo vehiclesbuttons;
    private String [][] costs;                   //Contains all the costs by vehicle
    // Create a HashMap to store services with hash codes
  
    private String [] options;                   //Contains all the services names

    int[] exteriorWashDisable = {2, 3, 5};
    int[] exteriorWashDisableMotor = {3};
    int[] interiorWashDisable = {2, 4, 5, 6};
    int[] exteriorAndInteriorDisable = {0, 1, 3, 4, 5, 6};
    int[] specialExteriorWashDisable = {0, 2, 5};
    int[] specialExteriorWashDisableMotor = {0};
    int[] specialInteriorWashDisable = {1, 2, 5, 6};
    int[] specialExteriorAndInteriorDisable = {0, 1, 2, 3, 4, 6};
    int[] biologicalInteriorCleaningDisable = {1, 2, 4, 5};

    int counter = 0; //to enable (=2)/disable the OK/Cancel buttons in the popup
    Boolean popupFlag = false; //when true, OK/Cancel get enabled

    private Button okButton;
    private Button cancelButton;

    public void enableDisableButtonsFlag() {
        if (counter >= 1) {
            popupFlag = true;
            okButton.setDisable(false);
            cancelButton.setDisable(false);
        } else {
            popupFlag = false;
            okButton.setDisable(true);
            cancelButton.setDisable(true);
        }
    }


    private void disablingServicesCI(Boolean selectedRadioBoolean, int[] disableArray) {
        if (selectedRadioBoolean) {
            for (int index : disableArray) {
                servicesList.get(index).setDisable(true);
            }
        }
    }
    
    public void CombinationImposing(ArrayList<RadioButton> selectedservices) { //In this function after we deselect/unselect one of the radiobuttons we may Enable one rbutton that is supposed to be disabled cause of another selected button.
                                                                               //In other words we consider the combinations of selected/unselected redio buttons
        for (int j = 0; j < selectedservices.size(); j++) {

            String selectedService = selectedservices.get(j).getText();
            Boolean selectedRadioBoolean = selectedservices.get(j).isSelected();

            switch (selectedService){
                case "Exterior Wash":
                    disablingServicesCI(selectedRadioBoolean, exteriorWashDisable);
                    break;
                case "Interior Wash":
                    disablingServicesCI(selectedRadioBoolean, interiorWashDisable);
                    break;
                case "Exterior(+)Interior":
                    disablingServicesCI(selectedRadioBoolean, exteriorAndInteriorDisable);
                    break;
                case "Special Exterior Wash":
                    disablingServicesCI(selectedRadioBoolean, specialExteriorWashDisable);
                    break;
                case "Special Interior Wash":
                    disablingServicesCI(selectedRadioBoolean, specialInteriorWashDisable);
                    break;
                case "Special Exterior(+)Interior":
                    disablingServicesCI(selectedRadioBoolean, specialExteriorAndInteriorDisable);
                    break;
                case "Biological Interior Cleaning":
                    disablingServicesCI(selectedRadioBoolean, biologicalInteriorCleaningDisable);
                    break;
                default:
                    break;
            }
        }
    }

    public MyServicesHandler(ArrayList<RadioButton> servicesList,Label calculatecosLabel,VehicleInfo vehiclesbuttons,String[][] costs,String[] options, Button okButton, Button cancelButton) {
        this.servicesList = servicesList;
        this.calculatecosLabel = calculatecosLabel; 
        this. vehiclesbuttons = vehiclesbuttons;  
        this.costs = costs;
        this.options = options;
        this.okButton = okButton;
        this.cancelButton = cancelButton;

        for (int i = 1; i <= this.options.length; i++) {
            services.put(i, options[i-1]);
        }

    }

    private void disablingServices(Boolean selectedRadioBoolean, int[] disableArray, RadioButton selectedRadioButton) {
        if (selectedRadioBoolean) {
            selectedservices.add(selectedRadioButton);
            for (int index : disableArray) {
                servicesList.get(index).setDisable(true);
            }
        } else {
            selectedservices.remove(selectedRadioButton);
            for (int index : disableArray) {
                servicesList.get(index).setDisable(false);
            }
            CombinationImposing(selectedservices);  
        }
    }

    private void disablingServicesMotor(Boolean selectedRadioBoolean, int[] disableArray, int[] disableArrayMotor, RadioButton selectedRadioButton, String vehicleKind) {
        if (selectedRadioBoolean) {
            selectedservices.add(selectedRadioButton);
            for (int index : disableArray) {
                if (servicesList.get(index).isSelected()) {
                    servicesList.get(index).setDisable(false);

                } else {
                    servicesList.get(index).setDisable(true);
                }
            }
        } else {
            selectedservices.remove(selectedRadioButton);
            if (vehicleKind != "MotorCycle") {
                for (int index : disableArray) {
                    servicesList.get(index).setDisable(false);
                }
            } else {
                for (int index : disableArrayMotor) {
                    servicesList.get(index).setDisable(false);
                }
            }
        }
        CombinationImposing(selectedservices);
    }

    @Override
    public void handle(ActionEvent event) {
        // Get the source of the event, which is the clicked radio button
        RadioButton selectedRadioButton = (RadioButton) event.getSource();

        String selectedService = selectedRadioButton.getText();
        Boolean selectedRadioBoolean = selectedRadioButton.isSelected();
        String vehicleKind = vehiclesbuttons.getVehicleKind();

        if (selectedRadioBoolean) {
            this.counter++;
        } else {
            this.counter--;
        }

        enableDisableButtonsFlag();
        
        switch (selectedService) {
            case "Exterior Wash":
                disablingServicesMotor(selectedRadioBoolean, exteriorWashDisable, exteriorWashDisableMotor, selectedRadioButton, vehicleKind);
                break;
            case "Interior Wash":
                disablingServices(selectedRadioBoolean, interiorWashDisable, selectedRadioButton);
                break;
            case "Exterior(+)Interior":
                disablingServices(selectedRadioBoolean, exteriorAndInteriorDisable, selectedRadioButton);
                break;
            case "Special Exterior Wash":
                disablingServicesMotor(selectedRadioBoolean, specialExteriorWashDisable, specialExteriorWashDisableMotor, selectedRadioButton, vehicleKind);
                break;
            case "Special Interior Wash":
                disablingServices(selectedRadioBoolean, specialInteriorWashDisable, selectedRadioButton);
                break;
            case "Special Exterior(+)Interior":
                disablingServices(selectedRadioBoolean, specialExteriorAndInteriorDisable, selectedRadioButton);
                break;
            case "Biological Interior Cleaning":
                disablingServices(selectedRadioBoolean, biologicalInteriorCleaningDisable, selectedRadioButton);
                break;
            case "Waxing-Polishing":
                if (selectedRadioBoolean) {
                    selectedservices.add(selectedRadioButton); 
                } else {
                    selectedservices.remove(selectedRadioButton);
                }
                break;
            case "Engine Cleaning":
                if (selectedRadioBoolean) {
                    selectedservices.add(selectedRadioButton); 
                } else {
                    selectedservices.remove(selectedRadioButton);
                }
                break;
            case "Chassis Wash":
                if (selectedRadioBoolean) {
                    selectedservices.add(selectedRadioButton); 
                } else {
                    selectedservices.remove(selectedRadioButton);
                } 
                break;
            default:
                break;
        }

        //**Calculating** the cost addition/substraction
        // Retrieving the appropriate service of the MAP based on what the user selected
        int hashCode = 0;
        String service;

        for (Map.Entry<Integer, String> entry : services.entrySet()) {
            hashCode = entry.getKey();
            service = entry.getValue();
            if (selectedRadioButton.getText() == service) {
            // System.out.println(selectedRadioButton.getText());
            break;
            }
        }

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs[i].length; j++){
                if (i == hashCode - 1) {
                    //Calculating the total cost based on the selected services and vehicle
                    if (vehiclesbuttons.getVehicleKind() == "Car") {
                        // System.out.println("car1"+vehiclesbuttons.getVehicleKind());
                        int service_cost = Integer.parseInt(costs[i][0]);
                        int oldtotalcost = Integer.parseInt(calculatecosLabel.getText());
                        int newtotalcost;
                        if (selectedRadioButton.isSelected()) {
                            newtotalcost = oldtotalcost + service_cost;
                        } else {
                            newtotalcost = oldtotalcost - service_cost;
                        }
                        calculatecosLabel.setText(Integer.toString(newtotalcost));
                        break;
                    } else if (vehiclesbuttons.getVehicleKind() == "Jeep") {
                        //System.out.println("car1"+vehiclesbuttons.getVehicleKind());
                        int service_cost = Integer.parseInt(costs[i][1]);
                        int oldtotalcost = Integer.parseInt(calculatecosLabel.getText());
                        int newtotalcost;
                        if (selectedRadioButton.isSelected()) {
                            newtotalcost = oldtotalcost + service_cost;
                        } else {
                            newtotalcost = oldtotalcost - service_cost;
                        }
                        calculatecosLabel.setText(Integer.toString(newtotalcost));
                        break;
                    } else if (vehiclesbuttons.getVehicleKind() == "MotorCycle") {
                        //System.out.println("car1"+vehiclesbuttons.getVehicleKind());
                        int service_cost = Integer.parseInt(costs[i][2]);
                        int oldtotalcost = Integer.parseInt(calculatecosLabel.getText());
                        int newtotalcost;
                        if (selectedRadioButton.isSelected()) {
                            newtotalcost = oldtotalcost + service_cost;
                        } else {
                            newtotalcost = oldtotalcost - service_cost;
                        }
                        calculatecosLabel.setText(Integer.toString(newtotalcost));
                        break;
                    }
                }
            }
        }  
    }
}
=======
package gr;
//error while submiting null values, jeep
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

//**It is connected with the SetOnAction of the RadioButtons(Available Services)**
public class MyServicesHandler implements EventHandler<ActionEvent> {
    
    private ArrayList<RadioButton> servicesList; //Passing all the Services/RadioButtons to the field of this class in order to handle them properly
    private Map<Integer, String> services = new HashMap<>();
    private ArrayList<RadioButton> selectedservices = new ArrayList<>();
    private Label calculatecosLabel;
    private VehicleInfo vehiclesbuttons;
    private String [][] costs;                   //Contains all the costs by vehicle
    // Create a HashMap to store services with hash codes
  
    private String [] options;                   //Contains all the services names

    int[] exteriorWashDisable = {2, 3, 5};
    int[] exteriorWashDisableMotor = {3};
    int[] interiorWashDisable = {2, 4, 5, 6};
    int[] exteriorAndInteriorDisable = {0, 1, 3, 4, 5, 6};
    int[] specialExteriorWashDisable = {0, 2, 5};
    int[] specialExteriorWashDisableMotor = {0};
    int[] specialInteriorWashDisable = {1, 2, 5, 6};
    int[] specialExteriorAndInteriorDisable = {0, 1, 2, 3, 4, 6};
    int[] biologicalInteriorCleaningDisable = {1, 2, 4, 5};

    int counter = 0; //to enable (=2)/disable the OK/Cancel buttons in the popup
    Boolean popupFlag = false; //when true, OK/Cancel get enabled

    private Button okButton;
    private Button cancelButton;

    public void enableDisableButtonsFlag() {
        if (counter >= 1) {
            popupFlag = true;
            okButton.setDisable(false);
            cancelButton.setDisable(false);
        } else {
            popupFlag = false;
            okButton.setDisable(true);
            cancelButton.setDisable(true);
        }
    }


    private void disablingServicesCI(Boolean selectedRadioBoolean, int[] disableArray) {
        if (selectedRadioBoolean) {
            for (int index : disableArray) {
                servicesList.get(index).setDisable(true);
            }
        }
    }
    
    public void CombinationImposing(ArrayList<RadioButton> selectedservices) { //In this function after we deselect/unselect one of the radiobuttons we may Enable one rbutton that is supposed to be disabled cause of another selected button.
                                                                               //In other words we consider the combinations of selected/unselected redio buttons
        for (int j = 0; j < selectedservices.size(); j++) {

            String selectedService = selectedservices.get(j).getText();
            Boolean selectedRadioBoolean = selectedservices.get(j).isSelected();

            switch (selectedService){
                case "Exterior Wash":
                    disablingServicesCI(selectedRadioBoolean, exteriorWashDisable);
                    break;
                case "Interior Wash":
                    disablingServicesCI(selectedRadioBoolean, interiorWashDisable);
                    break;
                case "Exterior(+)Interior":
                    disablingServicesCI(selectedRadioBoolean, exteriorAndInteriorDisable);
                    break;
                case "Special Exterior Wash":
                    disablingServicesCI(selectedRadioBoolean, specialExteriorWashDisable);
                    break;
                case "Special Interior Wash":
                    disablingServicesCI(selectedRadioBoolean, specialInteriorWashDisable);
                    break;
                case "Special Exterior(+)Interior":
                    disablingServicesCI(selectedRadioBoolean, specialExteriorAndInteriorDisable);
                    break;
                case "Biological Interior Cleaning":
                    disablingServicesCI(selectedRadioBoolean, biologicalInteriorCleaningDisable);
                    break;
                default:
                    break;
            }
        }
    }

    public MyServicesHandler(ArrayList<RadioButton> servicesList,Label calculatecosLabel,VehicleInfo vehiclesbuttons,String[][] costs,String[] options, Button okButton, Button cancelButton) {
        this.servicesList = servicesList;
        this.calculatecosLabel = calculatecosLabel; 
        this. vehiclesbuttons = vehiclesbuttons;  
        this.costs = costs;
        this.options = options;
        this.okButton = okButton;
        this.cancelButton = cancelButton;

        for (int i = 1; i <= this.options.length; i++) {
            services.put(i, options[i-1]);
        }

    }

    private void disablingServices(Boolean selectedRadioBoolean, int[] disableArray, RadioButton selectedRadioButton) {
        if (selectedRadioBoolean) {
            selectedservices.add(selectedRadioButton);
            for (int index : disableArray) {
                servicesList.get(index).setDisable(true);
            }
        } else {
            selectedservices.remove(selectedRadioButton);
            for (int index : disableArray) {
                servicesList.get(index).setDisable(false);
            }
            CombinationImposing(selectedservices);  
        }
    }

    private void disablingServicesMotor(Boolean selectedRadioBoolean, int[] disableArray, int[] disableArrayMotor, RadioButton selectedRadioButton, String vehicleKind) {
        if (selectedRadioBoolean) {
            selectedservices.add(selectedRadioButton);
            for (int index : disableArray) {
                if (servicesList.get(index).isSelected()) {
                    servicesList.get(index).setDisable(false);

                } else {
                    servicesList.get(index).setDisable(true);
                }
            }
        } else {
            selectedservices.remove(selectedRadioButton);
            if (vehicleKind != "MotorCycle") {
                for (int index : disableArray) {
                    servicesList.get(index).setDisable(false);
                }
            } else {
                for (int index : disableArrayMotor) {
                    servicesList.get(index).setDisable(false);
                }
            }
        }
        CombinationImposing(selectedservices);
    }

    @Override
    public void handle(ActionEvent event) {
        // Get the source of the event, which is the clicked radio button
        RadioButton selectedRadioButton = (RadioButton) event.getSource();

        String selectedService = selectedRadioButton.getText();
        Boolean selectedRadioBoolean = selectedRadioButton.isSelected();
        String vehicleKind = vehiclesbuttons.getVehicleKind();

        if (selectedRadioBoolean) {
            this.counter++;
        } else {
            this.counter--;
        }

        enableDisableButtonsFlag();
        
        switch (selectedService) {
            case "Exterior Wash":
                disablingServicesMotor(selectedRadioBoolean, exteriorWashDisable, exteriorWashDisableMotor, selectedRadioButton, vehicleKind);
                break;
            case "Interior Wash":
                disablingServices(selectedRadioBoolean, interiorWashDisable, selectedRadioButton);
                break;
            case "Exterior(+)Interior":
                disablingServices(selectedRadioBoolean, exteriorAndInteriorDisable, selectedRadioButton);
                break;
            case "Special Exterior Wash":
                disablingServicesMotor(selectedRadioBoolean, specialExteriorWashDisable, specialExteriorWashDisableMotor, selectedRadioButton, vehicleKind);
                break;
            case "Special Interior Wash":
                disablingServices(selectedRadioBoolean, specialInteriorWashDisable, selectedRadioButton);
                break;
            case "Special Exterior(+)Interior":
                disablingServices(selectedRadioBoolean, specialExteriorAndInteriorDisable, selectedRadioButton);
                break;
            case "Biological Interior Cleaning":
                disablingServices(selectedRadioBoolean, biologicalInteriorCleaningDisable, selectedRadioButton);
                break;
            case "Waxing-Polishing":
                if (selectedRadioBoolean) {
                    selectedservices.add(selectedRadioButton); 
                } else {
                    selectedservices.remove(selectedRadioButton);
                }
                break;
            case "Engine Cleaning":
                if (selectedRadioBoolean) {
                    selectedservices.add(selectedRadioButton); 
                } else {
                    selectedservices.remove(selectedRadioButton);
                }
                break;
            case "Chassis Wash":
                if (selectedRadioBoolean) {
                    selectedservices.add(selectedRadioButton); 
                } else {
                    selectedservices.remove(selectedRadioButton);
                } 
                break;
            default:
                break;
        }

        //**Calculating** the cost addition/substraction
        // Retrieving the appropriate service of the MAP based on what the user selected
        int hashCode = 0;
        String service;

        for (Map.Entry<Integer, String> entry : services.entrySet()) {
            hashCode = entry.getKey();
            service = entry.getValue();
            if (selectedRadioButton.getText() == service) {
            // System.out.println(selectedRadioButton.getText());
            break;
            }
        }

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs[i].length; j++){
                if (i == hashCode - 1) {
                    //Calculating the total cost based on the selected services and vehicle
                    if (vehiclesbuttons.getVehicleKind() == "Car") {
                        // System.out.println("car1"+vehiclesbuttons.getVehicleKind());
                        int service_cost = Integer.parseInt(costs[i][0]);
                        int oldtotalcost = Integer.parseInt(calculatecosLabel.getText());
                        int newtotalcost;
                        if (selectedRadioButton.isSelected()) {
                            newtotalcost = oldtotalcost + service_cost;
                        } else {
                            newtotalcost = oldtotalcost - service_cost;
                        }
                        calculatecosLabel.setText(Integer.toString(newtotalcost));
                        break;
                    } else if (vehiclesbuttons.getVehicleKind() == "Jeep") {
                        //System.out.println("car1"+vehiclesbuttons.getVehicleKind());
                        int service_cost = Integer.parseInt(costs[i][1]);
                        int oldtotalcost = Integer.parseInt(calculatecosLabel.getText());
                        int newtotalcost;
                        if (selectedRadioButton.isSelected()) {
                            newtotalcost = oldtotalcost + service_cost;
                        } else {
                            newtotalcost = oldtotalcost - service_cost;
                        }
                        calculatecosLabel.setText(Integer.toString(newtotalcost));
                        break;
                    } else if (vehiclesbuttons.getVehicleKind() == "MotorCycle") {
                        //System.out.println("car1"+vehiclesbuttons.getVehicleKind());
                        int service_cost = Integer.parseInt(costs[i][2]);
                        int oldtotalcost = Integer.parseInt(calculatecosLabel.getText());
                        int newtotalcost;
                        if (selectedRadioButton.isSelected()) {
                            newtotalcost = oldtotalcost + service_cost;
                        } else {
                            newtotalcost = oldtotalcost - service_cost;
                        }
                        calculatecosLabel.setText(Integer.toString(newtotalcost));
                        break;
                    }
                }
            }
        }  
    }
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
