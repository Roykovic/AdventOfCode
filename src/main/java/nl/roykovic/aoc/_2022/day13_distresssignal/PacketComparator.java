package nl.roykovic.aoc._2022.day13_distresssignal;

import java.util.ArrayList;
import java.util.Comparator;

public class PacketComparator implements Comparator<ArrayList<Object>> {
    @Override
    public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
        return PacketUtils.compare(o1, o2);
    }
}
