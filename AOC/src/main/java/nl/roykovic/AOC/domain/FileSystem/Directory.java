package nl.roykovic.AOC.domain.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemItem{

    private Directory parent;
    private String name;
    private List<Directory> children;
    private List<File> files;

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
        return this.children.stream().filter(child -> child.getName().equals(name)).findFirst().get();
    }

    public Directory cd(String name){
        if("..".equals(name)){
            return this.parent;
        }
        else{
            return this.getChild(name);
        }
    }

    public void addToParent(){
        this.parent.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
