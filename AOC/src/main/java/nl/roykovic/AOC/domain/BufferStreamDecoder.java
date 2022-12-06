package nl.roykovic.AOC.domain;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BufferStreamDecoder {
    public int findStartOfPacket(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        char[] bufferStream = reader.lines().findFirst().get().toCharArray();

        boolean startOfPacketFound = false;
        int pos = 3;
        while(!startOfPacketFound){
            char[] firstFourChars = ArrayUtils.subarray(bufferStream, 0, 4);
            startOfPacketFound = firstFourChars.length == new HashSet<Character>(Arrays.asList(ArrayUtils.toObject(firstFourChars))).size();
            bufferStream = ArrayUtils.subarray(bufferStream, 1, bufferStream.length-1);
            pos += 1;
        }

        return pos;
    }
}
