package com.github.jmcampanini.hrm.emulator;

import java.util.List;

/**
 * Represents the HMR CPU.
 */
public interface Processor {

    Inbox inbox();

    Outbox outbox();

    Worker worker();

    ProgramCounter programCounter();

    long steps();

    void run(List<Command> program);
}
