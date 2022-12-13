package nl.roykovic.aoc.distresssignal;

import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PacketUtils {

    public static Boolean compare (ArrayList left, ArrayList right ){
        int smallestSize = Math.min(left.size(), right.size());

        int i = 0;
        Boolean rightIsBigger = null;
        while(i< smallestSize && rightIsBigger == null){    //while there are still values to compare (in the smallest list) and no mismatch were found, keep looking
            Object leftValue = left.get(i);
            Object rightValue = right.get(i);

            if(leftValue.getClass() == ArrayList.class || rightValue.getClass() == ArrayList.class){
                rightIsBigger = compare(toList(leftValue), toList(rightValue)); //if either (or both) of the values are lists, compare the lists
            }
            else{
                rightIsBigger = toBool(NumberUtils.compare((Integer)rightValue, (Integer)leftValue));   //if neither are lists, both must be ints, these can be compared
            }
            i++;
        }

        if(rightIsBigger != null){
            return rightIsBigger;   //if a mismatch is found, return the result of that
        }
        else if(left.size() != right.size()){
            return smallestSize == left.size(); //if all pairs matched, and one of the lists is bigger, return true if the left one was smaller
        }
        else{
            return null;    //if neither mismatch nor length size were decisive, return null
        }
    }

    private static ArrayList toList(Object object){
        if(object.getClass() != ArrayList.class){
            return new ArrayList(List.of(object));  //if the object is not an Arraylist, make a list containing that item
        }
        return (ArrayList) object;  //if the object was already a list, just return it.
    }

    private static Boolean toBool(int boolInt){
        if(boolInt == 0){
            return null;    //if the comparation was 0, both were equal, and neither was bigger
        }
        return boolInt == 1;    //if one of them was bigger, return true or false (values are 1 if right was bigger and -1 if left was bigger)
    }
}
