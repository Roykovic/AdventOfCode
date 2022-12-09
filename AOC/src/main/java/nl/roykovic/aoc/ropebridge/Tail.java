package nl.roykovic.aoc.ropebridge;

import java.util.AbstractMap;

public class Tail  extends RopeEnd{
    public Tail(int x, int y) {
        super(x, y);
    }

    public void moveToHead(int headX, int headY){
        int size = path.size();

        int dX = x - headX;
        int dY = y - headY;

        if(Math.abs(dX) > 1 || Math.abs(dY) > 1) {

            int newX;
            int newY;
            if(Math.abs(dX) > 0 && Math.abs(dY) > 0){
                newX = -(dX/Math.abs(dX));
                newY = -(dY/Math.abs(dY));
            }
            else {
                newX = (int) (dX * -0.5);
                newY = (int) (dY * -0.5);
            }
            move(Direction.getByCoords(newX,newY));
        }
        if (path.size() == size) {
            path.add(new AbstractMap.SimpleEntry<>(x, y));
        }
    }

    @Override
    public void move(Direction direction){
//        System.out.println("Moving " + direction);
        super.move(direction);
    }
}
