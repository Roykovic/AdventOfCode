package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.BufferStreamDecoder;
import nl.roykovic.AOC.domain.SupplyStacks.Crane;
import nl.roykovic.AOC.domain.SupplyStacks.CraneFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class BufferStreamDecoderTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("BufferInput.txt").getFile();
        int startOfPacket = new BufferStreamDecoder().findStartOfPacket(input);
        System.out.println(startOfPacket);
    }
}
