package nl.roykovic.aoc._2022.filesystem;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemFactoryTest {
    @Test
    void testSumExampleDirsOver100000() throws IOException {
        File input = new File("src/test/resources/2022/FileSystemTestInput.txt");
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        Long sum = directories.stream().map(Directory::getSize).filter(it -> it<100001).reduce(0L, Long::sum);

        assertEquals(95437, sum);
    }

    @Test
    void testSumActualDirsOver100000() throws IOException {
        File input = new ClassPathResource("2022/FileSystemInput.txt").getFile();
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        Long sum = directories.stream().map(Directory::getSize).filter(it -> it<100001).reduce(0L, Long::sum);

        assertEquals(1350966, sum);
    }

    @Test
    void testExampleDirectoryToDeleteSize() throws IOException {
        File input = new File("src/test/resources/2022/FileSystemTestInput.txt");
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        long neededSize = 30000000;
        long totalSize = 70000000;
        long usedSize = root.getSize();
        long unusedSize = totalSize-usedSize;

        long sizeToFreeUp = neededSize - unusedSize;

        List<Long> bigEnoughDirs = directories.stream().map(Directory::getSize).filter(it -> it >= sizeToFreeUp).sorted().toList();
        assertEquals(24933642, bigEnoughDirs.get(0));
    }

    @Test
    void testActualDirectoryToDeleteSize() throws IOException {
        File input = new ClassPathResource("2022/FileSystemInput.txt").getFile();
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        long neededSize = 30000000;
        long totalSize = 70000000;
        long usedSize = root.getSize();
        long unusedSize = totalSize-usedSize;

        long sizeToFreeUp = neededSize - unusedSize;

        List<Long> bigEnoughDirs = directories.stream().map(Directory::getSize).filter(it -> it >= sizeToFreeUp).sorted().toList();
        assertEquals(6296435, bigEnoughDirs.get(0));
    }
}
