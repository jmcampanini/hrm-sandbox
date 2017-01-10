package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Thing;
import com.github.jmcampanini.hrm.emulator.Worker;

import java.util.Optional;

/**
 * Default implementation of {@link Worker}.
 */
public class DefaultWorker implements Worker {

    private Optional<Thing> thing = Optional.empty();

    @Override
    public Optional<Thing> thing() {
        return this.thing;
    }

    public void setThing(Thing thing) {
        this.thing = Optional.ofNullable(thing);
    }

    @Override
    public void clear() {
        this.thing = Optional.empty();
    }
}
