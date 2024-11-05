package nl.roykovic.aoc._2015.day25_instructionmanual;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InstructionmanualFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "2,1,31916031",
            "2947,3029,19980801",
    })
    public void test(int row, int col, int expected) {
        InstructionmanualFactory factory = new InstructionmanualFactory();

        int ordinal = factory.findOrdinalByRowAndCol(row, col);

        var output = factory.generate(ordinal);

        assertEquals(expected, output);
    }

    @Test
    public void testOrdinalCheck(){

        InstructionmanualFactory factory = new InstructionmanualFactory();

        int[][] input = new int[][] {
                { 1,3,6,10,15,21},
                { 2,5,9,14,20},
                { 4,8,13,19 },
                { 7,12,18 },
                { 11,17},
                { 16}
        };

        for(int x = 1; x <=6; x++){
            for(int y = 1; y <=6; y++){
                if(input[x-1].length >= y) {
                    assertEquals(input[x - 1][y - 1], factory.findOrdinalByRowAndCol(x, y));
                }
            }
        }


    }
}