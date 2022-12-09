package nl.roykovic.aoc.ropebridge;

public enum Direction {
    U(0,-1),
    D(0,1),
    L(-1,0),
    R(1,0),
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
}
