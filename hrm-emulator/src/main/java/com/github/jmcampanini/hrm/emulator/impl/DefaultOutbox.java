package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Outbox;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The default implementation of {@link Outbox}.
 */
public class DefaultOutbox implements Outbox {

    private final List<Thing> data;

    public DefaultOutbox() {
        this.data = Lists.newArrayList();
    }

    @Override
    public void put(Thing thing) {
        checkArgument(thing != null, "The thing cannot be null.");
        this.data.add(thing);
    }

    @Override
    public List<Thing> data() {
        return ImmutableList.copyOf(this.data);
    }
}
