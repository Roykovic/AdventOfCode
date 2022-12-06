package nl.roykovic.AOC.domain.RockPaperScissors;

public enum RPSOutcome {
    LOSS(0),
    DRAW(3),
    WIN(6);

    private final int score;

    RPSOutcome(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
