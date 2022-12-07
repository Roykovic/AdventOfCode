package nl.roykovic.aoc.domain.filesystem;

public interface FileSystemItem {
    void setName(String name);
    void setParent(Directory parent);

    void addToParent();

    long getSize();
}
