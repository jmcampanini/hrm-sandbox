package com.github.jmcampanini.hrm.emulator;

/**
 * A runtime exception thrown by the program.
 */
public class ProgramException extends RuntimeException {

    public ProgramException(String message) {
        super(message);
    }
}
