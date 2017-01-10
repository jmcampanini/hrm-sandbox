package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Inbox;
import com.github.jmcampanini.hrm.emulator.ProgramEndException;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DefaultInboxTests {

    @Test(expected = ProgramEndException.class)
    public void program_end_thrown_when_empty() throws ProgramEndException {
        Inbox inbox = DefaultInbox.empty();
        inbox.take();
    }

    @Test
    public void take_gets_next_value() {
        Inbox inbox = DefaultInbox.withValues(ImmutableList.of(
                Thing.of("a"),
                Thing.of("b"),
                Thing.of("c")));

        try {
            assertThat(inbox.take().value(), equalTo("a"));
            assertThat(inbox.take().value(), equalTo("b"));
            assertThat(inbox.take().value(), equalTo("c"));

            inbox.take();
            fail("failed since the last take should throw ProgramEnd");

        } catch (ProgramEndException e) {
            // success!
        }
    }
}