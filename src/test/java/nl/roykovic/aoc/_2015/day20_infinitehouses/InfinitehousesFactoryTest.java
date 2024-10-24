package nl.roykovic.aoc._2015.day20_infinitehouses;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InfinitehousesFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "1,10",
            "2,30",
            "3,40",
            "4,70",
            "5,60",
            "6,120",
            "7,80",
            "8,150",
            "9,130",
            "960,30480"
    })
    public void test(int houseNumber, int presents) {
        var output = new InfinitehousesFactory().calculatePresents(houseNumber);

        assertEquals(presents, output);
    }


    @Test
    public void test29000(){

        var factory = new InfinitehousesFactory();
        boolean found = false;
        int i = 0;

        while(!found){
            i++;
            found = factory.calculatePresents(i) >= 29000000;
        }

        assert(i > 960);
        assertEquals(665280,i);
    }

    @Test
    public void test29000two(){

        var factory = new InfinitehousesFactory();
        boolean found = false;
        int i = 665280;

        while(!found){
            i++;
            found = factory.calculatePresentsTwo(i) >= 29000000;
        }

        assert(i > 960);
        assert(i < 705601);
        assertEquals(705600,i);
    }
}
