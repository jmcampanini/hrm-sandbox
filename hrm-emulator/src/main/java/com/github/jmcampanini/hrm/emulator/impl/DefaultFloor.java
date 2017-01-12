package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Floor;
import com.github.jmcampanini.hrm.emulator.Thing;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of {@link Floor}.
 */
public class DefaultFloor implements Floor {

    private final Thing[] things;

    DefaultFloor(int capacity) {
        this.things = new Thing[capacity];
    }

    public static Floor empty(int capacity) {
        return new DefaultFloor(capacity);
    }

    /**
     * Things can contain nulls, as they will not be set but are used to mark position.
     */
    public static Floor prepopulated(int capacity, List<Thing> things) {
        Floor floor = new DefaultFloor(capacity);

        for (int i = 0; i < things.size(); i++) {
            Thing thing = things.get(i);

            if (thing != null) {
                floor.set(i, thing);
            }
        }

        return floor;
    }

    @Override
    public Optional<Thing> get(int tileNumber) {
        Thing thing = this.things[tileNumber];
        return Optional.ofNullable(thing);
    }

    @Override
    public void set(int tileNumber, Thing thing) {
        this.things[tileNumber] = thing;
    }
}
