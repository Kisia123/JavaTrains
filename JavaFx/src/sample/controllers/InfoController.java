package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Train;

public class InfoController {
    static Train selectedTrain;
    @FXML
    Label nameLabel;
    @FXML
    Label seatsLabel;
    @FXML
    Label freeLabel;
    @FXML
    Label stateLabel;
    @FXML
    Label classOneLabel;
    @FXML
    Label classSecLabel;
    @FXML
    Label classThLabel;

    public void initialize() {
        nameLabel.setText(selectedTrain.name.get());
        seatsLabel.setText(String.valueOf(selectedTrain.numberOfSeats));
        freeLabel.setText(String.valueOf(selectedTrain.emptySeats));
        stateLabel.setText(selectedTrain.state.toString());
        classThLabel.setText(selectedTrain.price + " zl");
        classSecLabel.setText((int)(selectedTrain.price * 1.5) + " zl");
        classOneLabel.setText(selectedTrain.price * 2 + " zl");
    }
}
