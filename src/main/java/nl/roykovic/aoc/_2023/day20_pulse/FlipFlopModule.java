package nl.roykovic.aoc._2023.day20_pulse;

public class FlipFlopModule extends Module{
    @Override
    public void receive(Module m, Boolean in) {
        if(!in){
            rememberedState = !rememberedState;
            send();
        }
    }

    @Override
    public void send() {
        for(Module m : this.getOutputs()){
            this.getSendOutputs().add(rememberedState);
            m.receive(this, rememberedState);
        }
    }
}
