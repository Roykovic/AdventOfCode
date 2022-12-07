package nl.roykovic.aoc.filesystem;

public class File implements FileSystemItem{
    private final long size;
    private String name;
    private Directory parent;

    public File(long size, String name, Directory parent) {
        this.size = size;
        this.name = name;
        this.parent = parent;
    }

    public void addToParent(){
        this.parent.add(this);
    }

    public long getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
