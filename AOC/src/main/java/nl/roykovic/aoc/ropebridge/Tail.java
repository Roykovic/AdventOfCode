package nl.roykovic.aoc.ropebridge;

import java.util.AbstractMap;

public class Tail  extends RopeEnd{
    public Tail(int x, int y) {
        super(x, y);
    }

    public void moveToRopeEnd(RopeEnd end){
        int size = path.size();

        int dX = x - end.x;
        int dY = y - end.y;

        if(Math.abs(dX) > 1 || Math.abs(dY) > 1) {  // only move if you are more than 1 away from either x or y

            int newX;
            int newY;

            if(Math.abs(dX) > 0 && Math.abs(dY) > 0){   //if you are away in both directions, move diagonally
                newX = -(dX/Math.abs(dX));              //dividing by absolute normalizes the value, the - inverts it
                newY = -(dY/Math.abs(dY));
            }
            else {
                newX = (int) (dX * -0.5);   //dX or dY is (-)2 now. So normalize and invert to get the direction
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
        super.move(direction);
    }
}
