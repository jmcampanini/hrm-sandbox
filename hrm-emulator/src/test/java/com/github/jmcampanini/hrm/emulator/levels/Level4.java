package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.commands.*;
import com.github.jmcampanini.hrm.emulator.impl.DefaultFloor;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that simulates Level 4.
 */
public class Level4 extends AbstractLevel {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            CopyToCommand.toTile(0),
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            CopyFromCommand.fromTile(0),
            OutboxCommand.INSTANCE,
            JumpCommand.jumpTo(0));

    @Before
    public void before() {
        setFloor(DefaultFloor.empty(3));
    }

    @Test
    public void scenario_1() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("1", "8", "f", "g", "4", "5"),
                ImmutableList.of("8", "1", "g", "f", "5", "4"));
    }
}
