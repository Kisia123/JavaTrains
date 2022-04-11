package main.zad3;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Zad3Test {

    @Test
    void l_Correct_TimeUnder10Millis() {
        assertTimeout(Duration.ofMillis(10), () -> new Zad3().l());
    }

    @Test
    void l_Correct_ListNotNull() {
        assertNotNull(new Zad3().l());
    }



    @Test
    void missingNum_NoException_CorrectNumber() {
        int m = 5;
        List<Integer> a = new ArrayList<>(
                Arrays.asList(1,3,5)
        );
        int[]result = {2,4};
        assertDoesNotThrow(() ->  assertArrayEquals(result,new Zad3().missingNum(a,m)));

    }
    @Test
    void missingNum_ThrowsException_NumberBelowZero() {
        int m = -5;
        List<Integer> a = new ArrayList<>(
                Arrays.asList(1,3,5)
        );
        Exception ex = assertThrows(Exception.class, () -> new Zad3().missingNum(a,m));
        assertEquals("Liczba powinna być większa od 0", ex.getMessage());

    }
    @Test
    void missingNum_ThrowsException_NumberInArrayBelowZero() {
        int m = 5;
        List<Integer> a = new ArrayList<>(
                Arrays.asList(1,-3,5)
        );
        Exception ex = assertThrows(Exception.class, () -> new Zad3().missingNum(a,m));
        assertEquals("Liczby powinny być większe od 0", ex.getMessage());

    }
}