package nl.roykovic.aoc._2015.day17_eggnog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EggnogContainerFactory {

    public Long calculatePossibleWaysFromFile(File file, int sum) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Integer> containers = reader.lines().mapToInt(Integer::parseInt).boxed().collect(Collectors.toCollection(ArrayList::new));

        return calculatePossibleWaysFromList(containers, sum);
    }

    public Long calculatePossibleWaysFromList(List<Integer> containers, int sum) {

        //first we find the absolute maximum number of containers we can fill,
        // by sorting the list an adding all the smallest numbers until we hit our goal
        Collections.sort(containers);
        Long calculatedSum = 0L;
        int i = 0;
        while (calculatedSum < sum) {
            calculatedSum += containers.get(i);
            i++;
        }

        int largestNumber = i;

        //Next we find the absolute minimum number of containers we fill, by doing the exact opposite
        containers.sort(Collections.reverseOrder());
        i = 0;
        calculatedSum = 0L;
        while (calculatedSum < sum) {
            calculatedSum += containers.get(i);
            i++;
        }

        int smallestNumber = i;

        long waysToMakeSum = 0L;

        //now we just calculate all k combinations where smallestNumber < k < largestNumber,
        // then we check if the sum of any of these combinations is our goal. And add 1 for each that is
        for (int k = smallestNumber; k <= largestNumber; k++) {
            waysToMakeSum += kCombinations(containers.stream().mapToInt(Integer::intValue).toArray(), k).stream().map(it -> Arrays.stream(it).sum()).filter(it -> it == sum).count();
        }

        return waysToMakeSum;
    }

    //Below code is 'borrowed' from https://stackoverflow.com/questions/29910312/algorithm-to-get-all-the-combinations-of-size-n-from-an-array-java
    List<int[]> kCombinations(int[] input, int k) {
        // sequence length

        List<int[]> subsets = new ArrayList<>();

        int[] s = new int[k];                  // here we'll keep indices
        // pointing to elements in input array

        if (k <= input.length) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (s[i] = i) < k - 1; i++) ;
            subsets.add(getSubset(input, s));
            for (; ; ) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--) ;
                if (i < 0) {
                    break;
                }
                s[i]++;                    // increment this item
                for (++i; i < k; i++) {    // fill up remaining items
                    s[i] = s[i - 1] + 1;
                }
                subsets.add(getSubset(input, s));
            }
        }
        return subsets;
    }

    // generate actual subset by index sequence
    int[] getSubset(int[] input, int[] subset) {
        int[] result = new int[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }
}
