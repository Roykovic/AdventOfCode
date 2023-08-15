package nl.roykovic.aoc._2022.encryptednumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncryptedNumbersList {
    private List<EncryptedNumber> numberList = new ArrayList<>();

    public void addToList(EncryptedNumber  number){
        numberList.add(number);
    }

    public void decrypt(int times){
        List<EncryptedNumber> originalList = new ArrayList<>(numberList);
        for( int i = 0; i < times; i++) {
            for (EncryptedNumber number : originalList) {
                    move(number);
            }
        }
    }

    public void move(EncryptedNumber number){
        number.setSwapped(true);

        int oldIndex = numberList.indexOf(number);

        numberList.remove(oldIndex);
        numberList.add(findIndexFromNumberOfMoves(number.getValue(), oldIndex), number);

//        for(EncryptedNumber n : numberList){
//            System.out.print(n.getValue() + ", ");
//        }
//        System.out.println("\n");

    }

    public EncryptedNumber findNthNumberAfterZero(long n){

        EncryptedNumber zero = numberList.stream().filter(it -> it.getValue() ==0 ).findFirst().orElseThrow(RuntimeException::new);

        return numberList.get(findIndexFromNumberOfMoves(n, numberList.indexOf(zero)));
    }

    private int findIndexFromNumberOfMoves(long numberOfMoves, int currentIndex){

        if(numberOfMoves == 0 ){
            return currentIndex;
        }

        if(numberOfMoves > 0) {

            int numbersToEndOfList = numberList.size()  - currentIndex;

            if (numberOfMoves < numbersToEndOfList) {
                return (int) (currentIndex + numberOfMoves);
            }

            long indexFromStart = numberOfMoves - numbersToEndOfList;

            return (int) (indexFromStart % numberList.size());
        }

        int numbersToStartOfList = currentIndex * -1;
        if(numberOfMoves > numbersToStartOfList){
            return (int) (currentIndex + numberOfMoves);
        }

        long indexFromEnd = numberOfMoves - numbersToStartOfList;

        long normalizedIndex = indexFromEnd % numberList.size();

        return (int) (numberList.size() + normalizedIndex);
    }
}
