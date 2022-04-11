package sample.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;
import sample.Reservation;

import java.io.IOException;

public class ReservationController {
    @FXML
    TableView<Reservation> tableOfReservations;
    @FXML
    TableColumn<Reservation, String> nameColumn;
    @FXML
    TableColumn<Reservation, String> startStColumn;
    @FXML
    TableColumn<Reservation, String> nameTColumn;
    @FXML
    TableColumn<Reservation, String> depColumn;
    @FXML
    TableColumn<Reservation, String> arrColumn;
    @FXML
    TableColumn<Reservation, String> classColumn;
    @FXML
    TableColumn<Reservation, String> priceColumn;

    public void initialize() {
        SortedList<Reservation> sortedList = new SortedList<>(Main.dataController.reservations);
        tableOfReservations.setItems(sortedList);
        nameColumn.setCellValueFactory(v -> v.getValue().firstName.concat(" ").concat(v.getValue().lastName));
        startStColumn.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().firstStation.name));
        nameTColumn.setCellValueFactory(v -> v.getValue().train.name);
        depColumn.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().train.departureTime.toString()));
        arrColumn.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().train.arrivalTime.toString()));
        classColumn.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().chosenClass.toString()));
        priceColumn.setCellValueFactory(v -> new SimpleStringProperty(String.valueOf(v.getValue().train.price)));
        tableOfReservations.setPlaceholder(new Label("Nie masz jeszcze rezerwacji"));
        tableOfReservations.setRowFactory(tv -> {
            TableRow<Reservation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Reservation rowData = row.getItem();
                    Main.dataController.reservations.remove(rowData);
                    rowData.train.addNumberOfEmptySeats();
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Uwaga");
                    a.setHeaderText(null);
                    a.setContentText("Rezerwacja została usunięta");
                    a.showAndWait();

                    return;
                }
            });
            return row;
        });
    }
}
