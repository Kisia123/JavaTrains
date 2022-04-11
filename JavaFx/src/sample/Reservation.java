package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Reservation implements Serializable {
    public transient SimpleStringProperty firstName = new SimpleStringProperty();
    public transient SimpleStringProperty lastName = new SimpleStringProperty();
    public TrainStation firstStation;
    public Train train;
    public TrainClass chosenClass;
    public Discount chosenDiscount;
    Reservation(){

    }
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(firstName.getValueSafe());
        oos.writeObject(lastName.getValueSafe());
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.firstName = new SimpleStringProperty( (String) ois.readObject());
        this.lastName = new SimpleStringProperty( (String) ois.readObject());
    }

    public Reservation(String firstName, String lastName, TrainStation trainStation, Train train, TrainClass trainClass, Discount discount){
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        firstStation = trainStation;
        this.train = train;
        chosenClass = trainClass;
        chosenDiscount = discount;
    }

}
