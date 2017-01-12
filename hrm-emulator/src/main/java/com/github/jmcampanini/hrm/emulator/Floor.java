package com.github.jmcampanini.hrm.emulator;

import java.util.Optional;

/**
 * Represents the floor, with numbered tiles, in HRM.
 */
public interface Floor {

    Optional<Thing> get(int tileNumber);

    void set(int tileNumber, Thing thing);
}
