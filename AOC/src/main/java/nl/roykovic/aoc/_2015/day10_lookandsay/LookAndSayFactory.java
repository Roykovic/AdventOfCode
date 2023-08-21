package nl.roykovic.aoc._2015.day10_lookandsay;

public class LookAndSayFactory {
    public String lookAndSay(String input){

        int count = 1;
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            if(i < input.length() -1 && input.charAt(i+1) == input.charAt(i)){
                count++;
            }
            else {
                output.append(count);
                output.append(input.charAt(i));
                count = 1;
            }
        }


        return output.toString();
    }
}
