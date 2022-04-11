package com.pro;

import com.pro.frames.AdminFrame;

/*interface LL{
	void f(int x,int t);
}*/
public class Main {
    static public Controller controller = new Controller();
    public static void main(String[] args) {
       new AdminFrame();

   /* 	LL ll = (x,t)-> {
			System.out.println(x+"+"+t);
		};

    	ll.f(2,3);
    //tworzenie pociągów
	    Train t1 = new Train("nn12",5,nowy);
	    Train t2 = new Train("okn67",4,opozniony);
	    Train t3 = new Train("kk89",6,nowy);
	    Train t4 = new Train("aaa123",6,nowy);
	    Train t5 = new Train("k456",6,nowy);
	    Train t6 = new Train("k121",6,nowy);
	    Train t7 = new Train("kjkl",6,nowy,30);
	    Train t8 = new Train("kppppp",6,nowy,70);
	    Train t9 = new Train("kkkkkkk",6,nowy);

	    //tworzenie stacji
        TrainStationContainer stacje = new TrainStationContainer();
        stacje.addStation("tt",15);

		System.out.println("Stacje wraz z ilością pociągów");
        stacje.printStation();

        //getstation
		TrainStation tt =stacje.getStation("tt");

    	//dodwanie pociagów do stacji
        tt.addTrain(t1,new Time(1,34));
        tt.addTrain(t2,new Time(1,34));
        tt.addTrain(t3,new Time(1,34));
        tt.addTrain(t4,new Time(1,34));
        tt.addTrain(t5,new Time(1,34));
        tt.addTrain(t5,new Time(2,34));
        tt.addTrain(t6,new Time(5,34));
        tt.addTrain(t6,new Time(1,34));
        tt.addTrain(t6,new Time(2,37));
        tt.addTrain(t7,new Time(1,34));
        tt.addTrain(t8,new Time(1,34));
        tt.addTrain(t9,new Time(1,34));

		//problem z pociagami
		System.out.println("Sprawdzenie czy pociąg ma problem");
		tt.trainProblems(t2);


		//usunięcie pociągu ze stacji
		System.out.println("Usunięcie pociągu ze stacji");
		System.out.println("Pociągi przed usunięciem:");
		tt.printTrains();
		tt.removeTrain(t8);
		System.out.println("Pociagi po usunięciu");
		tt.printTrains();

		//zwracanie pociągu o danej nazwie
		tt.searchString("aaa123");

		//fragment nazwy i zwrot listy pociągow
		System.out.println("Pociągi z podanym fragmentem nazwy");
		List<Train> ttt=tt.listOfTrains("p");
		for(Train train:ttt){
			System.out.println(train.name);
		}

		//ilosc produktow o danym stanie
		System.out.println("\nIlość produktow o podanym stanie:");
		System.out.println("ilosc: "+tt.stateOfTrain(nowy));

		//sortowania
        System.out.println("\nposortowanie po nazwie:");
        for(Train train: tt.sortByName()){
            System.out.println(train.name);
        }
        System.out.println("\nposortowanie po liczbie odjazdow:");
        for(Train train: tt.sortByNumber()){
            System.out.println(train.name);
        }
        //najdluzej jadacy
		System.out.println("\nnajdluzej jadacy");
        System.out.println(tt.maxTime().name);

		//metody stationcontainer
		stacje.addStation("tt2",10);
		stacje.addStation("tt3",10);

		//lista pustych stacji
		System.out.println("\nLista pustych stacji");
		List<TrainStation>puste=stacje.emptyStations();
		for(TrainStation stacja:puste){
			System.out.println(stacja.name);
		}

		//usuwanie stacji
		System.out.println("\nStacje przed usunięciem:");
		stacje.printStation();
		stacje.removeStation("tt3");
		System.out.println("Stacje po usnieciu:");
    	stacje.printStation();

*/

    }

}