package nl.roykovic.aoc.trees;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class TreePatch {
    private final Tree[][] patch;

    public TreePatch(Tree[][] patch) {
        this.patch = patch;
        setVisible();
    }

    private void setVisible(){

        for(int rowIndex = 0; rowIndex < patch.length; rowIndex++){
            Tree[] row = getRow(rowIndex);

            checkVisibility(row);
            ArrayUtils.reverse(row);
            checkVisibility(row);
        }

        for(int columnIndex = 0; columnIndex < patch[0].length; columnIndex++){
            Tree[] column = getColumn(columnIndex);

            checkVisibility(column);
            ArrayUtils.reverse(column);
            checkVisibility(column);
        }
    }

    private Tree[] getColumn(int column) {
         return Arrays.stream(patch).map(trees -> trees[column]).toArray(Tree[]::new);
    }

    private Tree[] getRow(int row){
        return patch[row];
    }

    private void checkVisibility(Tree[] sightLine){
        int highestTree = -1;
        for(Tree tree: sightLine){
            if(tree.getHeight() > highestTree){
                tree.setVisible(true);
                highestTree = tree.getHeight();
            }
        }
    }

    public Tree[][] getPatch() {
        return patch;
    }
}
