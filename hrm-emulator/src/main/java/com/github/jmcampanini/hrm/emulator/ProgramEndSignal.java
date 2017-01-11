package com.github.jmcampanini.hrm.emulator;

/**
 * This signals the end of a program. It is a checked exception so all calls to take will need to ensure they hand this
 * case.
 */
public class ProgramEndSignal extends Exception {
}
