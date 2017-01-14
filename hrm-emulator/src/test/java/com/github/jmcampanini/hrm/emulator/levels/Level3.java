package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.github.jmcampanini.hrm.emulator.commands.CopyFromCommand;
import com.github.jmcampanini.hrm.emulator.commands.OutboxCommand;
import com.github.jmcampanini.hrm.emulator.impl.DefaultFloor;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

/**
 * Test that simulates Level 3.
 */
public class Level3 extends AbstractLevel {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            CopyFromCommand.fromTile(4),
            OutboxCommand.INSTANCE,
            CopyFromCommand.fromTile(0),
            OutboxCommand.INSTANCE,
            CopyFromCommand.fromTile(3),
            OutboxCommand.INSTANCE);

    @Before
    public void before() {
        setFloor(DefaultFloor.prepopulated(6, ImmutableList.of(
                Thing.of('u'),      // 0
                Thing.of('j'),      // 1
                Thing.of('x'),      // 2
                Thing.of('g'),      // 3
                Thing.of('b'),      // 4
                Thing.of('e'))));    // 5
    }

    @Test
    public void scenario_1() {
        assertOutputMatches(
                PROGRAM,
                ImmutableList.of("-99", "-99", "-99", "-99"),
                ImmutableList.of("b", "u", "g"));
    }
}
