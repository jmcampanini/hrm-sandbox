package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link OutboxCommand}.
 */
public class OutboxCommandTests {

    private static final Thing THING_A = Thing.of('a');

    private final OutboxCommand command = OutboxCommand.INSTANCE;
    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test(expected = WorkerEmptyException.class)
    public void error_if_worker_empty() throws ProgramEndSignal {
        when(this.cpu.worker().thing()).thenReturn(Optional.empty());
        this.command.execute(this.cpu, this.cmdPointer);
    }

    @Test
    public void puts_worker_thing_in_outbox() throws ProgramEndSignal {
        when(this.cpu.worker().thing()).thenReturn(Optional.of(THING_A));
        this.command.execute(this.cpu, this.cmdPointer);
        verify(this.cpu.outbox()).put(eq(THING_A));
    }

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        when(this.cpu.worker().thing()).thenReturn(Optional.of(THING_A));
        this.command.execute(this.cpu, this.cmdPointer);
        assertThat(this.cmdPointer.get(), equalTo(1));
    }
}
