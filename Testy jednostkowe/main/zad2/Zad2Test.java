package main.zad2;

import main.zad1.BubbleSort;
import main.zad1.SortStrategy;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Zad2Test {

    @Test
    void l_Correct_TimeUnder10Millis() {
        assertTimeout(Duration.ofMillis(10), () -> new Zad2().l());
    }

    @Test
    void l_Correct_ArrayNotNull() {
        assertNotNull(new Zad2().l());
    }

    @Test
    void spot_WorksFine_CorrectNumbers() {
        int d = 9;
        List<Integer> l = new ArrayList<>(
                Arrays.asList(1, 2, 4, 0, 5, 6)
        );
        int[] result = {2, 4};
        assertDoesNotThrow(() -> assertArrayEquals(result, Zad2.spot(l, d)));

    }

    @Test
    void spot_ThrowsException_IfSolutionDoesNotExist() {
        int d = 9;
        List<Integer> l = new ArrayList<>(
                Arrays.asList(1, 2, 3, 0, 5)
        );
        Exception ex = assertThrows(Exception.class, () -> Zad2.spot(l, d));
        assertEquals("brak rozwiązania", ex.getMessage());
    }
}