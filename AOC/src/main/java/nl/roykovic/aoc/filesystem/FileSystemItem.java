package nl.roykovic.aoc.filesystem;

public interface FileSystemItem {
    void addToParent();

    long getSize();
}
