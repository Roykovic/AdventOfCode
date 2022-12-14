package nl.roykovic.aoc._2022.filesystem;

public class File implements FileSystemItem{
    private final long size;
    private final Directory parent;

    public File(long size, Directory parent) {
        this.size = size;
        this.parent = parent;
    }

    public void addToParent(){
        this.parent.add(this);
    }

    public long getSize() {
        return size;
    }
}
