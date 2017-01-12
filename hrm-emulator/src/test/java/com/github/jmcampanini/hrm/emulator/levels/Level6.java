package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.commands.*;
import com.github.jmcampanini.hrm.emulator.impl.DefaultFloor;
import com.github.jmcampanini.hrm.emulator.impl.FixedLengthInbox;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test that simulates Level 6.
 */
public class Level6 {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            CopyToCommand.toTile(0),
            InboxCommand.INSTANCE,
            AddCommand.withTile(0),
            OutboxCommand.INSTANCE,
            JumpCommand.jumpTo(0));

    @Test
    public void scenario_1() {
        assertProgramWorks(
                ImmutableList.of(1, 2, 9, 1, -5, 1, 1, 2),
                ImmutableList.of("3", "10", "-4", "3"));
    }

    @Test
    public void scenario_2() {
        assertProgramWorks(
                ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8),
                ImmutableList.of("3", "7", "11", "15"));
    }

    @Test
    public void scenario_3() {
        assertProgramWorks(
                ImmutableList.of(1, -1, 2, -2, 3, -3, 4, -4),
                ImmutableList.of("0", "0", "0", "0"));
    }

    private void assertProgramWorks(ImmutableList<Integer> input, ImmutableList<String> output) {
        Inbox inbox = FixedLengthInbox.withValues(input.stream()
                .map(Thing::of)
                .collect(Collectors.toList()));

        Cpu cpu = ImmutableCpu.builder()
                .inbox(inbox)
                .floor(DefaultFloor.empty(3))
                .build();

        cpu.processor().run(cpu, PROGRAM);

        List<String> data = cpu.outbox()
                .data().stream()
                .map(Thing::value)
                .collect(Collectors.toList());

        assertThat(data, equalTo(output));
    }
}
