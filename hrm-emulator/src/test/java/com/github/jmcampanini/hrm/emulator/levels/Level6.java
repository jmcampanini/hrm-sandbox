package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.commands.*;
import com.github.jmcampanini.hrm.emulator.impl.DefaultFloor;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that simulates Level 6.
 */
public class Level6 extends AbstractLevel {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            CopyToCommand.toTile(0),
            InboxCommand.INSTANCE,
            AddCommand.withTile(0),
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
                ImmutableList.of("1", "2", "9", "1", "-5", "1", "1", "2"),
                ImmutableList.of("3", "10", "-4", "3"));
    }

    @Test
    public void scenario_2() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8"),
                ImmutableList.of("3", "7", "11", "15"));
    }

    @Test
    public void scenario_3() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("1", "-1", "2", "-2", "3", "-3", "4", "-4"),
                ImmutableList.of("0", "0", "0", "0"));
    }
}
