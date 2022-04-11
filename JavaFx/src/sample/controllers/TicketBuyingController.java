package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.*;


public class TicketBuyingController {
    static Train yourSelectedTrain;
    @FXML
    private ComboBox<Discount> userBox;
    @FXML
    private ComboBox<TrainClass> classBox;
    @FXML
    private Button buyButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private Label priceNumber;

    public void initialize() {
        userBox.getItems().setAll(Discount.values());
        userBox.getSelectionModel().selectFirst();
        classBox.getItems().setAll(TrainClass.values());
        classBox.getSelectionModel().selectFirst();
        changePrice();
        userBox.setOnAction(e -> {
            changePrice();
        });
        classBox.setOnAction(e -> {
            changePrice();
        });

        buyButton.setOnAction(e -> {
            if (nameField.getText().isEmpty() || surnameField.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Błąd");
                a.setHeaderText(null);
                a.setContentText("Uzupełnij dane!");
                a.showAndWait();
                return;
            }

            if (!yourSelectedTrain.changeNumberOfEmptySeats()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Błąd");
                a.setHeaderText(null);
                a.setContentText("Nie ma już dostępnych miejsc");
                a.showAndWait();
                return;
            }
            Reservation reservation = new Reservation(nameField.getText(), surnameField.getText(), yourSelectedTrain.startStation, yourSelectedTrain, classBox.getValue(), userBox.getValue());
            Main.dataController.reservations.add(reservation);
            Stage stage = (Stage) buyButton.getScene().getWindow();
            stage.close();

        });


    }

    void changePrice() {
        double price = yourSelectedTrain.price;
        switch (classBox.getValue()) {
            case class1:
                price *= 2;
                break;
            case class2:
                price *= 1.5;
                break;

        }
        switch (userBox.getValue()) {
            case emeryt:
                price *= 0.75;
                break;
            case student:
                price *= 0.5;
                break;
        }

        priceNumber.setText((int) price + " zł");
    }


}
