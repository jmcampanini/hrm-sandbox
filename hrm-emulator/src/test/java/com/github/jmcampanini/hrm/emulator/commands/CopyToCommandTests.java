package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link CopyToCommand}.
 */
public class CopyToCommandTests {

    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test(expected = ProcessorException.class)
    public void errors_if_worker_is_empty() throws ProgramEndSignal {
        when(this.cpu.worker().thing()).thenReturn(Optional.empty());

        CopyToCommand command = CopyToCommand.toTile(1);
        command.execute(this.cpu, this.cmdPointer);
    }

    @Test
    public void sets_floor_to_worker_value() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of('a');
        when(this.cpu.worker().thing()).thenReturn(Optional.of(thing));

        CopyToCommand command = CopyToCommand.toTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);

        verify(this.cpu.floor()).set(eq(tileNum), eq(thing));
    }

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of('a');
        when(this.cpu.worker().thing()).thenReturn(Optional.of(thing));

        CopyToCommand command = CopyToCommand.toTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);
        assertThat(this.cmdPointer.get(), equalTo(1));
    }
}
