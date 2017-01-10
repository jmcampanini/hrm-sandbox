package com.github.jmcampanini.hrm.emulator;

public interface Outbox {
    void put(String value);
}
