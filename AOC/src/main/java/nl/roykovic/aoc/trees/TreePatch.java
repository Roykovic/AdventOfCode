package nl.roykovic.aoc.trees;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public record TreePatch(Tree[][] patch) {
    public TreePatch(Tree[][] patch) {
        this.patch = patch;
        setVisible();
        setScenicScores();
    }

    private void setVisible() {

        for (int rowIndex = 0; rowIndex < patch.length; rowIndex++) {
            Tree[] row = getRow(rowIndex);

            checkVisibility(row);
            ArrayUtils.reverse(row);
            checkVisibility(row);
            ArrayUtils.reverse(row);
        }

        for (int columnIndex = 0; columnIndex < patch[0].length; columnIndex++) {
            Tree[] column = getColumn(columnIndex);

            checkVisibility(column);
            ArrayUtils.reverse(column);
            checkVisibility(column);
            ArrayUtils.reverse(column);
        }
    }

    private void setScenicScores() {
        for (Tree[] trees : patch) {
            for (Tree tree : trees) {
                setScenicScore(tree);
            }
        }
    }

    private Tree[] getColumn(int column) {
        return Arrays.stream(patch).map(trees -> trees[column]).toArray(Tree[]::new);
    }

    private Tree[] getRow(int row) {
        return patch[row];
    }

    private void checkVisibility(Tree[] sightLine) {
        int highestTree = -1;
        for (Tree tree : sightLine) {
            if (tree.getHeight() > highestTree) {
                tree.setVisible(true);
                highestTree = tree.getHeight();
            }
        }
    }

    private void setScenicScore(Tree tree) {
        int scenicScore = 1;

        Tree[] treeRow = getRow(tree.getY());
        Tree[] treeCol = getColumn(tree.getX());
        scenicScore *= getViewDistance(treeRow, tree.getX(), tree.getHeight());
        scenicScore *= getViewDistance(treeCol, tree.getY(), tree.getHeight());

        ArrayUtils.reverse(treeRow);
        ArrayUtils.reverse(treeCol);
        scenicScore *= getViewDistance(treeRow, (treeRow.length - tree.getX()) - 1, tree.getHeight());
        scenicScore *= getViewDistance(treeCol, (treeRow.length - tree.getY()) - 1, tree.getHeight());

        ArrayUtils.reverse(treeRow);
        ArrayUtils.reverse(treeCol);

        tree.setScenicScore(scenicScore);
    }

    private int getViewDistance(Tree[] treeline, int pos, int height) {
        int viewDistance = 0;
        int curTreeHeight = -1;
        while (pos + 1 < treeline.length && curTreeHeight < height) {
            Tree curTree = treeline[pos + 1];
            curTreeHeight = curTree.getHeight();
            viewDistance++;
            pos++;
        }
        return viewDistance;
    }
}
