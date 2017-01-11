package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Cpu;
import com.github.jmcampanini.hrm.emulator.Pointer;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import com.github.jmcampanini.hrm.emulator.impl.AtomicPointer;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

/**
 * Test for {@link JumpCommand}.
 */
public class JumpCommandTests {

    private final Random rnd = new Random();
    private final Cpu cpu = mock(Cpu.class, RETURNS_DEEP_STUBS);
    private final Pointer cmdPointer = AtomicPointer.zeroed();

    @Test
    public void jumps_the_counter() throws ProgramEndSignal {
        for (int i = 0; i < 10; i++) {
            int jumpTo = this.rnd.nextInt();
            JumpCommand command = JumpCommand.jumpTo(jumpTo);

            command.execute(this.cpu, this.cmdPointer);
            assertThat(this.cmdPointer.get(), equalTo(jumpTo));
        }
    }
}
