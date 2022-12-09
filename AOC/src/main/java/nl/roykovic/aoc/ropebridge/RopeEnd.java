package nl.roykovic.aoc.ropebridge;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class RopeEnd {

    int x;
    int y;

    List<Map.Entry<Integer, Integer>> path;

    public RopeEnd(int x, int y) {
        this.path = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(Direction direction){
        this.x += direction.getAdditionalX();
        this.y += direction.getAdditionalY();
        path.add(new AbstractMap.SimpleEntry<>(x,y));
    }
}
