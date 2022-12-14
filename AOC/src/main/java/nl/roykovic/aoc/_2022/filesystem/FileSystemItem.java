package nl.roykovic.aoc._2022.filesystem;

public interface FileSystemItem {
    void addToParent();

    long getSize();
}
