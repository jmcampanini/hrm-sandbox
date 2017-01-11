package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Outbox;
import com.github.jmcampanini.hrm.emulator.Thing;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link ArrayListOutbox}.
 */
public class ArrayListOutboxTests {

    private final Outbox outbox = new ArrayListOutbox();

    @Test(expected = IllegalArgumentException.class)
    public void cannot_put_null_value() {
        this.outbox.put(null);
    }

    @Test
    public void outputted_data_is_stored_in_order() {
        this.outbox.put(Thing.of("a"));
        this.outbox.put(Thing.of("b"));
        this.outbox.put(Thing.of("c"));

        List<Thing> data = this.outbox.data();
        assertThat(data.size(), equalTo(3));
        assertThat(data.get(0).value(), equalTo("a"));
        assertThat(data.get(1).value(), equalTo("b"));
        assertThat(data.get(2).value(), equalTo("c"));
    }
}
