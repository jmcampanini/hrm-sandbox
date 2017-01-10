package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.ProgramCounter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Default implementation of {@link ProgramCounter}.
 */
public class DefaultProgramCounter implements ProgramCounter {

    private final AtomicInteger index;

    DefaultProgramCounter(int index) {
        this.index = new AtomicInteger(index);
    }

    public static ProgramCounter zeroed() {
        return new DefaultProgramCounter(0);
    }

    public static ProgramCounter startingAt(int index) {
        return new DefaultProgramCounter(index);
    }

    @Override
    public int get() {
        return this.index.get();
    }

    @Override
    public void increment() {
        this.index.incrementAndGet();
    }

    @Override
    public void setToIndex(int index) {
        this.index.set(index);
    }
}
