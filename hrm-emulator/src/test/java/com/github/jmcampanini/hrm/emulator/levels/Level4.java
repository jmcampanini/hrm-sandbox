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
 * Test that simulates Level 4.
 */
public class Level4 {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            CopyToCommand.toTile(0),
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            CopyFromCommand.fromTile(0),
            OutboxCommand.INSTANCE,
            JumpCommand.jumpTo(0));

    @Test
    public void scenario_1() {
        assertProgramWorks(
                ImmutableList.of("1", "8", "f", "g", "4", "5"),
                ImmutableList.of("8", "1", "g", "f", "5", "4"));
    }

    private void assertProgramWorks(ImmutableList<String> input, ImmutableList<String> output) {
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
