package nl.roykovic.AOC.domain.RockPaperScissors;

import static nl.roykovic.AOC.domain.RockPaperScissors.RPSMove.WINS;

public class RPSGame {
    private final RPSMove opponentsMove;
    private final RPSMove myMove;

    public RPSGame(char opponentsMove, char myMove){
        this(RPSMove.getByChar(opponentsMove), RPSMove.getByChar(myMove));
    }

    public RPSGame(char opponentsMove, char outcome, boolean placeHolder){   //elves solution, boolean just serves the purpose of differentiating between the two constructors
        this(RPSMove.getByChar(opponentsMove), RPSMove.getByOutcome(RPSMove.getByChar(opponentsMove), RPSOutcome.getByChar(outcome)));
    }

    public RPSGame(RPSMove opponentsMove, RPSMove myMove) {
        this.opponentsMove = opponentsMove;
        this.myMove = myMove;
    }

    private RPSOutcome determineOutcome(){
        if(opponentsMove.equals(myMove)){
            return RPSOutcome.DRAW;
        }
        if(WINS.get(myMove).equals(opponentsMove)){
            return RPSOutcome.WIN;
        }
        return RPSOutcome.LOSS;
    }

    public int determineScore(){
        return determineOutcome().getScore() + myMove.getScore();
    }
}
