package nl.roykovic.aoc._2023.day20_pulse;

public class BroadcastModule extends Module{

    @Override
    public void receive(Module sender, Boolean in) {
        rememberedState = in;
        send();
    }

    @Override
    public void send() {
        for(Module m : getOutputs()){
            this.getSendOutputs().add(rememberedState);
            m.receive(this, rememberedState);
        }
    }
}
