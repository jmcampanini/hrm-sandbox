package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.commands.CopyFromCommand;
import com.github.jmcampanini.hrm.emulator.commands.OutboxCommand;
import com.github.jmcampanini.hrm.emulator.impl.DefaultFloor;
import com.github.jmcampanini.hrm.emulator.impl.FixedLengthInbox;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test that simulates Level 3.
 */
public class Level3 {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            CopyFromCommand.fromTile(4),
            OutboxCommand.INSTANCE,
            CopyFromCommand.fromTile(0),
            OutboxCommand.INSTANCE,
            CopyFromCommand.fromTile(3),
            OutboxCommand.INSTANCE);

    @Test
    public void scenario_1() {
        assertProgramWorks(
                ImmutableList.of(-99, -99, -99, -99),
                ImmutableList.of("b", "u", "g"));
    }

    private void assertProgramWorks(ImmutableList<Integer> input, ImmutableList<String> output) {
        Inbox inbox = FixedLengthInbox.withValues(input.stream()
                .map(Thing::of)
                .collect(Collectors.toList()));

        Floor floor = DefaultFloor.prepopulated(6, ImmutableList.of(
                Thing.of('u'),      // 0
                Thing.of('j'),      // 1
                Thing.of('x'),      // 2
                Thing.of('g'),      // 3
                Thing.of('b'),      // 4
                Thing.of('e')));    // 5

        Cpu cpu = ImmutableCpu.builder()
                .inbox(inbox)
                .floor(floor)
                .build();

        cpu.processor().run(cpu, PROGRAM);

        List<String> data = cpu.outbox()
                .data().stream()
                .map(Thing::value)
                .collect(Collectors.toList());

        assertThat(data, equalTo(output));
    }
}
