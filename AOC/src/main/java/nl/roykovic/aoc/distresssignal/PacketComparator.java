package nl.roykovic.aoc.distresssignal;

import java.util.ArrayList;
import java.util.Comparator;

public class PacketComparator implements Comparator<ArrayList> {
    @Override
    public int compare(ArrayList o1, ArrayList o2) {
        return PacketUtils.compare(o1, o2);
    }
}
