package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.ProgramEndException;
import com.github.jmcampanini.hrm.emulator.Thing;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link InboxCommand}.
 */
public class InboxCommandTests {

    private final InboxCommand command = InboxCommand.INSTANCE;
    private final Processor processor = mock(Processor.class, RETURNS_DEEP_STUBS);

    @Test
    public void increments_the_counter() throws ProgramEndException {
        this.command.execute(this.processor);
        verify(this.processor.programCounter()).increment();
    }

    @Test
    public void sets_worker_to_inbox_head() throws ProgramEndException {
        Thing thing = Thing.of("a");
        when(this.processor.inbox().take()).thenReturn(thing);

        this.command.execute(this.processor);
        verify(this.processor.worker()).setThing(eq(thing));
    }
}
