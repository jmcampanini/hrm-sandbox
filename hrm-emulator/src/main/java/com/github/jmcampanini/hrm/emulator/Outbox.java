package com.github.jmcampanini.hrm.emulator;

import java.util.List;

/**
 * Represents the output of the HRM CPU.
 */
public interface Outbox {

    /**
     * Outputs the {@link Thing} from the HRM CPU. The thing cannot be null.
     */
    void put(Thing thing);

    /**
     * The data that has been outputted, in order.
     */
    List<Thing> data();
}
