package nl.roykovic.aoc.rockpaperscissors;

import java.util.Map;

public enum RPSMove{
    ROCK(1, 'A', 'X'),
    PAPER(2, 'B', 'Y'),
    SCISSORS(3, 'C', 'Z');

    public static final Map<RPSMove,RPSMove> WINS = Map.of(
            ROCK, SCISSORS,//Rock defeats Scissors
            SCISSORS,PAPER,//Scissors defeats Paper
            PAPER,ROCK//Paper defeats Rock
    );

    private final int score;
    private final char opponentChar;
    private final char myChar;

    RPSMove(int score, char opponentChar, char myChar){
        this.score = score;
        this.opponentChar = opponentChar;
        this.myChar = myChar;
    }

    public int getScore() {
        return score;
    }

    public static RPSMove getByChar(char character){
        for(RPSMove move : values()){
            if(move.myChar == character || move.opponentChar == character){
                return move;
            }
        }
        throw new IllegalArgumentException(String.valueOf(character));
    }

    public static RPSMove getByOutcome(RPSMove opponentMove, RPSOutcome outcome){

        return switch (outcome) {
            case DRAW -> opponentMove;
            case LOSS -> WINS.get(opponentMove);
            case WIN -> WINS.entrySet().stream().filter(m -> m.getValue().equals(opponentMove)).map(Map.Entry::getKey).findFirst().orElseThrow(IllegalArgumentException::new);
        };

    }
}
