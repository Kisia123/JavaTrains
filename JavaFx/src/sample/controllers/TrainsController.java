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
import sample.*;

import java.io.IOException;

public class TrainsController {
    static public TrainStation startStation;
    static public TrainStation endStation;
    static public Time departureTime;

    @FXML
    private TableView<Train> tableView;
    @FXML
    private TableColumn<Train, String> nameColumn;
    @FXML
    private TableColumn<Train, String> departureTimeColumn;
    @FXML
    private TableColumn<Train, String> arrivalTimeColumn;
    @FXML
    private TableColumn<Train, String> priceColumn;
    @FXML
    private TextField searching;
    @FXML
    private Button buyButton;


    public void initialize() {
        FilteredList<Train> filteredList = new FilteredList<>(Main.dataController.trainList);
        SortedList<Train>sortedList = new SortedList<>(filteredList);
        nameColumn.setCellValueFactory(v -> v.getValue().name);
        nameColumn.setSortable(true);
        nameColumn.setSortType(TableColumn.SortType.ASCENDING);
        departureTimeColumn.setCellValueFactory(v ->  new SimpleStringProperty(v.getValue().departureTime.toString()));
        departureTimeColumn.setComparator((t1,t2)->{
            return new Time(t1).compareTo(new Time(t2));
        });
        arrivalTimeColumn.setCellValueFactory(v -> new SimpleStringProperty(v.getValue().arrivalTime.toString()));
        arrivalTimeColumn.setComparator((t1,t2)->{
            return new Time(t1).compareTo(new Time(t2));
        });
        priceColumn.setCellValueFactory(v -> new SimpleStringProperty(String.valueOf(v.getValue().price)));
        priceColumn.setComparator((p1,p2)->{
            return Integer.compare(Integer.parseInt(p1),Integer.parseInt(p2));
        });
        tableView.setItems(sortedList);
        tableView.setPlaceholder(new Label("Nie znaleziono odjazdów"));

        tableView.setRowFactory(tv -> {
            TableRow<Train> row = new TableRow<>() {
                private Tooltip tooltip = new Tooltip();
                @Override
                public void updateItem(Train train, boolean empty) {
                    super.updateItem(train, empty);
                    if (train == null) {
                        setTooltip(null);
                    } else {

                        tooltip.setText(train.toString());
                        tooltip.setShowDelay(Duration.millis(570));
                        setTooltip(tooltip);
                    }
                }
            };

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Train rowData = row.getItem();
                    InfoController.selectedTrain = rowData;
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../layouts/info.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Informacje o pociągu");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                    System.out.println(rowData);
                }
            });
            return row;
        });

        searching.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(train -> {
                if( !train.name.get().contains(newValue)){
                    return false;
                }
                return filterTrains(train);
            });
        });

        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        filteredList.setPredicate(
                train -> {
                    return filterTrains(train);
                }
        );
        buyButton.setOnAction(e -> {
            Train selectedTrain = tableView.getSelectionModel().getSelectedItem();
            if (selectedTrain == null) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Błąd");
                a.setHeaderText(null);
                a.setContentText("Wybierz pociąg!");
                a.showAndWait();
                return;
            }
            TicketBuyingController.yourSelectedTrain = selectedTrain;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../layouts/bilet.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Kupowanie biletu");
                stage.setScene(new Scene(root));
                stage.show();
                stage.setOnCloseRequest(event->{
                    tableView.refresh();
                });
                stage.setOnHiding(event->{
                    tableView.refresh();
                });
            } catch (IOException error) {
                error.printStackTrace();
            }
        });

    }

    private boolean filterTrains(Train train) {
        if(train.emptySeats <=0){
            return false;
        }
        if (train.startStation == startStation) {
            if (train.endStation == endStation || endStation == null) {
                if(departureTime == null){
                    return true;
                }
                if(train.departureTime.compareTo(departureTime)>=0){
                    return true;
                }
            }
        }
        return false;
    }
}
