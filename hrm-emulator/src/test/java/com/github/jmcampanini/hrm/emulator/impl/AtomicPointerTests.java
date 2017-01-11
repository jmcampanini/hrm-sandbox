package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Pointer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link AtomicPointer}.
 */
public class AtomicPointerTests {

    @Test
    public void zeroed_starts_at_zero() {
        assertThat(AtomicPointer.zeroed().get(), equalTo(0));
    }

    @Test
    public void startingAt_starts_at_given_index() {
        assertThat(AtomicPointer.startingAt(2).get(), equalTo(2));
        assertThat(AtomicPointer.startingAt(22).get(), equalTo(22));
    }

    @Test
    public void increment_increases_by_one() {
        Pointer pointer = AtomicPointer.zeroed();
        pointer.increment();
        assertThat(pointer.get(), equalTo(1));
    }

    @Test
    public void setToIndex_sets_the_index() {
        Pointer pointer = AtomicPointer.zeroed();
        pointer.setToIndex(99);
        assertThat(pointer.get(), equalTo(99));
    }
}
