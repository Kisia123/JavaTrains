package main.zad1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Main1Test {

    @Test
    void main_NoException_NormalRun() {
        assertDoesNotThrow(()->Main.main(null));
    }
}