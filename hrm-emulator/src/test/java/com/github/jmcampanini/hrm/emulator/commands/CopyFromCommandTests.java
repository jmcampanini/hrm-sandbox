package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link CopyFromCommand}.
 */
public class CopyFromCommandTests {

    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test(expected = ProcessorException.class)
    public void errors_if_floor_empty() throws ProgramEndSignal {
        int tileNum = 1;
        when(this.cpu.floor().get(tileNum)).thenReturn(Optional.empty());

        CopyFromCommand command = CopyFromCommand.fromTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);
    }

    @Test
    public void sets_worker_to_floor_value() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of('a');
        when(this.cpu.floor().get(tileNum)).thenReturn(Optional.of(thing));

        CopyFromCommand command = CopyFromCommand.fromTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);

        verify(this.cpu.worker()).setThing(eq(thing));
    }

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of('a');
        when(this.cpu.floor().get(tileNum)).thenReturn(Optional.of(thing));

        CopyFromCommand command = CopyFromCommand.fromTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);
        assertThat(this.cmdPointer.get(), equalTo(1));
    }
}
