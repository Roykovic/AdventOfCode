package nl.roykovic.aoc._2023.day20_pulse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Module implements Serializable {
    private final List<Module> outputs;

    private String name;

    private final List<Boolean> receivedPulses;

    boolean rememberedState = false;

    public Module(String name) {
        this.outputs = new ArrayList<>();
        this.receivedPulses = new ArrayList<>();
        this.name = name;
    }

    public void receive(Module sender, Boolean in){
        receivedPulses.add(in);
    }
    public List<Map.Entry<Module, Boolean>> send(){
        List<Map.Entry<Module, Boolean>> toSend = new ArrayList<>();

        for(Module m : getOutputs()){
            toSend.add(Map.entry(m, rememberedState));
        }
        return toSend;
    }

    public List<Module> getOutputs() {
        return outputs;
    }

    public List<Boolean> getReceivedPulses() {
        return receivedPulses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRememberedState() {
        return rememberedState;
    }

    @Override
    public String toString() {
        return name;
    }
}
