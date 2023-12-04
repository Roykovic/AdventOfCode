package nl.roykovic.aoc._2023.day3_partnumbers;

import nl.roykovic.aoc.utils.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PartNumbersFactory {
    public int generatePartNumbers(Stream<String> input){
        List<String> inputList = input.toList();
        List<Coord> specialChars = new ArrayList<>();
        List<GridNumber> numbers = new ArrayList<>();

        buildNumbersAndChars(inputList, numbers, specialChars, "[^A-Za-z0-9\\. ]");

        for(Coord specialChar : specialChars) {
            visitPartNumbers(numbers, specialChar);
        }
        return numbers.stream().filter(GridNumber::isPartNumber).mapToInt(GridNumber::getValue).sum();
    }

    public int generateGearRatios(Stream<String> input){

        List<String> inputList = input.toList();
        List<Coord> specialChars = new ArrayList<>();
        List<GridNumber> numbers = new ArrayList<>();

        buildNumbersAndChars(inputList, numbers, specialChars, "[\\*]");
        return specialChars.stream().mapToInt(c -> calculateGearRatio(numbers, c)).sum();
    }

    private void buildNumbersAndChars(List<String> inputList, List<GridNumber> numbers, List<Coord> specialChars, String regex){
        for(int y = 0; y< inputList.size(); y++) {
            String currentRow = inputList.get(y);

            Matcher numberMatcher = Pattern.compile("[0-9]+").matcher(currentRow);

            while(numberMatcher.find()) {
                numbers.add(new GridNumber(new Coord(numberMatcher.start(), y), new Coord(numberMatcher.end()-1, y), numberMatcher.group()));
            }

            Matcher charMatcher = Pattern.compile(regex).matcher(currentRow);

            while(charMatcher.find()) {
                specialChars.add(new Coord(charMatcher.start(), y));
            }
        }
    }
    private void visitPartNumbers(List<GridNumber> numbers, Coord coord){
        for(Coord neighbour : coord.getNeighbours()){
            for(GridNumber number : numbers){
                if(number.contains(neighbour)){
                    number.setPartNumber(true);
                }
            }
        }
    }

    private int calculateGearRatio(List<GridNumber> numbers, Coord coord){
        List<GridNumber> numberNeighbours = new ArrayList<>();
        for(Coord neighbour : coord.getNeighbours()){
            for(GridNumber number : numbers){
                if(number.contains(neighbour)){
                    if(!number.partNumber) {
                        number.setPartNumber(true);
                        numberNeighbours.add(number);
                    }
                }
            }
        }
        if(numberNeighbours.size() == 2){
            return numberNeighbours.stream().mapToInt(GridNumber::getValue).reduce((a, b) -> a*b).orElseThrow(RuntimeException::new);
        }
        return 0;
    }

    private static class GridNumber{
        final Coord start;
        final Coord end;
        final String value;
        boolean partNumber = false;

        public GridNumber(Coord start, Coord end, String value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        boolean contains(Coord coord){
            return Objects.equals(coord.getY(), start.getY()) && coord.getX() >= start.getX() && coord.getX() <= end.getX();
        }

        public void setPartNumber(boolean partNumber) {
            this.partNumber = partNumber;
        }

        public boolean isPartNumber() {
            return partNumber;
        }

        public int getValue() {
            return Integer.parseInt(value);
        }
        @Override
        public String toString() {
            return value;
        }
    }
}
