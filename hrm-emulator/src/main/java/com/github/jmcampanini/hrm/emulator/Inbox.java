package com.github.jmcampanini.hrm.emulator;

/**
 * Represents the input of the HRM CPU.
 */
public interface Inbox {

    /**
     * Returns the next {@link Value} from the inbox.
     *
     * @throws ProgramEndException when the inbox is empty
     */
    Value take() throws ProgramEndException;
}
