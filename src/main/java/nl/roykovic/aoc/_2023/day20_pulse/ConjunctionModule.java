package nl.roykovic.aoc._2023.day20_pulse;

import java.util.List;

public class ConjunctionModule extends Module{

    List<Boolean> rememberedStates;
    @Override
    public void receive(Module sender, Boolean in) {
        int index = this.getInputs().indexOf(sender);
        rememberedStates.add(index, in);

        send();
    }

    @Override
    public void send() {
        boolean out = !rememberedStates.stream().allMatch(it -> it);
        for(Module m : getOutputs()){
            this.getSendOutputs().add(out);
            m.receive(this, out);
        }
    }
}
