package nl.roykovic.aoc._2023.day19_sorter;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
}
