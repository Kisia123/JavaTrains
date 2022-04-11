package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sample.TrainState.*;

public class DataController implements Serializable {

    public transient ObservableList<Train> trainList = FXCollections.observableArrayList();

    public transient ObservableList<TrainStation> trainStationsList = FXCollections.observableArrayList();

    public transient ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(new ArrayList<>(trainList));
        oos.writeObject(new ArrayList<>(trainStationsList));
        oos.writeObject(new ArrayList<>(reservations));
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        ArrayList<Train>trainList = (ArrayList<Train>) ois.readObject();
        ArrayList<TrainStation>trainStationList = (ArrayList<TrainStation>) ois.readObject();
        ArrayList<Reservation>reservations = (ArrayList<Reservation>) ois.readObject();

        this.trainList = FXCollections.observableArrayList(trainList);
        this.trainStationsList = FXCollections.observableArrayList(trainStationList);
        this.reservations = FXCollections.observableArrayList(reservations);
    }

    public DataController() {
        //data();
    }
    private void data(){
        trainStationsList.add(new TrainStation("Station1", 15));
        trainStationsList.add(new TrainStation("Station2", 5));
        trainStationsList.add(new TrainStation("Station3", 10));
        trainStationsList.add(new TrainStation("Station4", 11));


        trainList.add(new Train("nn12", 5, nowy, trainStationsList.get(0), trainStationsList.get(1), new Time(8, 34), new Time(9, 34)));
        trainList.add(new Train("okn67", 4, opozniony, trainStationsList.get(1), trainStationsList.get(2), new Time(9, 38), new Time(12, 34)));
        trainList.add(new Train("kk89", 6, nowy, trainStationsList.get(1), trainStationsList.get(3), new Time(3, 03), new Time(9, 34)));
        trainList.add(new Train("aaa123", 6, nowy, trainStationsList.get(2), trainStationsList.get(1), new Time(5, 12), new Time(6, 14)));
        trainList.add(new Train("k456", 6, nowy, trainStationsList.get(0), trainStationsList.get(3), new Time(10, 38), new Time(11, 9)));
        trainList.add(new Train("k1kkh21", 9, nowy, trainStationsList.get(0), trainStationsList.get(1), new Time(11, 30), new Time(13, 4)));
        trainList.add(new Train("kjkl", 7, opozniony, trainStationsList.get(3), trainStationsList.get(0), new Time(12, 45), new Time(14, 24)));
        trainList.add(new Train("kppppp", 23, nowy, trainStationsList.get(1), trainStationsList.get(0), new Time(13, 56), new Time(16, 36)));
        trainList.add(new Train("kkkkkkk", 12, nowy, trainStationsList.get(1), trainStationsList.get(3), new Time(15, 00), new Time(16, 11)));
        trainList.add(new Train("nnNN12", 13, nowy, trainStationsList.get(0), trainStationsList.get(3), new Time(8, 34), new Time(9, 34)));
        trainList.add(new Train("oknAD67", 45, opozniony, trainStationsList.get(2), trainStationsList.get(3), new Time(10, 38), new Time(12, 34)));
        trainList.add(new Train("RWkk89", 6, nowy, trainStationsList.get(1), trainStationsList.get(2), new Time(6, 03), new Time(9, 34)));
        trainList.add(new Train("aADSDaa123", 7, nowy, trainStationsList.get(1), trainStationsList.get(0), new Time(7, 12), new Time(8, 14)));
        trainList.add(new Train("ksqcd456", 2, zwykly, trainStationsList.get(3), trainStationsList.get(0), new Time(10, 38), new Time(11, 9)));
        trainList.add(new Train("k1fa121", 1, nowy, trainStationsList.get(3), trainStationsList.get(2), new Time(14, 30), new Time(23, 4)));
        trainList.add(new Train("kjsdgkl", 6, nowy, trainStationsList.get(3), trainStationsList.get(1), new Time(18, 45), new Time(19, 24)));
        trainList.add(new Train("kppfyujgkppp", 9, opozniony, trainStationsList.get(1), trainStationsList.get(3), new Time(23, 06), new Time(01, 36)));
        trainList.add(new Train("kkkksafkkk", 10, opozniony, trainStationsList.get(1), trainStationsList.get(2), new Time(15, 00), new Time(16, 11)));


    }
    private static final String CSV_SEPARATOR = ";";
    public void readTrainsFromCSV() throws IOException {
        ArrayList<Train>simpleArrayListOfTrains = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("trains.csv"), "UTF-8"));
        br.readLine();
        String line = br.readLine();
        while(line!=null&&!line.isEmpty()){
            String[]strings = line.split(";");
            Train train = new Train();
            train.name = new SimpleStringProperty(strings[0]);
            train.numberOfSeats = Integer.parseInt(strings[1]);
            train.state = nowy;
            train.emptySeats = Integer.parseInt(strings[3]);
            train.price = Integer.parseInt(strings[4]);
            train.departureTime = new Time(strings[5]);
            train.arrivalTime = new Time(strings[6]);
            train.startStation = getStationByName(strings[7]);
            train.endStation= getStationByName(strings[8]);
            simpleArrayListOfTrains.add(train);
            line = br.readLine();
        }
        br.close();
        trainList.clear();
        trainList.addAll(simpleArrayListOfTrains);
    }
    public void writeTrainToCSV() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("trains.csv"), "UTF-8"));
        bw.write("name;seats;state;empty seats;price;departure time;arrival time;start station;end station");
        bw.newLine();
        for (Train train : trainList ) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(train.name.getValueSafe());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.numberOfSeats);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.state);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.emptySeats);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.price);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.departureTime);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.arrivalTime);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.startStation.name);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.endStation.name);
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public void readTicketsFromCSV() throws IOException {
        ArrayList<Reservation>reservations = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("reservations.csv"), "UTF-8"));
        br.readLine();
        String line = br.readLine();
        while(line!=null&&!line.isEmpty()){
            String[]strings = line.split(";");
            Reservation r = new Reservation();
            r.firstName = new SimpleStringProperty(strings[0]);
            r.lastName = new SimpleStringProperty(strings[1]);
            r.firstStation = getStationByName(strings[2]);
            r.train = findTrainByName(strings[3]);
            r.chosenClass = TrainClass.valueOf(strings[4]);
            r.chosenDiscount = Discount.valueOf(strings[5]);
            reservations.add(r);
            line = br.readLine();
        }
        br.close();
       this.reservations.clear();
       this.reservations.addAll(reservations);
    }
    public void writeTicketsToCSV() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("reservations.csv"), "UTF-8"));
        bw.write("first name;last name;first station;train;class;discount");
        bw.newLine();
        for (Reservation r : reservations ) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(r.firstName.getValueSafe());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(r.lastName.getValueSafe());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(r.firstStation.name);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(r.train.name.getValueSafe());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(r.chosenClass.name());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(r.chosenDiscount.name());

            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public ArrayList<Train> readFromCSV() throws IOException {
        ArrayList<Train>simpleArrayListOfTrains = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("products.csv"), "UTF-8"));
        br.readLine();
        String line = br.readLine();
        while(line!=null&&!line.isEmpty()){
            String[]strings = line.split(";");
            Train train = new Train();
            train.name = new SimpleStringProperty(strings[0]);
            train.numberOfSeats = Integer.parseInt(strings[1]);
            train.state = TrainState.valueOf(strings[2]);
            train.emptySeats = Integer.parseInt(strings[3]);
            train.price = Integer.parseInt(strings[4]);
            train.departureTime = new Time(strings[5]);
            train.arrivalTime = new Time(strings[6]);
            train.startStation = getStationByName(strings[7]);
            train.endStation= getStationByName(strings[8]);
            simpleArrayListOfTrains.add(train);
            line = br.readLine();
        }
        br.close();
        return simpleArrayListOfTrains;
    }
    public TrainStation getStationByName(String s){
        for(TrainStation t: trainStationsList){
            if(t.name.equals(s)){
                return t;
            }
        }
        return null;
    }
    public void writeToCSV(TrainStation trainStation) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));
        bw.write("name;seats;state;empty seats;price;departure time;arrival time;start station;end station");
        bw.newLine();
        for (Train train : trainList ) {
            if(train.startStation != trainStation){
                continue;
            }
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(train.name.getValueSafe());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.numberOfSeats);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.state);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.emptySeats);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.price);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.departureTime);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.arrivalTime);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.startStation.name);
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(train.endStation.name);
            //oneLine.append(CSV_SEPARATOR);
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public Train findTrainByName(String name) {
        for (Train train : trainList) {
            if (name.compareTo(train.name.get()) == 0) {
                return train;
            }
        }
        System.out.println("Taki pociąg nie istnieje");
        return null;
    }

    public TrainStation findTrainStationByName(String name) {
        for (TrainStation trainStation : trainStationsList) {
            //System.out.println("stacje:"+trainStation.name);
            if (name.compareTo(trainStation.name) == 0) {
                return trainStation;
            }
        }
        System.out.println("Taka stacja nie istnieje");
        return null;
    }


    Map<String, TrainStation> stations = new HashMap<>();

    TrainStation getStation(String name) {
        for (TrainStation station : stations.values()) {
            if (name == station.name) {
                return station;
            }
        }
        System.out.println("nie ma takiej stacji");
        return null;
    }

    void compareByMaxCapacity() {

    }

    void removeTrain(Train t1) {
        trainList.remove(t1);
    }

    void addStation(String name, int capacity) {
        stations.put(name, new TrainStation(name, capacity));
    }

    void removeStation(String name) {
        stations.remove(name);
    }

    List<TrainStation> emptyStations() {
        List<TrainStation> emptyStations = new ArrayList<>();
        for (TrainStation station : stations.values()) {
            if (station.trainsOnStation.isEmpty()) {
                emptyStations.add(station);
            }
        }
        return emptyStations;
    }

    void printStation() {
        for (TrainStation station : stations.values()) {
            System.out.println("Nazwa: " + station.name);
            System.out.println("Obciązenie: " + station.trainsOnStation.size() + "\n");
        }
    }

}
