package nl.roykovic.aoc._2022.day6_bufferstream;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;

public class BufferStreamDecoder {
    public int findStartOfPacket(File file, int packetLength) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String bufferStream = reader.lines().findFirst().orElseThrow(IndexOutOfBoundsException::new);

        return findStartOfPacket(bufferStream, packetLength);
    }
    public int findStartOfPacket(String input, int packetLength){

        char[] bufferStream = input.toCharArray();

        boolean startOfPacketFound = false;
        int pos = packetLength -1;
        while(!startOfPacketFound){
            char[] firstPacket = ArrayUtils.subarray(bufferStream, 0, packetLength);
            startOfPacketFound = firstPacket.length == new HashSet<>(Arrays.asList(ArrayUtils.toObject(firstPacket))).size();
            bufferStream = ArrayUtils.subarray(bufferStream, 1, bufferStream.length);
            pos += 1;
        }

        return pos;
    }
}
