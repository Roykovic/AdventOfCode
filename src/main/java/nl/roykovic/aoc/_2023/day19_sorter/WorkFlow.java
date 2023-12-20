package nl.roykovic.aoc._2023.day19_sorter;

import org.apache.commons.lang3.SerializationUtils;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class WorkFlow {
    List<Constraint> constraints;
    private final String fallback;

    public WorkFlow(List<Constraint> constraints, String fallback) {
        this.constraints = constraints;
        this.fallback = fallback;
    }

    public Optional<String> consider(Map<String, WorkFlow> workFlowMap, Part part){

        Optional<String> next = Optional.empty();

        int index =0;

        while(next.isEmpty() && index < constraints.size()){
            Constraint currentConstraint = constraints.get(index);
            next = currentConstraint.next(part.values.get(currentConstraint.getProperty()));

            if(next.isPresent()){
                WorkFlow nextFlow = workFlowMap.get(next.get());
                if(nextFlow != null) {
                    next = nextFlow.consider(workFlowMap, part);
                }
            }
            index++;
        }

        return next.or(() -> workFlowMap.get(fallback) != null? workFlowMap.get(fallback).consider(workFlowMap, part) : Optional.of(fallback));
    }

    public Map<String, List<RangePart>> consider(Map<String, WorkFlow> workFlowMap, RangePart part){

        Map<String, List<RangePart>> output = new HashMap<>();

        RangePart unmatchedPart = new RangePart(SerializationUtils.clone(new HashMap<>(part.values)));
        for(Constraint constraint : constraints) {
            RangePart matchedPart = new RangePart(SerializationUtils.clone(new HashMap<>(unmatchedPart.values)));
            if (constraint.getOperator() == '>') {
                matchedPart.values.get(constraint.getProperty()).setStart(constraint.getNumber() + 1);
                unmatchedPart.values.get(constraint.getProperty()).setEnd(constraint.getNumber());
            } else {
                matchedPart.values.get(constraint.getProperty()).setEnd(constraint.getNumber() - 1);
                unmatchedPart.values.get(constraint.getProperty()).setStart(constraint.getNumber());
            }
            output.computeIfAbsent(constraint.getFollowup(), it -> new ArrayList<>()).add(matchedPart);
        }
        output.computeIfAbsent(fallback,it -> new ArrayList<>()).add(unmatchedPart);

        while (output.entrySet().stream().anyMatch(it -> !it.getValue().isEmpty() && (!Objects.equals(it.getKey(), "A") && !Objects.equals(it.getKey(), "R")))){
            List<Map.Entry<String, RangePart>> toRemove = new ArrayList<>();
            for(var set : output.entrySet().stream().filter(it -> !it.getKey().equals("A") && !it.getKey().equals("R") && !it.getValue().isEmpty()).toList()){
                List<RangePart> rangeParts = set.getValue();
                for(RangePart rp : rangeParts) {
                    var newMap = workFlowMap.get(set.getKey()).consider(workFlowMap, rp);
                    for(var entry : newMap.entrySet()){
                        output.computeIfAbsent(entry.getKey(),it -> new ArrayList<>()).addAll(entry.getValue());
                    }
                    toRemove.add(Map.entry(set.getKey(), rp));
                }
            }
            for(var entry : toRemove){
                output.get(entry.getKey()).remove(entry.getValue());
            }
        }

        return output.entrySet().stream().filter(it -> !it.getValue().isEmpty()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
