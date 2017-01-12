package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import com.github.jmcampanini.hrm.emulator.impl.DefaultWorker;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link AddCommand}.
 */
public class AddCommandTests {

    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of(10);
        when(this.cpu.floor().get(tileNum)).thenReturn(Optional.of(thing));
        when(this.cpu.worker().thing()).thenReturn(Optional.of(thing));

        AddCommand command = AddCommand.withTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);
        assertThat(this.cmdPointer.get(), equalTo(1));
    }

    @Test(expected = TileEmptyException.class)
    public void tile_must_have_thing() throws ProgramEndSignal {
        AddCommand command = AddCommand.withTile(1);
        command.execute(this.cpu, this.cmdPointer);
    }

    @Test(expected = WorkerEmptyException.class)
    public void worker_must_have_thing() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of(10);
        when(this.cpu.floor().get(tileNum)).thenReturn(Optional.of(thing));

        AddCommand command = AddCommand.withTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);
    }

    @Test
    public void adds_numbers_correctly() throws ProgramEndSignal {
        int tileNum = 1;
        Thing thing = Thing.of(10);
        Worker worker = new DefaultWorker();
        worker.setThing(thing);

        when(this.cpu.floor().get(tileNum)).thenReturn(Optional.of(thing));
        when(this.cpu.worker()).thenReturn(worker);

        AddCommand command = AddCommand.withTile(tileNum);
        command.execute(this.cpu, this.cmdPointer);

        assertThat(this.cpu.worker().thing().isPresent(), equalTo(true));
        assertThat(this.cpu.worker().thing().get().value(), equalTo("20"));
    }
}
