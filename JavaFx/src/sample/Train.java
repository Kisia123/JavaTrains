package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Train implements Comparable<Train>, Serializable {
    public transient SimpleStringProperty name = new SimpleStringProperty();
    public int numberOfSeats;
    public int emptySeats;
    public TrainState state;
    int travelTime = 60;
    public int price = 100;
    public TrainStation startStation;
    public TrainStation endStation;
    public Time departureTime;
    public Time arrivalTime;

    public Train(String name, int number, TrainState state, TrainStation startStation,TrainStation endStation,Time departureTime,Time arrivalTime){
        this.name.set(name);
        this.numberOfSeats =number;
        emptySeats = numberOfSeats;
        this.state=state;
        this.startStation = startStation;
        this.endStation = endStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = ThreadLocalRandom.current().nextInt(10,50);
    }
    public Train(){
    }
    public Train(String name, int number, TrainState state){
        this.name.set(name);
        this.numberOfSeats =number;
        this.state=state;

    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(name.getValueSafe());
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        String name = (String) ois.readObject();
        this.name = new SimpleStringProperty(name);
    }

    Train(String name, int number, TrainState state, int tt){
        this.name.set(name);
        numberOfSeats =number;
        this.state=state;
        travelTime=tt;
    }

    public boolean changeNumberOfEmptySeats(){
        if(emptySeats==0){
           return false;
        }
        emptySeats --;
        return true;
    }
    public void addNumberOfEmptySeats(){
        emptySeats++;
    }
    void print(){
        System.out.println("informacje:");
        System.out.println("nazwa: "+name);
        System.out.println("ilosc miejsc: "+ numberOfSeats);
        System.out.println("stan: "+state);
    }
    @Override
    public String toString(){
        StringBuilder bob = new StringBuilder();
        bob.append("informacje:\n");
        bob.append("nazwa: "+name.get()+"\n");
        bob.append("ilosc miejsc: "+ numberOfSeats+"\n");
        bob.append("ilosc wolnych miejsc: "+ emptySeats+"\n");
        bob.append("stan: "+state);
        return bob.toString();

    }

    public Object[] printToTable(){
        return new Object[]{name,numberOfSeats,state};
    }

    @Override
    public int compareTo(Train t1) {
        return  name.get().compareTo(t1.name.get());
    }

}
