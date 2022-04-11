package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Time;
import sample.TrainStation;

import java.io.IOException;

public class TicketController {
    @FXML
    private ComboBox<TrainStation> startStationBox;
    @FXML
    private ComboBox<TrainStation> endStationBox;
    @FXML
    private TextField timeTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button reservationButton;
    @FXML
    private Button serialButton;

    public void initialize() {
        startStationBox.setItems(Main.dataController.trainStationsList);
        endStationBox.setItems(Main.dataController.trainStationsList);
        searchButton.setOnAction(e -> {
            if (startStationBox.getValue() == null) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Błąd");
                a.setHeaderText(null);
                a.setContentText("Wybierz stację początkową!");
                a.showAndWait();
                return;
            }
            TrainsController.startStation = startStationBox.getValue();
            TrainsController.endStation = endStationBox.getValue();
            if (timeTextField.getText().isEmpty()) {
                TrainsController.departureTime = null;
            } else {
                try {
                    TrainsController.departureTime = new Time(timeTextField.getText());
                } catch (Exception exception) {
                    TrainsController.departureTime = null;
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Błąd");
                    a.setHeaderText(null);
                    a.setContentText("Podano zły format czasu");
                    a.showAndWait();
                    return;
                }
            }
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../layouts/table.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Wybieranie pociągu");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException error) {
                error.printStackTrace();
            }


        });

        reservationButton.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../layouts/reservation.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Twoje rezerwacje");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException error) {
                error.printStackTrace();
            }

        });

        serialButton.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../layouts/seria.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Serializacja");
                stage.setScene(new Scene(root));
                stage.show();
                stage.setOnHidden(event->{
                    System.out.println("czy się uruchamia?>");
                    System.out.println(Main.dataController.trainStationsList.size());
                    startStationBox.setItems(Main.dataController.trainStationsList);
                    endStationBox.setItems(Main.dataController.trainStationsList);
                });
            } catch (IOException error) {
                error.printStackTrace();
            }

        });
    }

}


