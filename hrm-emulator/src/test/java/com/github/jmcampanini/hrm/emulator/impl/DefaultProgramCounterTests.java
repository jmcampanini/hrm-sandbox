package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.ProgramCounter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DefaultProgramCounter}.
 */
public class DefaultProgramCounterTests {

    @Test
    public void zeroed_starts_at_zero() {
        assertThat(DefaultProgramCounter.zeroed().get(), equalTo(0));
    }

    @Test
    public void startingAt_starts_at_given_index() {
        assertThat(DefaultProgramCounter.startingAt(2).get(), equalTo(2));
        assertThat(DefaultProgramCounter.startingAt(22).get(), equalTo(22));
    }

    @Test
    public void increment_increases_by_one() {
        ProgramCounter counter = DefaultProgramCounter.zeroed();
        counter.increment();
        assertThat(counter.get(), equalTo(1));
    }

    @Test
    public void setToIndex_sets_the_index() {
        ProgramCounter counter = DefaultProgramCounter.zeroed();
        counter.setToIndex(99);
        assertThat(counter.get(), equalTo(99));
    }
}
