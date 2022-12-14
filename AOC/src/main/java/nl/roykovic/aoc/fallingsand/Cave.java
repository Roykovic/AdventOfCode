package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.util.Map;

public class Cave {
    Map<Coord, IParticle> particles;
    int highestY;

    public Cave(Map<Coord, IParticle> particles, int highestY) {
        this.particles = particles;
        this.highestY = highestY;
    }
}
