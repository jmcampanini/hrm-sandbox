package com.github.jmcampanini.hrm.emulator;

import java.util.List;

/**
 * Represents the output of the HRM CPU.
 */
public interface Outbox {

    /**
     * Outputs the {@link Value} from the HRM CPU. The value cannot be null.
     */
    void put(Value value);

    /**
     * The data that has been outputted, in order.
     */
    List<Value> data();
}
