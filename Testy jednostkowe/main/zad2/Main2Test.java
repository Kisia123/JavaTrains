package main.zad2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Main2Test {

    @Test
    void main_NoException_NormalRun() {
        assertDoesNotThrow(()-> Main.main(null));
    }
}