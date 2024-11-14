package nl.roykovic.aoc._2016.day10_bots;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Stream;
public class BotsFactory {
    public int generate(Stream<String> input, int lowToLookFor, int highToLookFor, boolean stop) {
        Map<Integer, Bot> bots = new HashMap<>();
        Map<Integer, Receiver> outputs = new HashMap<>();

        List<String> initInstructions = new ArrayList<>();

        var botsList = input.map(s -> {
            s = s.trim();
            if(s.contains("value")){
                initInstructions.add(s);
                return null;
            }
            else{
                int curBot = Integer.parseInt(StringUtils.substringBetween(s, "bot ", " gives"));

                String low = StringUtils.substringBetween(s, "low to bot "," and high");
                String high = StringUtils.substringAfter(s, "and high to bot ");

                String lowOutput = StringUtils.substringBetween(s, "low to output "," and high");
                String highOutput = StringUtils.substringAfter(s, "and high to output ");

                Integer lowBot = null;
                Integer highBot = null;

                Integer lowOutputInt = null;
                Integer highOutputInt = null;

                if(low != null && !low.isEmpty()){
                    lowBot = Integer.parseInt(low);
                }
                else{
                    lowOutputInt = Integer.parseInt(lowOutput);
                    outputs.put(lowOutputInt, new Output(lowOutputInt));
                }
                if(!high.isEmpty()){
                    highBot = Integer.parseInt(high);
                }
                else{
                    highOutputInt = Integer.parseInt(highOutput);
                    outputs.put(highOutputInt, new Output(highOutputInt));
                }

                Bot b = new Bot(curBot, lowBot, highBot, lowOutputInt, highOutputInt);

                bots.put(curBot, b);

                return b;
            }
        }).filter(Objects::nonNull).toList();

        for(Bot b : botsList){

            if(b.low != null){
                b.lowReceiver = bots.get(b.low);
            }
            else{
                b.lowReceiver = outputs.get(b.lowOutput);
            }

            if(b.high != null){
                b.highReceiver = bots.get(b.high);
            }
            else{
                b.highReceiver = outputs.get(b.highOutput);
            }

        }


        for(String instruction : initInstructions){
            int value = Integer.parseInt(StringUtils.substringBetween(instruction, "value ", " goes"));
            int bot = Integer.parseInt(StringUtils.substringAfter(instruction, "to bot "));

            bots.get(bot).accept(value);
        }


        Bot botToLookFor = null;

        if(stop) {
            while (botToLookFor == null) {
                for (Bot b : botsList) {
                    botToLookFor = b.proceed(lowToLookFor, highToLookFor, true);
                    if (botToLookFor != null) {
                        break;
                    }
                }
            }

            return botToLookFor.getNumber();
        }

        Output output0 = (Output) outputs.get(0);
        Output output1 = (Output) outputs.get(1);
        Output output2 = (Output) outputs.get(2);

        while(output0.chips.isEmpty() || output1.chips.isEmpty() || output2.chips.isEmpty()){
            for(Bot b: botsList){
                b.proceed(0,0,false);
            }
        }

        return output0.chips.get(0) * output1.chips.get(0) *output2.chips.get(0);

    }
}
