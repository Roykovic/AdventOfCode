package nl.roykovic.aoc._2022.day6_bufferstream;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferStreamDecoderTest {

    @ParameterizedTest
    @CsvSource(
            {"mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
            "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
            "nppdvjthqldpwncqszvftbrmjlhg,6",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"})
    void testStartOfPacketExampleStreams(String bufferStream, int startOfPacket){
        int output = new BufferStreamDecoder().findStartOfPacket(bufferStream, 4);
        assertEquals(startOfPacket, output);
    }

    @Test
    void testStartOfPacketActualStream() {
        var input = FileReaderService.getFileAsString(2022, "BufferInput.txt", false);
        int startOfPacket = new BufferStreamDecoder().findStartOfPacket(input, 4);
        assertEquals(1300, startOfPacket);
    }

    @ParameterizedTest
    @CsvSource(
                    {"mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
                    "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
                    "nppdvjthqldpwncqszvftbrmjlhg,23",
                    "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
                    "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"})
    void testStartOfMessageExampleStreams(String bufferStream, int startOfPacket){
        int output = new BufferStreamDecoder().findStartOfPacket(bufferStream, 14);
        assertEquals(startOfPacket, output);
    }

    @Test
    void testStartOfMessageActualStream() {
        var input = FileReaderService.getFileAsString(2022, "BufferInput.txt", false);

        int startOfPacket = new BufferStreamDecoder().findStartOfPacket(input, 14);
        assertEquals(3986, startOfPacket);
    }

}
