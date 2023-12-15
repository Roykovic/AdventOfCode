package nl.roykovic.aoc._2022.day14_fallingsand;

import nl.roykovic.aoc.utils.Coord;

import java.util.Map;

public record Cave(Map<Coord, IParticle> particles, Long highestY) {
}
