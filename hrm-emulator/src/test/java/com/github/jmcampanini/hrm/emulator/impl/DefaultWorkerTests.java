package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Thing;
import com.github.jmcampanini.hrm.emulator.Worker;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DefaultWorker}.
 */
public class DefaultWorkerTests {

    private final Worker worker = new DefaultWorker();

    @Test
    public void starts_empty() {
        assertThat(this.worker.thing().isPresent(), equalTo(false));
    }

    @Test
    public void can_set_value() {
        String val = "a";
        this.worker.setThing(Thing.of(val));

        assertThat(this.worker.thing().isPresent(), equalTo(true));
        assertThat(this.worker.thing().get().value(), equalTo(val));
    }

    @Test
    public void clear_sets_to_empty() {
        this.worker.setThing(Thing.of("a"));
        assertThat(this.worker.thing().isPresent(), equalTo(true));

        this.worker.clear();
        assertThat(this.worker.thing().isPresent(), equalTo(false));
    }
}