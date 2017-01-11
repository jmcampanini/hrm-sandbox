package com.github.jmcampanini.hrm.emulator;

/**
 * A runtime exception thrown by the {@link Processor}.
 */
public class ProcessorException extends RuntimeException {

    public ProcessorException(String message) {
        super(message);
    }
}
