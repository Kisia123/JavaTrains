package main.zad1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {

    @Test
    void sort_Correct_Unsorted() {
        int[] sorted = {1, 2, 3, 4, 5};
        int[] unsorted = {2, 5, 4, 1, 3};
        int[] actual = new BubbleSort().sort(unsorted);
        assertArrayEquals(sorted, actual);

    }
    @Test
    void sort_Correct_Sorted() {
        int[] sorted = {1, 2, 3, 4, 5};
        int[] unsorted = {1, 2, 3, 4, 5};
        int[] actual = new BubbleSort().sort(unsorted);
        assertArrayEquals(sorted, actual);
    }
    @Test
    void sort_ThrowException_NullArray(){
        int[] unsorted = null;
        assertThrows(NullPointerException.class, ()->new BubbleSort().sort(unsorted));
    }
    @Test
    void sort_Correct_ArraySizeIsZero(){
        int[] expected = new int[]{};
        int[] unsorted = new int[]{};
        int[] actual = new BubbleSort().sort(unsorted);
        assertArrayEquals(expected,actual);
    }
}