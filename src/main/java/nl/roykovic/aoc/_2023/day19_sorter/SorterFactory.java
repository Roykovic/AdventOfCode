package nl.roykovic.aoc._2023.day19_sorter;

import nl.roykovic.aoc._2023.day5_garden.Range;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class SorterFactory {

    private Map<String, WorkFlow> workFlowMap = new HashMap<>();
    private List<Part> parts = new ArrayList<>();

    public void generate(List<String> input) {

        var blankLine = input.indexOf("");

        var workFlows = input.subList(0, blankLine);
        var partsList = input.subList(blankLine+1,input.size());

        workFlowMap = new HashMap<>();

        for(String workflow : workFlows){
            String workflowName = workflow.substring(0, workflow.indexOf('{'));
            var constraints = Arrays.stream(
                    workflow
                            .substring(workflow.indexOf('{')+1, workflow.length()-1)
                            .split(","))
                    .map(it -> {
                        var arr = it.split(":");

                        Character property = null;
                        Character operator = null;
                        Integer number = null;
                        String followup = arr[0];

                        if(arr.length > 1) {
                            var operation = arr[0];
                            property = operation.charAt(0);
                            operator = operation.charAt(1);
                            number = Integer.parseInt(operation.substring(2));

                            followup = arr[1];
                        }

                        return new Constraint(property, operator, number,followup);
                    }).collect(Collectors.partitioningBy(it -> it.getProperty() != null));


            WorkFlow workFlow = new WorkFlow(constraints.get(true), constraints.get(false).get(0).getFollowup());

            workFlowMap.put(workflowName, workFlow);
        }

        parts =partsList.stream()
                .map(it -> it.substring(1, it.length()-1))
                .map(it -> it.split(","))
                .map(it ->
                        Arrays.stream(it)
                                .map(value -> value.split("="))
                                .map(valueArr -> Map.entry(valueArr[0].charAt(0), Integer.parseInt(valueArr[1]))).collect(Collectors.toList())
                )
                .map(it -> it.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .map(Part::new)
                .toList();
    }

    public int run(){
        Map<String, List<Part>> outputs = Map.of("A", new ArrayList<>(), "R", new ArrayList<>());

        for(Part part : parts){
            WorkFlow currentWorkflow = workFlowMap.get("in");
            outputs.get(currentWorkflow.consider(workFlowMap, part).get()).add(part);
        }

        return outputs.get("A").stream().mapToInt(p -> p.values.values().stream().mapToInt(prop -> prop).sum()).sum();
    }

    public BigInteger getAllPossibilities(){
        RangePart part = new RangePart(Map.of('x', new Range(1,4000),'m', new Range(1,4000),'a', new Range(1,4000), 's', new Range(1, 4000)));

        Map<String, List<RangePart>> outputs = Map.of("A", new ArrayList<>(), "R", new ArrayList<>());

        WorkFlow currentWorkflow = workFlowMap.get("in");

        var bla = currentWorkflow.consider(workFlowMap, part).get("A");

        return bla.stream().map(RangePart::getPossibilities).reduce(BigInteger::add).orElseThrow();
    }
}
