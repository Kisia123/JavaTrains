package main.zad5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Zad5Test {

    @Test
    void kaprekar_NoExceptionAndCorrectResult_ValidInput() {
        int n = 9876;
        int result = 3;
        assertDoesNotThrow(()->assertEquals(result,new Zad5().kaprekar(n)));
    }
    @Test
    void kaprekar_ThrowsException_IfToSmall() {
        int n = -9876;
        Exception ex = assertThrows(IllegalKaprekarArgumentException.class, () -> new Zad5().kaprekar(n));
        assertEquals("\nPodana liczba nie spełnia wymagań algorytmu\nLiczba musi być większa od 0", ex.getMessage());
    }
    @Test
    void kaprekar_ThrowsException_IfToBig() {
        int n = 12000;
        Exception ex = assertThrows(IllegalKaprekarArgumentException.class, () -> new Zad5().kaprekar(n));
        assertEquals("\nPodana liczba nie spełnia wymagań algorytmu\nLiczba musi być czterocyfrowa", ex.getMessage());
    }
    @Test
    void kaprekar_ThrowsException_IfEveryDigitIsTheSame() {
        int n = 2222;
        Exception ex = assertThrows(IllegalKaprekarArgumentException.class, () -> new Zad5().kaprekar(n));
        assertEquals("\nPodana liczba nie spełnia wymagań algorytmu\nLiczba musi mieć przynajmniej dwie różne cyfry", ex.getMessage());
    }

}