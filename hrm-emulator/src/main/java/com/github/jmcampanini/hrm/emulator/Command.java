package com.github.jmcampanini.hrm.emulator;

/**
 * Represents a command for the {@link Cpu}.
 */
public interface Command {

    void execute(Cpu cpu, Pointer cmdPointer) throws ProgramEndSignal;
}
