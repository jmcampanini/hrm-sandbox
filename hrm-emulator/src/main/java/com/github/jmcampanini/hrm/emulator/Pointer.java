package com.github.jmcampanini.hrm.emulator;

/**
 * Represents the location in the program.
 */
public interface Pointer {

    int get();

    void increment();

    void setToIndex(int index);
}
