package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.*;

import java.util.List;

/**
 * Default implementation of {@link Processor}.
 */
public class DefaultProcessor implements Processor {

    @Override
    public int run(Cpu cpu, List<Command> program) {
        Pointer cmdPointer = AtomicPointer.zeroed();
        int steps = 0;

        try {
            while (cmdPointer.get() < program.size()) {
                Command nextCommand = program.get(cmdPointer.get());
                nextCommand.execute(cpu, cmdPointer);
                steps++;
            }

        } catch (ProgramEndSignal e) {
            // ending gracefully
        }

        return steps;
    }
}
