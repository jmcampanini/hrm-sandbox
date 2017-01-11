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
    default void execute(Processor processor) throws ProgramEndException {
        Thing thing = processor.worker()
                .thing()
                .orElseThrow(() -> new ProcessorException("The worker has no thing to put into the outbox."));

        processor.outbox().put(thing);
        processor.programCounter().increment();
    }
}
