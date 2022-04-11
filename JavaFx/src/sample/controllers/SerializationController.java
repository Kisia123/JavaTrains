package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sample.Main;
import sample.Train;
import sample.TrainStation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SerializationController implements Initializable {
    @FXML
    Button serialAllButton;
    @FXML
    Button deserialAllButton;
    @FXML
    Button serialStationButton;
    @FXML
    Button deserialStationButton;
    @FXML
    Button serialTrainButton;
    @FXML
    Button deserialTrainButton;
    @FXML
    Button serialTicketButton;
    @FXML
    Button deserialTicketButton;
    @FXML
    ComboBox<TrainStation> chooseStationBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serialAllButton.setOnAction(e -> {
            try {
                Main.serialize();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        deserialAllButton.setOnAction(e -> {
            try {
                Main.deserialize();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            chooseStationBox.setItems(Main.dataController.trainStationsList);
        });
        chooseStationBox.setItems(Main.dataController.trainStationsList);
        serialStationButton.setOnAction(e -> {
            TrainStation trainStation = chooseStationBox.getValue();
            if (trainStation == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Musisz wybrać stację");
                alert.showAndWait();
                return;
            }
            try {
                Main.dataController.writeToCSV(trainStation);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Zapisywanie nie powiodło się");
                alert.showAndWait();
            }
        });
        deserialStationButton.setOnAction(e -> {
            TrainStation trainStation = chooseStationBox.getValue();
            if (trainStation == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Musisz wybrać stację");
                alert.showAndWait();
                return;
            }
            try {
                ArrayList<Train> list = Main.dataController.readFromCSV();
                trainStation.addTrains(list);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Zapisywanie nie powiodło się");
                alert.showAndWait();
            }
        });


        serialTrainButton.setOnAction(e ->
        {
            try {
                Main.dataController.writeTrainToCSV();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Nie powiodło się");
                alert.showAndWait();
            }
        });
        deserialTrainButton.setOnAction(e -> {
            try {
                Main.dataController.readTrainsFromCSV();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Zapisywanie nie powiodło się");
                alert.showAndWait();
            }
        });

        serialTicketButton.setOnAction(e ->
        {
            try {
                Main.dataController.writeTicketsToCSV();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Nie powiodło się");
                alert.showAndWait();
            }
        });
        deserialTicketButton.setOnAction(e -> {
            try {
                Main.dataController.readTicketsFromCSV();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Zapisywanie nie powiodło się");
                alert.showAndWait();
            }
        });

    }
}
