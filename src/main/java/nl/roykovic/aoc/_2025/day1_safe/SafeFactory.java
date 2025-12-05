package nl.roykovic.aoc._2025.day1_safe;

import java.util.List;
public class SafeFactory {
    public int generate(List<String> input, boolean p2) {
        int password = 0;
        int current = 50;


        for(String line : input){
            char operation = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));
            int step = 0;

            while(step < value){
                current = operation == 'L' ? current - 1 : current + 1;
                if(current == 100){
                    current = 0;
                }
                if(current == -1){
                    current = 99;
                }
                if(current == 0 && p2){
                    password++;
                }
                step++;

            }
            if(current == 0 && !p2){
                password++;
            }

        }

        return password;
    }
}
