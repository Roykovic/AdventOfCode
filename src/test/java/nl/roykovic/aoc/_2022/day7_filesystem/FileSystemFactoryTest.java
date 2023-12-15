package nl.roykovic.aoc._2022.day7_filesystem;

import nl.roykovic.aoc.utils.FileReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "FileSystemTestInput.txt,true,95437",
            "FileSystemInput.txt,false,1350966"
    })
    void testSumDirsOver100000(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        Long sum = directories.stream().map(Directory::getSize).filter(it -> it<100001).reduce(0L, Long::sum);
        assertEquals(expected, sum);
    }
    @ParameterizedTest
    @CsvSource({
            "FileSystemTestInput.txt,true,24933642",
            "FileSystemInput.txt,false,6296435"
    })
    void testActualDirectoryToDeleteSize(String filename, boolean test, int expected){
        var input = FileReaderService.getLinesFromFile(2022, filename, test);
        Directory root = new FileSystemFactory().generateFromFile(input);
        List<Directory> directories= root.buildList();

        long neededSize = 30000000;
        long totalSize = 70000000;
        long usedSize = root.getSize();
        long unusedSize = totalSize-usedSize;

        long sizeToFreeUp = neededSize - unusedSize;

        Long min = directories.stream().map(Directory::getSize).filter(it -> it >= sizeToFreeUp).min(Comparator.comparingLong(it -> it)).orElseThrow();
        assertEquals(expected, min);
    }
}
