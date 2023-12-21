package nl.roykovic.aoc._2023.day20_pulse;

import java.util.List;

public class Module {
    private List<Module> inputs;
    private List<Module> outputs;

    private List<Boolean> sendOutputs;

    boolean rememberedState = false;

    public void receive(Module sender, Boolean in){
        return;
    }
    public void send(){
        return;
    }

    public List<Module> getInputs() {
        return inputs;
    }

    public void setInputs(List<Module> inputs) {
        this.inputs = inputs;
    }

    public List<Module> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Module> outputs) {
        this.outputs = outputs;
    }

    public List<Boolean> getSendOutputs() {
        return sendOutputs;
    }

    public void setSendOutputs(List<Boolean> sendOutputs) {
        this.sendOutputs = sendOutputs;
    }

    public boolean isRememberedState() {
        return rememberedState;
    }

    public void setRememberedState(boolean rememberedState) {
        this.rememberedState = rememberedState;
    }
}
