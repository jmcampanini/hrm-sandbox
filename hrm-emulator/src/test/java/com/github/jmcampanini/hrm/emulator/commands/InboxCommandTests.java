package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Cpu;
import com.github.jmcampanini.hrm.emulator.Pointer;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link InboxCommand}.
 */
public class InboxCommandTests {

    private final InboxCommand command = InboxCommand.INSTANCE;
    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        this.command.execute(this.cpu, this.cmdPointer);
        assertThat(cmdPointer.get(), equalTo(1));
    }

    @Test
    public void sets_worker_to_inbox_head() throws ProgramEndSignal {
        Thing thing = Thing.of('a');
        when(this.cpu.inbox().take()).thenReturn(thing);

        this.command.execute(this.cpu, cmdPointer);
        verify(this.cpu.worker()).setThing(eq(thing));
    }
}
