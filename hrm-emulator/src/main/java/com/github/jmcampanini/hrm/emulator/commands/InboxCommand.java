package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.*;
import org.immutables.value.Value;

/**
 * The {@link Command} to take input from the {@link com.github.jmcampanini.hrm.emulator.Inbox}.
 */
@Value.Immutable
public interface InboxCommand extends Command {

    InboxCommand INSTANCE = ImmutableInboxCommand.builder().build();

    @Override
    default void execute(Cpu cpu, Pointer cmdPointer) throws ProgramEndSignal {
        Thing thing = cpu.inbox().take();
        cpu.worker().setThing(thing);
        cmdPointer.increment();
    }
}
