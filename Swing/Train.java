package com.pro;

public class Train implements Comparable<Train> {
    public String name;
    public int numberOfSeats;
    public TrainState state;
    int travelTime = 60;
    public Train(String name,int number,TrainState state){
        this.name=name;
        numberOfSeats =number;
        this.state=state;

    }
    Train(String name,int number,TrainState state,int tt){
        this.name=name;
        numberOfSeats =number;
        this.state=state;
        travelTime=tt;
    }
    void print(){
        System.out.println("informacje:");
        System.out.println("nazwa: "+name);
        System.out.println("ilosc miejsc: "+ numberOfSeats);
        System.out.println("stan: "+state);
    }
    public Object[] printToTable(){
        return new Object[]{name,numberOfSeats,state};
    }


    @Override
    public int compareTo(Train t1) {
        return  name.compareTo(t1.name);
    }

}
