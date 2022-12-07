package nl.roykovic.AOC;

import nl.roykovic.AOC.domain.FileSystem.Directory;
import nl.roykovic.AOC.domain.FileSystem.FileSystemFactory;
import nl.roykovic.AOC.domain.Rucksack;
import nl.roykovic.AOC.domain.RucksackFactory;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileSystemFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("FileSystemInput.txt").getFile();
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        Long i = directories.stream().map(Directory::getSize).filter(it -> it<100001).reduce(0L, Long::sum);

        System.out.println(i);
    }
}
