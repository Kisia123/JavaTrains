package sample;

public enum Discount {
    brak("brak zniżki"),student("zniżka uczniowska/studencka"), emeryt("zniżka emerytalna");
    public String displayName;
    Discount(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString(){
        return displayName;
    }
}
