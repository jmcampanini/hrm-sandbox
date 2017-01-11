package com.github.jmcampanini.hrm.emulator.levels;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.github.jmcampanini.hrm.emulator.commands.InboxCommand;
import com.github.jmcampanini.hrm.emulator.commands.JumpCommand;
import com.github.jmcampanini.hrm.emulator.commands.OutboxCommand;
import com.github.jmcampanini.hrm.emulator.impl.*;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Level2 {

    private static final ImmutableList<Command> PROGRAM = ImmutableList.of(
            InboxCommand.INSTANCE,
            OutboxCommand.INSTANCE,
            JumpCommand.jumpTo(0));

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
        Processor processor = new DefaultProcessor(
                DefaultInbox.withValues(input.stream()
                        .map(s -> Thing.of(s))
                        .collect(Collectors.toList())),
                new DefaultOutbox(),
                new DefaultWorker(),
                DefaultProgramCounter.zeroed());

        processor.run(PROGRAM);

        List<Thing> data = processor.outbox().data();
        assertThat(data.size(), equalTo(output.size()));

        for (int i = 0; i < output.size(); i++) {
            assertThat(data.get(i).value(), equalTo(output.get(i)));
        }
    }
}
