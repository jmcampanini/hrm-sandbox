package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Inbox;
import com.github.jmcampanini.hrm.emulator.ProgramEndException;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.google.common.collect.Queues;

import java.util.Queue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@link Inbox}. It is not thread-safe.
 */
public class DefaultInbox implements Inbox {

    private final Queue<Thing> queue;

    DefaultInbox(Queue<Thing> queue) {
        this.queue = checkNotNull(queue);
    }

    public static Inbox empty() {
        return new DefaultInbox(Queues.newArrayDeque());
    }

    public static Inbox withValues(Iterable<Thing> things) {
        checkNotNull(things);
        return new DefaultInbox(Queues.newArrayDeque(things));
    }

    @Override
    public Thing take() throws ProgramEndException {
        if (this.queue.isEmpty()) {
            throw new ProgramEndException();
        }

        return this.queue.remove();
    }
}
