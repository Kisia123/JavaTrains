package main.zad1;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {

    @Test
    void run_Correct_TimeUnder1Second() {
        SortStrategy sS = new BubbleSort();
        assertTimeout(Duration.ofMillis(1000),()-> new main.zad1.Test().run(sS));
    }
    @Test
    void testArray_Correct_TimeSmallArray() {
        SortStrategy sS = new BubbleSort();
        int n  = 100;
        int [] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = ThreadLocalRandom.current().nextInt(0, 4 * n);
        }
        assertTimeout(Duration.ofMillis(1),()-> main.zad1.Test.testArray(sS,tab));
    }
    @Test
    void testArray_Correct_TimeMediumArray() {
        SortStrategy sS = new BubbleSort();
        int n  = 1000;
        int [] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = ThreadLocalRandom.current().nextInt(0, 4 * n);
        }
        assertTimeout(Duration.ofMillis(30),()-> main.zad1.Test.testArray(sS,tab));
    }
    @Test
    void testArray_Correct_TImeBigArray() {
        SortStrategy sS = new BubbleSort();
        int n  = 10000;
        int [] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = ThreadLocalRandom.current().nextInt(0, 4 * n);
        }
        assertTimeout(Duration.ofMillis(300),()-> main.zad1.Test.testArray(sS,tab));
    }

}