package nl.roykovic.aoc.utils;

import java.util.Map;

public enum Direction {
    R(1,0),
    D(0,1),
    L(-1,0),
    U(0,-1),
    UL(-1,-1),
    UR(1,-1),
    DL(-1,1),
    DR(1,1);

    private final int additionalX;
    private final int additionalY;

    Direction(int additionalX, int additionalY) {
        this.additionalX = additionalX;
        this.additionalY = additionalY;
    }

    public static final Map<Direction,Direction> OPPOSITE = Map.of(
            U, D,
            D,U,
            L,R,
            R,L
    );


    public int getAdditionalX() {
        return additionalX;
    }

    public int getAdditionalY() {
        return additionalY;
    }

    public static Direction getByCoords(int x, int y){
        for (Direction d : values()) {
            if (d.additionalX == x && d.additionalY == y) {
                return d;
            }
        }
        throw new IllegalArgumentException(x + " " + y);
    }

    public static Direction getByCoords(Coord a, Coord b){

        int x = (int) (b.getX() -a.getX());
        int y = (int) (b.getY() - a.getY());

        return getByCoords(x,y);
    }

    public static Direction getFromViewingDirection(Direction viewingDirection, Direction original){
        return switch (viewingDirection){
            case D -> OPPOSITE.get(original);
            case U -> original;
            case R -> {
                int newOrdinal = original.ordinal() +1;
                if(newOrdinal > 3){
                    newOrdinal = 1;
                }
                yield Direction.values()[newOrdinal];
            }
            case L -> {
                int newOrdinal = original.ordinal() -1;
                if(newOrdinal < 0){
                    newOrdinal = 3;
                }
                yield Direction.values()[newOrdinal];
            }
            default -> throw new RuntimeException();
        };
    }
}
