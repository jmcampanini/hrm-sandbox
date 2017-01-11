package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Pointer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Default implementation of {@link Pointer}.
 */
public class AtomicPointer implements Pointer {

    private final AtomicInteger index;

    AtomicPointer(int index) {
        this.index = new AtomicInteger(index);
    }

    public static Pointer zeroed() {
        return new AtomicPointer(0);
    }

    public static Pointer startingAt(int index) {
        return new AtomicPointer(index);
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
