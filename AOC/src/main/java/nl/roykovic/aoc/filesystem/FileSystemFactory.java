package nl.roykovic.aoc.filesystem;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class FileSystemFactory {
    public Directory generateFromFile(File file) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = reader.lines().toList();

        Directory root = new Directory("root");

        lines = lines.subList(2, lines.size()); //remove cd / and ls, since we already created the root

        Directory curDir = root;
        for(String line: lines){

            if(line.startsWith("$ cd")){
                String[] changeDirCommand = line.split(" ");
                curDir = curDir.cd(changeDirCommand[2]);
            }
            else if(!line.startsWith("$ ls")){
                String[] parts = line.split(" ");
                FileSystemItem item = getFileSystemItem(parts[0], parts[1], curDir);
                item.addToParent();
            }
        }
        return root;
    }

    private FileSystemItem getFileSystemItem(String prefix, String name, Directory parent){
        FileSystemItem item;
        if("dir".equals(prefix)){
            item = new Directory(parent, name);
        }
        else{
            long size = NumberUtils.createLong(prefix);
            item = new nl.roykovic.aoc.filesystem.File(size,name, parent);
        }

        return item;
    }
}
