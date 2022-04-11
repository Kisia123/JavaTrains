package com.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pro.TrainState.nowy;
import static com.pro.TrainState.opozniony;

public class Controller {

    public List<Train> trainList = new ArrayList<>();
    void removeTrain(Train t1) {
        trainList.remove(t1);
    }
    public List<TrainStation>trainStationsList = new ArrayList<>();

    public Controller(){
        trainList.add(new Train("nn12",5,nowy));
        trainList.add(new Train("okn67",4,opozniony));
        trainList.add(new Train("kk89",6,nowy));
        trainList.add(new Train("aaa123",6,nowy));
        trainList.add(new Train("k456",6,nowy));
        trainList.add(new Train("k121",6,nowy));
        trainList.add(new Train("kjkl",6,nowy));
        trainList.add(new Train("kppppp",6,nowy));
        trainList.add(new Train("kkkkkkk",6,nowy));

        trainStationsList.add(new TrainStation("Station1",15));
        trainStationsList.add(new TrainStation("Station2",5));
        trainStationsList.add(new TrainStation("Station3",10));
        trainStationsList.add(new TrainStation("Station4",11));


    }
    public Train findTrainByName(String name) {
        for (Train train : trainList) {
            if (name.compareTo(train.name) == 0) {
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
    void compareByMaxCapacity(){

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
