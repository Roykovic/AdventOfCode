package nl.roykovic.aoc._2023.day20_pulse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FlipFlopModule extends Module{
    public FlipFlopModule(String name) {
        super(name);
    }

    boolean flipped = false;

    @Override
    public void receive(Module m, Boolean in) {
        super.receive(m, in);
        if(!in){
            rememberedState = !rememberedState;
         flipped = true;
        }
    }

    @Override
    public List<Map.Entry<Module, Boolean>> send() {
        if(flipped) {
            flipped = false;
            return super.send();
        }
        return Collections.emptyList();
    }
}
