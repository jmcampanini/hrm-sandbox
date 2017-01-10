package com.github.jmcampanini.hrm.emulator;

public interface Inbox {

    String take() throws ProgramEndException;
}
