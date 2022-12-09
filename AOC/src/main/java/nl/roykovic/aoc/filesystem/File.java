package nl.roykovic.aoc.filesystem;

public class File implements FileSystemItem{
    private final long size;
    private Directory parent;

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

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
