package nl.roykovic.aoc._2023.day9_oasis;

import java.util.ArrayList;
import java.util.List;

public class OasisList {
    private final List<Long> list;

    public OasisList(List<Long> list) {
        this.list = list;
    }

    public Long getNextValue(){
        List<List<Long>> differenceLists = getDifferenceLists(list);
        for(int i = differenceLists.size() -2; i>=0; i--){
            List<Long> l1 = differenceLists.get(i+1);
            List<Long> l2 = differenceLists.get(i);


            l2.add(l1.get(l1.size()-1) + l2.get(l2.size()-1));
        }

        return differenceLists.get(0).get(differenceLists.get(0).size() -1);
    }

    public Long getPreviousValue(){
        List<List<Long>> differenceLists = getDifferenceLists(list);
        for(int i = differenceLists.size() -2; i>=0; i--){
            List<Long> l1 = differenceLists.get(i+1);
            List<Long> l2 = differenceLists.get(i);


            l2.add(0, l2.get(0) - l1.get(0));
        }

        return differenceLists.get(0).get(0);
    }

    private List<List<Long>> getDifferenceLists(List<Long> input){
        List<List<Long>> differenceLists = new ArrayList<>();

        List<Long> currentList = new ArrayList<>(input);
        while(currentList.stream().anyMatch(it -> it != 0)){
            differenceLists.add(currentList);
            currentList = getDifferenceList(currentList);
        }
        return differenceLists;
    }

    private List<Long> getDifferenceList(List<Long> originalList){
        List<Long> differenceList = new ArrayList<>();
        for(int i = 1; i< originalList.size(); i++){
            differenceList.add(originalList.get(i) - originalList.get(i-1));
        }
        return differenceList;
    }
}
