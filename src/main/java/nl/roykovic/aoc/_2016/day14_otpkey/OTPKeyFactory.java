package nl.roykovic.aoc._2016.day14_otpkey;

import nl.roykovic.aoc._2015.day4_adventcoin.MD5Encoder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
public class OTPKeyFactory {
    public int generate(String input, boolean stretchedKeys) {
        int index = 0;
        int found = 0;

        Map<Integer, String> foundRepeatingCharacters = new HashMap<>();
        List<Integer> foundIndexes = new ArrayList<>();


        while(true){
            String hash = MD5Encoder.encode(input+index);

            if(stretchedKeys){
                hash = generateStretchedKey(hash);
            }

                Pattern p = Pattern.compile("(.)\\1{2,}");
                Matcher m = p.matcher(hash);
                if (m.find()) {

                    String foundSubstring = m.group();
                    foundRepeatingCharacters.put(index, foundSubstring);

                    if(foundSubstring.length() > 4){

                        if(index == 22804){
                            System.out.println();
                        }

                        int start = Math.max(index-1000, 0);

                        for(; start< index; start++){
                            if(foundRepeatingCharacters.containsKey(start) && foundRepeatingCharacters.get(start).charAt(0) == foundSubstring.charAt(0)){
                                foundIndexes.add(start);
                                found++;
                            }
                        }
                    }

                }
            if(found > 64 && foundIndexes.stream().mapToInt(it -> it).max().orElseThrow() < index-10000){
                break;
            }
            index++;
        }

        return foundIndexes.stream().mapToInt(it -> it).distinct().sorted().limit(64).max().orElseThrow();
    }
    private String generateStretchedKey(String input) {
        String hash = input;
        for(int i = 0; i < 2016; i++) {
            hash = MD5Encoder.encode(hash);
        }

        return hash;
    }
}
