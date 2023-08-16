package nl.roykovic.aoc._2022.monkeymap;

import nl.roykovic.aoc._2022.monkeymath.MonkeyMathFactory;
import nl.roykovic.aoc.utils.Direction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonkeyMapFactoryTest {
    @Test
    void testExampleRoute() throws IOException{
        File input = new File("src/test/resources/2022/MonkeyMapTestInput.txt");
        MonkeyMap monkeyMap = new MonkeyMapFactory().generateFromFile(input);

        int currentY = 0;
        int currentX = ArrayUtils.indexOf(monkeyMap.getMap()[0], '.');
        Direction[] directions = {Direction.R, Direction.D, Direction.L, Direction.U};
        int currentDirection = 0;

        for(String instruction: monkeyMap.getInstructions()){
            if(NumberUtils.isCreatable(instruction)){
                int numberInstruction = NumberUtils.toInt(instruction);
                for(int i = 0; i < numberInstruction; i++){
                    int newX = currentX + directions[currentDirection].getAdditionalX();
                    int newY = currentY + directions[currentDirection].getAdditionalY();

                    if(monkeyMap.getMap()[newY][newX] != '#'){
                        System.out.println("move");
                        currentX = newX;
                        currentY = newY;
                    }
                    else{
                        System.out.println("wall");
                        break;
                    }
                }
            }
            else{
                if(Objects.equals(instruction, "R")){
                    currentDirection++;
                }
                else{
                    currentDirection--;
                }
                System.out.println("changed direction to " + directions[currentDirection]);
            }
        }


        System.out.println(monkeyMap);
    }
}
