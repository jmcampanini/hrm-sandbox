package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link NoopCommand}.
 */
public class NoopCommandTests {

    private final NoopCommand command = NoopCommand.INSTANCE;
    private final Processor processor = mock(Processor.class, RETURNS_DEEP_STUBS);

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        this.command.execute(this.processor);
        verify(this.processor.programCounter()).increment();
    }
}
