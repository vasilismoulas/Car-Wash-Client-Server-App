<<<<<<< HEAD
package gr.uop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class TableViewCreation extends TableView<VehicleInfo>{
    
    private TableView<VehicleInfo> tableView; 
    private static IncomeBook vehicleInfo;
    private static ObservableList<VehicleInfo> data;
    
    public Node createTableView() {
        tableView = new TableView<>();

        TableColumn<VehicleInfo, String> plateColumn = new TableColumn<>("License Plate");
        TableColumn<VehicleInfo, String> costColumn = new TableColumn<>("Cost");
        TableColumn<VehicleInfo, String> timeColumn = new TableColumn<>("Time");

        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        plateColumn.setStyle("-fx-alignment: CENTER;");
        costColumn.setStyle("-fx-alignment: CENTER;");
        timeColumn.setStyle("-fx-alignment: CENTER;");

        tableView.getColumns().addAll(plateColumn, costColumn, timeColumn);

        tableView.setItems(FXCollections.observableArrayList());

        double columnWidthPercentage = 33.3;

        tableView.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double tableViewWidth = newValue.doubleValue();
                double columnWidth = (tableViewWidth * columnWidthPercentage) / 100.0;

                plateColumn.setPrefWidth(columnWidth);
                costColumn.setPrefWidth(columnWidth);
                timeColumn.setPrefWidth(columnWidth);
            }
        });
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                //vehicleInfo.printReceipt();
            }
        });
        
        return tableView;
    }

    public ObservableList<VehicleInfo> addVehicleInfo(String licencePlate, String cost, String currentTime) {
        ObservableList<VehicleInfo> data = tableView.getItems();
        if (data == null) {
            data = FXCollections.observableArrayList();
            tableView.setItems(data);
        }
        VehicleInfo newItem = new VehicleInfo(licencePlate, cost, currentTime);
        data.add(newItem);
        return data;
    }   

    public TableView<VehicleInfo> getTableView() {
        return tableView;
    }


}
=======
package gr.uop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class TableViewCreation extends TableView<VehicleInfo>{
    
    private TableView<VehicleInfo> tableView; 
    private static IncomeBook vehicleInfo;
    private static ObservableList<VehicleInfo> data;
    
    public Node createTableView() {
        tableView = new TableView<>();

        TableColumn<VehicleInfo, String> plateColumn = new TableColumn<>("License Plate");
        TableColumn<VehicleInfo, String> costColumn = new TableColumn<>("Cost");
        TableColumn<VehicleInfo, String> timeColumn = new TableColumn<>("Time");

        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        plateColumn.setStyle("-fx-alignment: CENTER;");
        costColumn.setStyle("-fx-alignment: CENTER;");
        timeColumn.setStyle("-fx-alignment: CENTER;");

        tableView.getColumns().addAll(plateColumn, costColumn, timeColumn);

        tableView.setItems(FXCollections.observableArrayList());

        double columnWidthPercentage = 33.3;

        tableView.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double tableViewWidth = newValue.doubleValue();
                double columnWidth = (tableViewWidth * columnWidthPercentage) / 100.0;

                plateColumn.setPrefWidth(columnWidth);
                costColumn.setPrefWidth(columnWidth);
                timeColumn.setPrefWidth(columnWidth);
            }
        });
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                //vehicleInfo.printReceipt();
            }
        });
        
        return tableView;
    }

    public ObservableList<VehicleInfo> addVehicleInfo(String licencePlate, String cost, String currentTime) {
        ObservableList<VehicleInfo> data = tableView.getItems();
        if (data == null) {
            data = FXCollections.observableArrayList();
            tableView.setItems(data);
        }
        VehicleInfo newItem = new VehicleInfo(licencePlate, cost, currentTime);
        data.add(newItem);
        return data;
    }   

    public TableView<VehicleInfo> getTableView() {
        return tableView;
    }


}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
