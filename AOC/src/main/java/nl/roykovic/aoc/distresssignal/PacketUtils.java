package nl.roykovic.aoc.distresssignal;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class PacketUtils{

    public static int compare (ArrayList<Object> left, ArrayList<Object> right ){
        int smallestSize = Math.min(left.size(), right.size());

        int i = 0;
        int rightIsBigger = 0;
        while(i< smallestSize && rightIsBigger == 0){    //while there are still values to compare (in the smallest list) and no mismatch were found, keep looking
            Object leftValue = left.get(i);
            Object rightValue = right.get(i);

            if(leftValue.getClass() == ArrayList.class || rightValue.getClass() == ArrayList.class){
                rightIsBigger = compare(toList(leftValue), toList(rightValue)); //if either (or both) of the values are lists, compare the lists
            }
            else{
                rightIsBigger = NumberUtils.compare((Integer)rightValue, (Integer)leftValue);   //if neither are lists, both must be ints, these can be compared
            }
            i++;
        }

        if(rightIsBigger != 0){
            return rightIsBigger;   //if a mismatch is found, return the result of that
        }
        else if(left.size() != right.size()){
            return left.size() == smallestSize? 1:-1; //if all pairs matched, and one of the lists is bigger, return true if the left one was smaller
        }
        else{
            return 0;    //if neither mismatch nor length size were decisive, return null
        }
    }

    private static ArrayList<Object> toList(Object object){
        if(object.getClass() != ArrayList.class){
            return new ArrayList<>(List.of(object));  //if the object is not an Arraylist, make a list containing that item
        }
        //noinspection unchecked
        return (ArrayList<Object>) object;  //if the object was already a list, just return it.
    }
}
