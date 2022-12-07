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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemFactoryTest {
    @Test
    void testFactory() throws IOException {
        File input = new ClassPathResource("FileSystemInput.txt").getFile();
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        Long i = directories.stream().map(Directory::getSize).filter(it -> it<100001).reduce(0L, Long::sum);

        System.out.println(i);
    }

    @Test
    void testFactory2() throws IOException {
        File input = new ClassPathResource("FileSystemInput.txt").getFile();
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        long neededSize = 30000000;
        long totalSize = 70000000;
        long usedSize = root.getSize();
        long unusedSize = totalSize-usedSize;

        long sizeToFreeUp = neededSize - unusedSize;

        List<Long> bigEnoughDirs = directories.stream().map(Directory::getSize).filter(it -> it>=sizeToFreeUp).collect(Collectors.toList());
        Collections.sort(bigEnoughDirs);
        System.out.println(bigEnoughDirs.get(0));
    }
}
