package main.zad1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    @Test
    void sort_Correct_Unsorted() {
            int[] sorted = {1, 2, 3, 4, 5};
            int[] unsorted = {2, 5, 4, 1, 3};
            int[] actual = new InsertionSort().sort(unsorted);
            assertArrayEquals(sorted, actual);
        }
    @Test
    void sort_Correct_Sorted() {
        int[] sorted = {1, 2, 3, 4, 5};
        int[] unsorted = {1, 2, 3, 4, 5};
        int[] actual = new InsertionSort().sort(unsorted);
        assertArrayEquals(sorted, actual);
    }
    @Test
    void sort_ThrowException_NullArray(){
        int[] unsorted = null;
        assertThrows(NullPointerException.class, ()->new InsertionSort().sort(unsorted));
    }
}