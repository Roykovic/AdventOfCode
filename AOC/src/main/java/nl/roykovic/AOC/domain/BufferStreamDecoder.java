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
    public int findStartOfPacket(File file, int packetLength) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        char[] bufferStream = reader.lines().findFirst().get().toCharArray();

        boolean startOfPacketFound = false;
        int pos = packetLength -1;
        while(!startOfPacketFound){
            char[] firstPacket = ArrayUtils.subarray(bufferStream, 0, packetLength);
            startOfPacketFound = firstPacket.length == new HashSet<Character>(Arrays.asList(ArrayUtils.toObject(firstPacket))).size();
            bufferStream = ArrayUtils.subarray(bufferStream, 1, bufferStream.length);
            pos += 1;
        }

        return pos;
    }
}
