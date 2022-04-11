package sample;

import java.io.*;
import java.util.*;

public class TrainStation implements Comparable<TrainStation>, Serializable {
    public String name;
    public int maxCapacity;
    public Map<Train, List<Time>> trainsOnStation = new HashMap<>();

    public TrainStation(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public Object[] printToTable() {
        return new Object[]{name, maxCapacity, getLoad()};
    }


    @Override
    public String toString() {
        return name;
    }



    //a
    public void addTrain(Train t1, Time time) {
        if (trainsOnStation.size() < maxCapacity) {
            if (!trainsOnStation.containsKey(t1)) {
                trainsOnStation.put(t1, new ArrayList<Time>());
            }
            trainsOnStation.get(t1).add(time);
        } else {
            System.err.println("Błąd - stacja przepełniona");
        }
    }
    public void addTrains(ArrayList<Train>t){
        trainsOnStation.clear();
        for(Train train : t){
            System.out.println(train.name);
            addTrain(train,train.departureTime);
        }
    }

    public void removeTrain(String name, Time time) {
        trainsOnStation.forEach(((train, times) -> {
            if (train.name.get().compareTo(name) == 0) {
                times.remove(time);
            }
        }));
    }

    public Train findTrain(String name) {
        for (Train train : trainsOnStation.keySet()) {
            if (train.name.get().compareTo(name) == 0) {
                return train;
            }
        }
        return null;
    }

    ;

    public void findTimeAndReplace(Time newTime, Time oldTime, Train train) {
        List<Time> timeList = trainsOnStation.get(train);
        for (int i = 0; i < timeList.size(); i++) {
            if (timeList.get(i).equals(oldTime)) {
                timeList.set(i, newTime);
                return;
            }
        }
    }

    ;

    //b
    void trainProblems(Train t1) {
        if (t1.state == TrainState.opozniony || t1.state == TrainState.zepsuty) {
            if (trainsOnStation.containsKey(t1)) {
                System.out.println("Pociąg miał problem i został usunięty ze stacji\n");
                trainsOnStation.remove(t1);
            }
        }
    }

    //c
    void removeTrain(Train t1) {
        trainsOnStation.remove(t1);
    }

    //d
    Train searchString(String name) {
        for (Train trains : trainsOnStation.keySet()) {
            if (name.compareTo(trains.name.get()) == 0) {
                return trains;
            }
        }
        System.out.println("Taki pociąg nie istnieje");
        return null;
    }

    //e
    List<Train> listOfTrains(String partOfName) {
        List<Train> trains = new ArrayList<>();
        for (Train train : trainsOnStation.keySet()) {
            if (train.name.get().contains(partOfName)) {
                trains.add(train);
            }
        }
        return trains;
    }

    //f
    int stateOfTrain(Enum state) {
        int i = 0;
        for (Train train : trainsOnStation.keySet()) {
            if (train.state == state) {
                i++;
            }
        }
        return i;
    }

    //g
    void printTrains() {
        int i = 1;
        for (Train train : trainsOnStation.keySet()) {
            System.out.println("Pociąg numer: " + i);
            System.out.println();
            i++;
        }
    }

    //h
    List<Train> sortByName() {
        List<Train> sorted = new ArrayList<>(trainsOnStation.keySet());
        Collections.sort(sorted);
        return sorted;
    }

    //i
    public List<Train> sortByNumber() {
        List<Train> sorted = new ArrayList<>(trainsOnStation.keySet());
        sorted.sort((o1, o2) -> Integer.compare(trainsOnStation.get(o2).size(), trainsOnStation.get(o1).size()));
        return sorted;
    }

    //j
    Train maxTime() {
        List<Train> trains = new ArrayList<>(trainsOnStation.keySet());
//        return Collections.max(trains, new Comparator<Train>() {
//            @Override
//            public int compare(Train o1, Train o2) {
//                return o1.compareTo(o2);
//            }
//        });
        return Collections.max(trains, (o1, o2) -> {
            return Integer.compare(o1.travelTime, o2.travelTime);
        });
    }

    public int getLoad() {
        int i = 0;
        for (Train train : trainsOnStation.keySet()) {
            i += trainsOnStation.get(train).size();
        }
        return i;
    }

    @Override
    public int compareTo(TrainStation ts1) {

        return Integer.compare(maxCapacity, ts1.maxCapacity);
    }

    public int compareByLoad(TrainStation ts1) {
        return Integer.compare(getLoad(), ts1.getLoad());
    }


}
