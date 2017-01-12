package com.github.jmcampanini.hrm.emulator;

import com.github.jmcampanini.hrm.emulator.impl.*;
import org.immutables.value.Value;

/**
 * Represents the HMR CPU.
 */
@Value.Immutable
public interface Cpu {

    @Value.Default
    default Inbox inbox() {
        return FixedLengthInbox.empty();
    }

    @Value.Default
    default Outbox outbox() {
        return new ArrayListOutbox();
    }

    @Value.Default
    default Worker worker() {
        return new DefaultWorker();
    }

    @Value.Default
    default Floor floor() {
        return DefaultFloor.empty(0);
    }

    @Value.Default
    default Processor processor() {
        return new DefaultProcessor();
    }
}
