package com.github.jmcampanini.hrm.emulator.commands;

import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.ProgramEndSignal;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Mockito.*;

/**
 * Test for {@link JumpCommand}.
 */
public class JumpCommandTests {

    private final Random rnd = new Random();
    private final Processor processor = mock(Processor.class, RETURNS_DEEP_STUBS);

    @Test
    public void jumps_the_counter() throws ProgramEndSignal {
        for (int i = 0; i < 10; i++) {
            int jumpTo = this.rnd.nextInt();
            JumpCommand command = JumpCommand.jumpTo(jumpTo);

            command.execute(this.processor);
            verify(this.processor.programCounter()).setToIndex(eq(jumpTo));
        }
    }
}
