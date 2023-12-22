package nl.roykovic.aoc._2023.day20_pulse;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class PulseFactory {
    public List<Module> generate(Stream<String> input) {
        var moduleOutputMap = input
                .map(it -> it.split(" -> "))
                .map(it -> Map.entry(createModuleByName(it[0]), it[1].split(", ")))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<Module, List<Module>> conjunctionInputs = new HashMap<>();
        List<Module> unnamedModules = new ArrayList<>();

        for(var moduleOutputEntry : moduleOutputMap.entrySet()){
            for(String output : moduleOutputEntry.getValue()){
                Module currentModule = moduleOutputEntry.getKey();
                Module outputModule = moduleOutputMap.keySet().stream()
                        .filter(it -> it.getName().equals(output))
                        .findFirst()
                        .orElseGet(() -> {
                            Module unnamedModule = new Module(output);

                            unnamedModules.add(unnamedModule);

                            return unnamedModule;
                        });

                if(outputModule instanceof ConjunctionModule){
                    conjunctionInputs.computeIfAbsent(outputModule, it -> new ArrayList<>()).add(currentModule);
                }

                currentModule.getOutputs().add(outputModule);
            }
        }

        for(var conjunctionInput : conjunctionInputs.entrySet()){
            Module conjunction = conjunctionInput.getKey();
            List<Module> inputs = conjunctionInput.getValue();

            ((ConjunctionModule)conjunction).setInputs(inputs);
        }

        var outputList = new ArrayList<>(moduleOutputMap.keySet());
        outputList.addAll(unnamedModules);

        return outputList;
    }

    public long pushButton(List<Module> modules){
        Module broadcaster = modules.stream().filter(it -> it.getName().equals("broadcaster")).findFirst().orElseThrow();

        Queue<Map.Entry<Module, Map.Entry<Module, Boolean>>> queue = new LinkedList<>(List.of(Map.entry(new Module("Button"),Map.entry(broadcaster, false))));

        while(!queue.isEmpty()) {

            var toReceive = queue.poll();

            var sender = toReceive.getKey();

            var val = toReceive.getValue();
            var module = val.getKey();
            var pulse= val.getValue();

            module.receive(sender, pulse);

            for(var toSend : module.send()){
                queue.add(Map.entry(module, toSend));
            }
        }
        var bla = modules.stream()
                .map(Module::getReceivedPulses)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return bla.get(false) * bla.get(true);
    }

    public static Module createModuleByName(String name){
        switch (name.charAt(0)){
            case '%' -> {
                return new FlipFlopModule(name.substring(1));
            }
            case '&' -> {
                return new ConjunctionModule(name.substring(1));
            }
            case 'b' -> {
                return new BroadcastModule(name);
            }
            default -> throw new RuntimeException();
        }
    }

}
