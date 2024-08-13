<<<<<<< HEAD
package gr.uop;

import java.util.ArrayList;

import javafx.scene.control.Label;

public class LicensePlate {
        private Label licensePlateLabel;
        private ArrayList<String> licensePlateArray = new ArrayList<String>();
    
        public LicensePlate() {
            licensePlateLabel = new Label("");
        }
    
        public Label getStartingText() { //when the label first loads
            return licensePlateLabel;
        }

        public Label updateLicensePlateLabel() { //updates the label
            String licensePlate = "";
            for (int i = 0; i < licensePlateArray.size(); i++) {
                licensePlate += licensePlateArray.get(i);
            }
            licensePlateLabel.setText(licensePlate);
            return licensePlateLabel;
        }

        public void addCharInLicensePlateArray(String value) { //stores the value into an array depending on the condition
            if (value == "SPACE") { 
                value = " ";
                licensePlateArray.add(value);

            } else if (value == "BACKSPACE") {
                int lastElement = licensePlateArray.size() - 1;
                try {
                    licensePlateArray.remove(lastElement);

                } catch (IndexOutOfBoundsException e) {
                    // System.out.println("It looks like the array is already emptied!");

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else if (value == "ENTER") {
                if (licensePlateArray.size() < 2) {
                    // System.out.println("The license plate isn't valid!");

                } else {
                    // System.out.println("Licence plate: " + licensePlateLabel.getText());
                }

            } else {
                licensePlateArray.add(value);

            }
        }
}
=======
package gr.uop;

import java.util.ArrayList;

import javafx.scene.control.Label;

public class LicensePlate {
        private Label licensePlateLabel;
        private ArrayList<String> licensePlateArray = new ArrayList<String>();
    
        public LicensePlate() {
            licensePlateLabel = new Label("");
        }
    
        public Label getStartingText() { //when the label first loads
            return licensePlateLabel;
        }

        public Label updateLicensePlateLabel() { //updates the label
            String licensePlate = "";
            for (int i = 0; i < licensePlateArray.size(); i++) {
                licensePlate += licensePlateArray.get(i);
            }
            licensePlateLabel.setText(licensePlate);
            return licensePlateLabel;
        }

        public void addCharInLicensePlateArray(String value) { //stores the value into an array depending on the condition
            if (value == "SPACE") { 
                value = " ";
                licensePlateArray.add(value);

            } else if (value == "BACKSPACE") {
                int lastElement = licensePlateArray.size() - 1;
                try {
                    licensePlateArray.remove(lastElement);

                } catch (IndexOutOfBoundsException e) {
                    // System.out.println("It looks like the array is already emptied!");

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else if (value == "ENTER") {
                if (licensePlateArray.size() < 2) {
                    // System.out.println("The license plate isn't valid!");

                } else {
                    // System.out.println("Licence plate: " + licensePlateLabel.getText());
                }

            } else {
                licensePlateArray.add(value);

            }
        }
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
