package com.github.jmcampanini.hrm.emulator;

/**
 * Represents the location in the program that is currently running.
 */
public interface ProgramCounter {

    int get();

    void increment();

    void setToIndex(int index);
}
