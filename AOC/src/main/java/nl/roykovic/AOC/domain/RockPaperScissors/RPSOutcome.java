package nl.roykovic.AOC.domain.RockPaperScissors;

public enum RPSOutcome {
    LOSS(0, 'X'),
    DRAW(3, 'Y'),
    WIN(6, 'Z');

    private final int score;
    private final char character;

    RPSOutcome(int score, char character) {
        this.score = score;
        this.character = character;
    }

    public int getScore() {
        return score;
    }

    public static RPSOutcome getByChar(char character){
        for(RPSOutcome outcome : values()){
            if(outcome.character == character){
                return outcome;
            }
        }
        throw new IllegalArgumentException(String.valueOf(character));
    }
}
