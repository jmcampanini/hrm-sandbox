package com.github.jmcampanini.hrm.emulator;

/**
 * Represents a command for the {@link Processor}.
 */
public interface Command {

    void execute(Processor processor) throws ProgramEndException;
}
