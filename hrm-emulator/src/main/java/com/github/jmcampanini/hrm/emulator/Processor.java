package com.github.jmcampanini.hrm.emulator;

import java.util.List;

/**
 * Processes the program on the provided {@link Cpu}.
 */
public interface Processor {

    /**
     * Returns the number of steps to execute the program.
     */
    int run(Cpu cpu, List<Command> program);
}
