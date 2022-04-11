package main.zad4;

import main.zad2.Zad2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Zad4Test {
    @Test
    void list_Correct_ListNotNull() {
        assertNotNull(Main.list);
    }

    @Test
    void reorderDigitsAsc_NoException_CorrectNumbers() {
        List<Integer> l = new ArrayList<>(
                Arrays.asList(132, 465)
        );
        List<Integer> result = new ArrayList<>(
                Arrays.asList(123, 456)
        );
        assertDoesNotThrow(() -> assertEquals(result, new Zad4().reorderDigits(l, SortType.asc)));
    }

    @Test
    void reorderDigitsDesc_NoException_CorrectNumbers() {
        List<Integer> l = new ArrayList<>(
                Arrays.asList(132, 465)
        );
        List<Integer> result = new ArrayList<>(
                Arrays.asList(321, 654)
        );
        assertDoesNotThrow(() -> assertEquals(result, new Zad4().reorderDigits(l, SortType.desc)));
    }

    @Test
    void reorderDigits_ThrowsException_NumberBelowZero() {
        List<Integer> l = new ArrayList<>(
                Arrays.asList(-132, 465)
        );
        Exception ex = assertThrows(Exception.class, () -> new Zad4().reorderDigits(l, SortType.asc));
        assertEquals("Bledna wartosc", ex.getMessage());
    }
}