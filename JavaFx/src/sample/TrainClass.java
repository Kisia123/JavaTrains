package sample;

public enum TrainClass {
    class1("1 klasa"),class2("2 klasa"),class3("3 klasa");
    public String displayName;
    TrainClass(String d){
        this.displayName = d;
    }
    @Override
    public String toString(){
        return displayName;
    }
}
