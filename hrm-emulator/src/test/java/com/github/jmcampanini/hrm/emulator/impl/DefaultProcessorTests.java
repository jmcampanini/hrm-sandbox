package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Command;
import com.github.jmcampanini.hrm.emulator.Processor;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.github.jmcampanini.hrm.emulator.commands.InboxCommand;
import com.github.jmcampanini.hrm.emulator.commands.NoopCommand;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DefaultProcessor}.
 */
public class DefaultProcessorTests {

    private static final int THING_COUNT = 8;

    private final Processor processor = buildProcessor(THING_COUNT);

    private Processor buildProcessor(int thingCount) {
        List<Thing> things = Lists.newArrayListWithCapacity(thingCount);
        for (int i = 0; i < thingCount; i++) {
            char alpha = (char) ('a' + i);
            things.add(Thing.of(String.valueOf(alpha)));
        }
        return new DefaultProcessor(
                DefaultInbox.withValues(things),
                new DefaultOutbox(),
                new DefaultWorker(),
                DefaultProgramCounter.zeroed());
    }

    @Test
    public void end_if_counter_bigger_than_program_list() {
        List<Command> commands = ImmutableList.of(
                NoopCommand.INSTANCE,
                NoopCommand.INSTANCE);

        this.processor.run(commands);
        assertThat(this.processor.programCounter().get(), greaterThanOrEqualTo(commands.size()));
    }

    @Test
    public void steps_increase_for_each_step() {
        List<Command> commands1 = ImmutableList.of(NoopCommand.INSTANCE);
        List<Command> commands2 = ImmutableList.of(NoopCommand.INSTANCE, NoopCommand.INSTANCE);
        List<Command> commands3 = ImmutableList.of(NoopCommand.INSTANCE, NoopCommand.INSTANCE, NoopCommand.INSTANCE);

        this.processor.run(commands1);
        assertThat(this.processor.steps(), equalTo(1));

        this.processor.run(commands2);
        assertThat(this.processor.steps(), equalTo(3));

        this.processor.run(commands3);
        assertThat(this.processor.steps(), equalTo(6));
    }

    @Test
    public void end_gracefully_on_programEndException() {
        List<Command> commands = Lists.newArrayListWithCapacity(THING_COUNT + 1);
        for (int i = 0; i < THING_COUNT + 1; i++) {
            commands.add(InboxCommand.INSTANCE);
        }

        this.processor.run(commands);
        assertThat(this.processor.steps(), equalTo(THING_COUNT)); // the size of the inbox
    }

    @Test
    public void works_with_empty_program() {
        List<Command> commands = ImmutableList.of();

        this.processor.run(commands);
        assertThat(this.processor.programCounter().get(), equalTo(0));
    }
}
