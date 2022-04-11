package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;

public class Main extends Application {
    public static DataController dataController = new DataController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        deserialize();
        Parent root = FXMLLoader.load(getClass().getResource("layouts/layout.fxml"));
        primaryStage.setTitle("Kupowanie biletu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Komunikat");
            alert.setContentText("Czy chcesz zapisać dane?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                try {
                    serialize();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    static public void serialize() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(dataController);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    static public void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("2.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        dataController = (DataController) objectInputStream.readObject();
        objectInputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }
}
