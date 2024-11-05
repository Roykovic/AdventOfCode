package nl.roykovic.aoc._2015.day24_balance;

import java.util.*;
import java.util.stream.Stream;
public class BalanceFactory {

 List<Integer> values = new ArrayList<>();
 Long QE = Long.MAX_VALUE;

    public Long generate(Stream<String> input, int compartments) {
        List<Integer> inputArr = input.mapToInt(Integer::valueOf).boxed().toList();

        int total = inputArr.stream().mapToInt(it->it).sum();
        int partTotal = total/compartments;
        Vector<Integer> A
                = new Vector<>(inputArr);

        // Function call
        Combination(A,partTotal );


        return QE;
    }

    void unique_combination(int l, int sum, int K,
                                   Vector<Integer> local,
                                   Vector<Integer> A)
    {
        // If a unique combination is found
        if (sum == K) {

            List<Integer> localArr = List.of(local.toArray(new Integer[0]));
            Long localQE = localArr.stream().map(Long::valueOf).reduce(1L, (a, b) -> a * b);

            if(values.isEmpty() || (localArr.size() == values.size() && localQE < QE) || localArr.size() < values.size()){
                values = localArr;
                QE = localQE;
            }
            return;
        }

        // For all other combinations
        for (int i = l; i < A.size(); i++) {

            // Check if the sum exceeds K
            if (sum + A.get(i) > K)
                continue;

            // Check if it is repeated or not
            if (i > l && Objects.equals(A.get(i), A.get(i - 1)))
                continue;

            // Take the element into the combination
            local.add(A.get(i));

            // Recursive call
            unique_combination(i + 1, sum + A.get(i), K,
                    local, A);

            // Remove element from the combination
            local.remove(local.size() - 1);
        }
    }

    // Function to find all combination
    // of the given elements
    void Combination(Vector<Integer> A, int K)
    {
        // Sort the given elements
        Collections.sort(A);

        // To store combination
        Vector<Integer> local = new Vector<Integer>();

        unique_combination(0, 0, K, local, A);
    }

}
