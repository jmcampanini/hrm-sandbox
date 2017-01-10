package com.github.jmcampanini.hrm.emulator;

/**
 * Thrown when {@link Inbox#take} is called on an empty inbox. This signals the end of a program. It is a checked
 * exception so all calls to take will need to ensure they hand this case.
 */
public class ProgramEndException extends Exception {
}
