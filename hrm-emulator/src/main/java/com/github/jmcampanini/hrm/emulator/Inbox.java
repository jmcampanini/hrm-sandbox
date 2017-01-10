package com.github.jmcampanini.hrm.emulator;

/**
 * Represents the input of the HRM CPU.
 */
public interface Inbox {

    /**
     * Returns the next {@link Thing} from the inbox.
     *
     * @throws ProgramEndException when the inbox is empty
     */
    Thing take() throws ProgramEndException;
}
