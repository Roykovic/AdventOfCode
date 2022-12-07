package nl.roykovic.AOC.domain.FileSystem;

public interface FileSystemItem {
    public void setName(String name);
    public void setParent(Directory parent);

    public void addToParent();

    public long getSize();
}
