package nl.roykovic.aoc.fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.util.Map;

public record Cave(Map<Coord, IParticle> particles, int highestY) {
}
