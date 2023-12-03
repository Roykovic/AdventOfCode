package nl.roykovic.aoc._2023.day3_partnumbers;

import nl.roykovic.aoc.utils.Coord;
import nl.roykovic.aoc.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PartNumbersFactory {
    public int generate(Stream<String> input){

       List<String> inputList = input.toList();

       List<Coord> specialChars = new ArrayList<>();
        List<GridNumber> matches = new ArrayList<>();

        for(int y = 0; y< inputList.size(); y++) {
            String currentRow = inputList.get(y);

            Matcher numberMatcher = Pattern.compile("[0-9]+").matcher(currentRow);

            while(numberMatcher.find()) {
                GridNumber gn = new GridNumber(new Coord(numberMatcher.start(), y), new Coord(numberMatcher.end()-1, y), numberMatcher.group());
                matches.add(gn);
            }

            Matcher charMatcher = Pattern.compile("[^A-Za-z0-9\\. ]").matcher(currentRow);

            while(charMatcher.find()) {
                specialChars.add(new Coord(charMatcher.start(), y));
            }
        }

        for(Coord specialChar : specialChars) {
            getNumericalNeighbours(matches, specialChar);
        }
        return matches.stream().filter(GridNumber::isPartNumber).mapToInt(GridNumber::getValue).sum();
    }

    private void getNumericalNeighbours(List<GridNumber> numbers, Coord coord){

        List<Coord> neighbours = new ArrayList<>();

        Coord o = new Coord(coord);

        coord.move(Direction.UL);
        neighbours.add(new Coord(coord));
        coord.move(Direction.R);
        neighbours.add(new Coord(coord));
        coord.move(Direction.R);
        neighbours.add(new Coord(coord));
        coord.move(Direction.D);
        neighbours.add(new Coord(coord));
        coord.move(Direction.D);
        neighbours.add(new Coord(coord));
        coord.move(Direction.L);
        neighbours.add(new Coord(coord));
        coord.move(Direction.L);
        neighbours.add(new Coord(coord));
        coord.move(Direction.U);
        neighbours.add(new Coord(coord));

        for(Coord neighbour : neighbours){
            for(GridNumber number : numbers){
                if(number.contains(neighbour)){
                    number.setPartNumber(true);
                }
            }
        }
    }

    private static class GridNumber{
        Coord start;
        Coord end;

       String value;

        public GridNumber(Coord start, Coord end, String value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        boolean contains(Coord coord){
            return Objects.equals(coord.getY(), start.getY()) && coord.getX() >= start.getX() && coord.getX() <= end.getX();
        }

        @Override
        public String toString() {
            return value;
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

        boolean partNumber = false;
    }
}
