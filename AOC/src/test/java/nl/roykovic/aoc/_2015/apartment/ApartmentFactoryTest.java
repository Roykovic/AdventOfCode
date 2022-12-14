package nl.roykovic.aoc._2015.apartment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApartmentFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "(()),0",
            "()(),0",
            "(((,3",
            "))(((((,3",
            "(()(()(,3",
            "()),-1",
            "))(,-1",
            "))),-3",
            ")())()),-3",
            })
    void testExampleFloor(String input, int expectedFloor) {
        int floor = new ApartmentFactory().generateFromString(input).sum();
        assertEquals(expectedFloor, floor);
    }

    @Test
    void testActualFloor() throws IOException {
        File input = new ClassPathResource("2015/ApartmentInput.txt").getFile();
        int floor = new ApartmentFactory().generateFromFile(input).sum();
        assertEquals(138, floor);
    }

    @ParameterizedTest
    @CsvSource({
            "),1",
            "()()),5"
    })
    void testExampleBasementInstruction(String input, int expectedFloor) {

        int[] instructions = new ApartmentFactory().generateFromString(input).toArray();

        int floor = 0;
        int instruction =0;

        for(int i = 0; i< instructions.length && floor != -1; i++){
                floor += instructions[i];
                instruction = i +1;
        }

        assertEquals(expectedFloor, instruction);
    }

    @Test
    void testActualBasementInstruction() throws IOException {

        File input = new ClassPathResource("2015/ApartmentInput.txt").getFile();
        int[] instructions = new ApartmentFactory().generateFromFile(input).toArray();

        int floor = 0;
        int instruction =0;

        for(int i = 0; i< instructions.length && floor != -1; i++){
            floor += instructions[i];
            instruction = i +1;
        }

        assertEquals(1771, instruction);
    }
}