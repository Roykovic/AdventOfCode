package nl.roykovic.aoc.bufferstream;

import nl.roykovic.aoc.bufferstream.BufferStreamDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class BufferStreamDecoderTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("BufferInput.txt").getFile();
        int startOfPacket = new BufferStreamDecoder().findStartOfPacket(input, 4);
        System.out.println(startOfPacket);
    }

    @Test
    void testFactory2() throws IOException {
        File input = new ClassPathResource("BufferInput.txt").getFile();
        int startOfPacket = new BufferStreamDecoder().findStartOfPacket(input, 14);
        System.out.println(startOfPacket);
    }
}
