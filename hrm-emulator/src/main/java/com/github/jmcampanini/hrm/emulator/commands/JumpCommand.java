package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.Cpu;
import com.github.jmcampanini.hrm.emulator.Pointer;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import org.immutables.value.Value;

@Value.Immutable
public interface JumpCommand extends Command {

    int index();

    static JumpCommand jumpTo(int index) {
        return ImmutableJumpCommand.builder()
                .index(index)
                .build();
    }

    @Override
    default void execute(Cpu cpu, Pointer cmdPointer) throws ProgramEndSignal {
        cmdPointer.setToIndex(index());
    }
}
