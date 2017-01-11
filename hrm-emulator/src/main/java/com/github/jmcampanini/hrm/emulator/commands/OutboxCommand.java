package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.*;
import org.immutables.value.Value;

/**
 * The {@link Command} to put a {@link Value} to the {@link com.github.jmcampanini.hrm.emulator.Outbox}.
 */
@Value.Immutable
public interface OutboxCommand extends Command {

    OutboxCommand INSTANCE = ImmutableOutboxCommand.builder().build();

    @Override
    default void execute(Cpu cpu, Pointer cmdPointer) throws ProgramEndSignal {
        Thing thing = cpu.worker().thing()
                .orElseThrow(() -> new ProcessorException("The worker has no thing to put into the outbox."));

        cpu.outbox().put(thing);
        cmdPointer.increment();
    }
}
