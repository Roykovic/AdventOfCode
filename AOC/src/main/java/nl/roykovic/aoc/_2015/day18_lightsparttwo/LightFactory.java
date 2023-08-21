package nl.roykovic.aoc._2015.day18_lightsparttwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class LightFactory{

        public boolean[][] generateFromFile(File file) throws FileNotFoundException {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = reader.lines().toList();

            boolean[][] lights = new boolean[lines.size()][lines.size()];
            for(int x = 0; x < lines.size(); x++){
                String line = lines.get(x);
                for(int y = 0; y < line.length(); y++){
                    lights[x][y] = line.charAt(y) == '#';
                }
            }
            return lights;
        }

        public boolean[][] animate(boolean[][] lights){

            boolean[][] newLights = new boolean[lights.length][lights.length];

            for(int x = 0; x < lights.length; x++){
                boolean[] lightsAtX = lights[x];
                for(int y = 0; y < lightsAtX.length; y++){
                    newLights[x][y] = toggle(lights, x, y);
                }
            }
            return newLights;
        }

        private boolean toggle(boolean[][] lights, int x, int y){
            int neighbourCounter = 0;

            outerLoop: for(int neighbourX = x -1; neighbourX <= x+1; neighbourX++){
                for(int neighbourY = y-1; neighbourY <= y+1; neighbourY++){
                    if((neighbourX != x || neighbourY != y) && neighbourX >= 0 && neighbourY >= 0 && neighbourX < lights.length && neighbourY < lights.length && lights[neighbourX][neighbourY]){
                        neighbourCounter++;
                    }
                    if(neighbourCounter > 3){
                        break outerLoop;
                    }
                }
            }

            var uitkomst = neighbourCounter == 3 || (neighbourCounter == 2 && lights[x][y]);
            return uitkomst ;
        }

}
