package nl.roykovic.AOC.domain.FileSystem;

public interface FileSystemItem {
    void setName(String name);
    void setParent(Directory parent);

    void addToParent();

    long getSize();
}
