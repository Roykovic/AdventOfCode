package nl.roykovic.aoc._2023.day20_pulse;

public class BroadcastModule extends Module{

    public BroadcastModule(String name) {
        super(name);
    }

    @Override
    public void receive(Module sender, Boolean in) {
        super.receive(sender, in);
        rememberedState = in;
    }
}
