package nl.roykovic.aoc._2022.encryptednumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncryptedNumbersList {
    private List<EncryptedNumber> numberList = new ArrayList<>();

    public void addToList(EncryptedNumber  number){
        numberList.add(number);
    }

    public void decrypt(){
        List<EncryptedNumber> originalList = new ArrayList<>(numberList);
        for(EncryptedNumber number : originalList){
            if(!number.isSwapped()){
                move(number);
            }
        }
    }

    public void move(EncryptedNumber number){
        number.setSwapped(true);

        if(number.getValue() > 0) {
            for (int i = 0; i < number.getValue(); i++) {
                int oldIndex = numberList.indexOf(number);

                if (oldIndex < numberList.size() - 2) {
                    Collections.swap(numberList, oldIndex, oldIndex + 1);
                } else {
                    numberList.remove(number);
                    numberList.add(0, number);
                }
            }
        }
        else{
            for (int i = 0; i > number.getValue(); i--) {
                int oldIndex = numberList.indexOf(number);

                if (oldIndex > 1) {
                    Collections.swap(numberList, oldIndex, oldIndex - 1);
                } else {
                    numberList.remove(number);
                    numberList.add(number);
                }
            }
        }
    }

    public EncryptedNumber findNthNumberAfterZero(int n){

        EncryptedNumber zero = numberList.stream().filter(it -> it.getValue() ==0 ).findFirst().orElseThrow(RuntimeException::new);

        int zeroIndex = numberList.indexOf(zero);

        int numbersToEndOfList = numberList.size() -1 - zeroIndex;


        if(n < numbersToEndOfList){
            return numberList.get(zeroIndex + n);
        }

        int nFromStart = n -numbersToEndOfList -1;

        int normalizedIndex = nFromStart % (numberList.size());

        return numberList.get(normalizedIndex);
    }
}
