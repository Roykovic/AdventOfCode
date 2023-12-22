package nl.roykovic.aoc._2023.day20_pulse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConjunctionModule extends Module{

    List<Module> inputs;
    Map<Module, Boolean> rememberedStates;

    public ConjunctionModule(String name) {
        super(name);
    }

    public void setInputs(List<Module> inputs) {
        this.inputs = inputs;
        rememberedStates = inputs.stream().map(it -> Map.entry(it, false)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void receive(Module sender, Boolean in) {
        super.receive(sender, in);
        rememberedStates.put(sender, in);
        rememberedState = !rememberedStates.values().stream().allMatch(it -> it);
    }
}
