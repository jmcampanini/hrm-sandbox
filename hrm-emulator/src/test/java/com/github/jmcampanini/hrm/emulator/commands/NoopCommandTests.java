package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import com.github.jmcampanini.hrm.emulator.Thing;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link NoopCommand}.
 */
public class NoopCommandTests {

    private static final Thing THING_A = Thing.of("a");

    private final NoopCommand command = NoopCommand.INSTANCE.INSTANCE;
    private final Processor processor = mock(Processor.class, RETURNS_DEEP_STUBS);

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        when(this.processor.worker().thing()).thenReturn(Optional.of(THING_A));
        this.command.execute(this.processor);
        verify(this.processor.programCounter()).increment();
    }
}
