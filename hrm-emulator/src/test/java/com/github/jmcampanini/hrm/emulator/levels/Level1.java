package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.commands.InboxCommand;
import com.github.jmcampanini.hrm.emulator.commands.OutboxCommand;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

/**
 * Test that simulates Level 1.
 */
public class Level1 extends AbstractLevel {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE);

    @Test
    public void scenario_1() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("a", "b", "c"),
                ImmutableList.of("a", "b", "c"));
    }

    @Test
    public void scenario_2() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("x", "y", "z"),
                ImmutableList.of("x", "y", "z"));
    }

    @Test
    public void scenario_3() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("p", "i", "e"),
                ImmutableList.of("p", "i", "e"));
    }
}
