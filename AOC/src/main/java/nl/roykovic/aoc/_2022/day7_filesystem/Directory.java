package nl.roykovic.aoc._2022.day7_filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemItem{

    private final Directory parent;
    private final String name;
    private final List<Directory> children;
    private final List<File> files;

    public Directory(Directory parent, String name){
        this.children = new ArrayList<>();
        this.files = new ArrayList<>();

        this.parent = parent;
        this.name = name;
    }
    public Directory(String name){
        this(null, name);
    }

    public void add(File file){
        this.files.add(file);
    }

    public void add(Directory dir){
        this.children.add(dir);
    }

    private Directory getChild(String name){
        return this.children.stream().filter(child -> child.getName().equals(name)).findFirst().orElseThrow(IndexOutOfBoundsException::new);
    }

    public Directory cd(String name){
        if("..".equals(name)){
            return this.parent;
        }
        else{
            return this.getChild(name);
        }
    }

    public long getSize(){
        int size = 0;

        for(Directory child: children){
            size += child.getSize();
        }
        for(File file: files){
            size += file.getSize();
        }

        return size;
    }

    public List<Directory> buildList(){

        List<Directory> directories = new ArrayList<>();

            directories.add(this);

        for(Directory child: children){
            directories.addAll(child.buildList());
        }

        return directories;
    }

    public void addToParent(){
        this.parent.add(this);
    }

    public String getName() {
        return name;
    }
}
