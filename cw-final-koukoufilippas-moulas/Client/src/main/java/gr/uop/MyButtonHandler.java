package gr.uop;

import gr.DirectoryServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

//**Handling the keyboards buttons and modifying the Licence Plate accordingly**
public class MyButtonHandler implements EventHandler<ActionEvent>{
    
        private String value;
        private LicensePlate licensePlate;

        public MyButtonHandler(String value, LicensePlate licensePlate) {
            this.value = value;
            this.licensePlate = licensePlate;
        }

        @Override
        public void handle(ActionEvent event) {
            licensePlate.addCharInLicensePlateArray(value);

            Label licensePlateLabel = licensePlate.updateLicensePlateLabel();
            //System.out.println(licensePlateLabel);
            
              if(value == "ENTER")
             {
              String LabelText = licensePlateLabel.getText();
              int LabelLength = LabelText.length();
                if (LabelLength >=2) {
                  //System.out.println("The license plate isn't valid!");

                  DirectoryServices DS_dialog = new DirectoryServices(licensePlateLabel); //As the current license plate is valid we open the DirectoryServices Dialog.                                                       
                } else {
                    //System.out.println("This license plate will be sent somewhere!");
                } 
             }else{
               //In any other case the Dialog pane won't be opened.
             }                          
        }
}
