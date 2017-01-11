package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.ProgramEndException;
import com.github.jmcampanini.hrm.emulator.ProcessorException;
import com.github.jmcampanini.hrm.emulator.Thing;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link OutboxCommand}.
 */
public class OutboxCommandTests {

    private static final Thing THING_A = Thing.of("a");

    private final OutboxCommand command = OutboxCommand.INSTANCE;
    private final Processor processor = mock(Processor.class, RETURNS_DEEP_STUBS);

    @Test(expected = ProcessorException.class)
    public void error_if_worker_empty() throws ProgramEndException {
        when(this.processor.worker().thing()).thenReturn(Optional.empty());
        this.command.execute(this.processor);
    }

    @Test
    public void puts_worker_thing_in_outbox() throws ProgramEndException {
        when(this.processor.worker().thing()).thenReturn(Optional.of(THING_A));
        this.command.execute(this.processor);
        verify(this.processor.outbox()).put(eq(THING_A));
    }

    @Test
    public void increments_the_counter() throws ProgramEndException {
        when(this.processor.worker().thing()).thenReturn(Optional.of(THING_A));
        this.command.execute(this.processor);
        verify(this.processor.programCounter()).increment();
    }
}
