package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.commands.NoopCommand;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link DefaultProcessor}.
 */
public class DefaultProcessorTests {

    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Processor processor = new DefaultProcessor();

    @Test
    public void end_if_counter_bigger_than_program_list() {
        List<Command> program = ImmutableList.of(
                NoopCommand.INSTANCE,
                NoopCommand.INSTANCE);

        int steps = this.processor.run(this.cpu, program);
        assertThat(steps, equalTo(2));
    }

    @Test
    public void steps_match_noop_counts() {
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            int noopCount = 3 + rnd.nextInt(5);
            List<Command> program = Collections.nCopies(noopCount, NoopCommand.INSTANCE);

            int steps = this.processor.run(this.cpu, program);
            assertThat(steps, equalTo(noopCount));
        }
    }

    @Test
    public void end_gracefully_on_programEndException() throws ProgramEndSignal {
        List<Command> program = ImmutableList.of(
                NoopCommand.INSTANCE,
                NoopCommand.INSTANCE,
                new EndSignalCommand(),
                NoopCommand.INSTANCE,
                NoopCommand.INSTANCE);

        int steps = this.processor.run(this.cpu, program);
        assertThat(steps, equalTo(2));
    }

    @Test
    public void works_with_empty_program() {
        List<Command> program = ImmutableList.of();
        int steps = this.processor.run(this.cpu, program);
        assertThat(steps, equalTo(0));
    }

    /**
     * Command that just throws a {@link ProgramEndSignal}.
     */
    private class EndSignalCommand implements Command {
        @Override
        public void execute(Cpu cpu, Pointer cmdPointer) throws ProgramEndSignal {
            throw new ProgramEndSignal();
        }
    }
}
