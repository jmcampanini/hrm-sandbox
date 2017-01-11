package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.*;
import com.github.jmcampanini.hrm.emulator.commands.InboxCommand;
import com.github.jmcampanini.hrm.emulator.commands.OutboxCommand;
import com.github.jmcampanini.hrm.emulator.impl.FixedLengthInbox;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test that simulates Level 1.
 */
public class Level1 {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE);

    @Test
    public void scenario_1() {
        assertProgramWorks(
                ImmutableList.of("a", "b", "c"),
                ImmutableList.of("a", "b", "c"));
    }

    @Test
    public void scenario_2() {
        assertProgramWorks(
                ImmutableList.of("x", "y", "z"),
                ImmutableList.of("x", "y", "z"));
    }

    @Test
    public void scenario_3() {
        assertProgramWorks(
                ImmutableList.of("p", "i", "e"),
                ImmutableList.of("p", "i", "e"));
    }

    private void assertProgramWorks(ImmutableList<String> input, ImmutableList<String> output) {
        Inbox inbox = FixedLengthInbox.withValues(input.stream()
                .map(Thing::of)
                .collect(Collectors.toList()));

        Cpu cpu = ImmutableCpu.builder()
                .inbox(inbox)
                .build();

        cpu.processor().run(cpu, PROGRAM);

        List<String> data = cpu.outbox()
                .data().stream()
                .map(Thing::value)
                .collect(Collectors.toList());

        assertThat(data, equalTo(output));
    }
}
