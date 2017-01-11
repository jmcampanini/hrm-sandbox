package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Cpu;
import com.github.jmcampanini.hrm.emulator.Pointer;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link NoopCommand}.
 */
public class NoopCommandTests {

    private final NoopCommand command = NoopCommand.INSTANCE;
    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test
    public void increments_the_counter() throws ProgramEndSignal {
        this.command.execute(this.cpu, this.cmdPointer);
        assertThat(this.cmdPointer.get(), equalTo(1));
    }
}
