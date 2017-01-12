package com.github.jmcampanini.hrm.emulator;

/**
 * Thrown when the {@link Worker} is expected to contain a {@link Thing} but is empty.
 */
public class WorkerEmptyException extends ProcessorException {

    public WorkerEmptyException() {
        super("Worker is empty");
    }
}
