<<<<<<< HEAD
package gr.uop;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Client extends Application {    

    @Override
    public void start(Stage stage) {

        String[][] keyboardKeys = {
            {"A", "B", "C", "D", "E", "F", "G", "7", "8", "9"},
            {"H", "I", "J", "K", "L", "M", "N", "4", "5", "6"},
            {"O", "P", "Q", "R", "S", "T", "U", "1", "2", "3"},
            {"V", "W", "X", "Y", "Z", "SPACE", "BACKSPACE", "0", "ENTER"}
        };
        
        var spiltScreenInTwoVBox = new VBox(); //this one will include the "upperLabel" and the "keyboardStackPane" elements

        LicensePlate licensePlate = new LicensePlate();

        Label licensePlateLabel = licensePlate.getStartingText();
        spiltScreenInTwoVBox.getChildren().add(licensePlateLabel);

        licensePlateLabel.setMaxHeight(Double.MAX_VALUE);
        licensePlateLabel.setPrefHeight(384);
        licensePlateLabel.setMaxWidth(Double.MAX_VALUE);
        licensePlateLabel.setAlignment(Pos.CENTER);

        Font font = new Font(100);
        licensePlateLabel.setFont(font);


        StackPane keyboardStackPane = new StackPane(); //this one will include the "keyboardGrid"
        spiltScreenInTwoVBox.getChildren().add(keyboardStackPane);

        keyboardStackPane.setMinHeight(384);
        keyboardStackPane.setPrefHeight(stage.getHeight() / 2);
        keyboardStackPane.setMaxHeight(960);

        GridPane keyboardGrid = new GridPane(); //this one will include all the buttons
        keyboardStackPane.getChildren().add(keyboardGrid);

        keyboardGrid.setMinHeight(384);
        keyboardGrid.setPrefHeight(stage.getHeight() / 2);
        keyboardGrid.setMaxHeight(960);


        for (int i = 0; i < 10; i++) { //to set the width of each column
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(10);
            keyboardGrid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) { //to "maximize" the height of the buttons
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            keyboardGrid.getRowConstraints().add(row);
        }

        for (int i = 0; i < keyboardKeys.length; i++) {
            for (int j = 0; j < keyboardKeys[i].length; j++) {
                Button button = new Button(keyboardKeys[i][j]);
                MyButtonHandler mybuttonhandler = new MyButtonHandler(keyboardKeys[i][j], licensePlate);
                button.setOnAction(mybuttonhandler);
                
                button.setMaxHeight(Double.MAX_VALUE);
                button.setMaxWidth(Double.MAX_VALUE);
                
                if (i == 3 && j == 5) { //for the "SPACE" key to take up 2 columns
                    keyboardGrid.setColumnSpan(button, 2);
                    keyboardGrid.add(button, j, i);

                } else if (i == 3 && j > 5) { //for the keys after "SPACE", to move them to the right by 1 column
                    keyboardGrid.add(button, j+1, i);

                } else { //for the rest of the keys
                    keyboardGrid.add(button, j, i);

                }
            }
        }

        var scene = new Scene(spiltScreenInTwoVBox, 1024, 768);

        //for debugging the height of the buttons when scene > 768px
        scene.heightProperty().addListener((observable, oldHeight, newHeight) -> {
           keyboardStackPane.setPrefHeight(scene.getHeight() / 2);
           keyboardGrid.setPrefHeight(scene.getHeight() / 2);

        //    System.out.println("Scene Height before resize: " + scene.getHeight());
         });
         scene.widthProperty().addListener((observable, oldWidth, newWidth) -> {
           keyboardStackPane.setPrefWidth(scene.getWidth() / 2);
           keyboardGrid.setPrefWidth(scene.getWidth() / 2);

        //    System.out.println("Scene Width before resize: " + scene.getWidth());
         });

        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);

        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

 
}
=======
package gr.uop;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Client extends Application {    

    @Override
    public void start(Stage stage) {

        String[][] keyboardKeys = {
            {"A", "B", "C", "D", "E", "F", "G", "7", "8", "9"},
            {"H", "I", "J", "K", "L", "M", "N", "4", "5", "6"},
            {"O", "P", "Q", "R", "S", "T", "U", "1", "2", "3"},
            {"V", "W", "X", "Y", "Z", "SPACE", "BACKSPACE", "0", "ENTER"}
        };
        
        var spiltScreenInTwoVBox = new VBox(); //this one will include the "upperLabel" and the "keyboardStackPane" elements

        LicensePlate licensePlate = new LicensePlate();

        Label licensePlateLabel = licensePlate.getStartingText();
        spiltScreenInTwoVBox.getChildren().add(licensePlateLabel);

        licensePlateLabel.setMaxHeight(Double.MAX_VALUE);
        licensePlateLabel.setPrefHeight(384);
        licensePlateLabel.setMaxWidth(Double.MAX_VALUE);
        licensePlateLabel.setAlignment(Pos.CENTER);

        Font font = new Font(100);
        licensePlateLabel.setFont(font);


        StackPane keyboardStackPane = new StackPane(); //this one will include the "keyboardGrid"
        spiltScreenInTwoVBox.getChildren().add(keyboardStackPane);

        keyboardStackPane.setMinHeight(384);
        keyboardStackPane.setPrefHeight(stage.getHeight() / 2);
        keyboardStackPane.setMaxHeight(960);

        GridPane keyboardGrid = new GridPane(); //this one will include all the buttons
        keyboardStackPane.getChildren().add(keyboardGrid);

        keyboardGrid.setMinHeight(384);
        keyboardGrid.setPrefHeight(stage.getHeight() / 2);
        keyboardGrid.setMaxHeight(960);


        for (int i = 0; i < 10; i++) { //to set the width of each column
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(10);
            keyboardGrid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) { //to "maximize" the height of the buttons
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            keyboardGrid.getRowConstraints().add(row);
        }

        for (int i = 0; i < keyboardKeys.length; i++) {
            for (int j = 0; j < keyboardKeys[i].length; j++) {
                Button button = new Button(keyboardKeys[i][j]);
                MyButtonHandler mybuttonhandler = new MyButtonHandler(keyboardKeys[i][j], licensePlate);
                button.setOnAction(mybuttonhandler);
                
                button.setMaxHeight(Double.MAX_VALUE);
                button.setMaxWidth(Double.MAX_VALUE);
                
                if (i == 3 && j == 5) { //for the "SPACE" key to take up 2 columns
                    keyboardGrid.setColumnSpan(button, 2);
                    keyboardGrid.add(button, j, i);

                } else if (i == 3 && j > 5) { //for the keys after "SPACE", to move them to the right by 1 column
                    keyboardGrid.add(button, j+1, i);

                } else { //for the rest of the keys
                    keyboardGrid.add(button, j, i);

                }
            }
        }

        var scene = new Scene(spiltScreenInTwoVBox, 1024, 768);

        //for debugging the height of the buttons when scene > 768px
        scene.heightProperty().addListener((observable, oldHeight, newHeight) -> {
           keyboardStackPane.setPrefHeight(scene.getHeight() / 2);
           keyboardGrid.setPrefHeight(scene.getHeight() / 2);

        //    System.out.println("Scene Height before resize: " + scene.getHeight());
         });
         scene.widthProperty().addListener((observable, oldWidth, newWidth) -> {
           keyboardStackPane.setPrefWidth(scene.getWidth() / 2);
           keyboardGrid.setPrefWidth(scene.getWidth() / 2);

        //    System.out.println("Scene Width before resize: " + scene.getWidth());
         });

        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);

        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

 
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
