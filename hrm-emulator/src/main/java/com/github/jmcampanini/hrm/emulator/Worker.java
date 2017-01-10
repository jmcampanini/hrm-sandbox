package com.github.jmcampanini.hrm.emulator;

import java.util.Optional;

/**
 * Represents the worker inside of the HRM.
 */
public interface Worker {

    Optional<Thing> thing();

    void setThing(Thing thing);

    void clear();
}
