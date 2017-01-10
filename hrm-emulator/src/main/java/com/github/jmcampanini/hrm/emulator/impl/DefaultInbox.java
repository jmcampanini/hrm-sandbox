package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Inbox;
import com.github.jmcampanini.hrm.emulator.ProgramEndException;
import com.github.jmcampanini.hrm.emulator.Value;
import com.google.common.collect.Queues;

import java.util.Queue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@link Inbox}. It is not thread-safe.
 */
public class DefaultInbox implements Inbox {

    private final Queue<Value> queue;

    DefaultInbox(Queue<Value> queue) {
        this.queue = checkNotNull(queue);
    }

    public static Inbox empty() {
        return new DefaultInbox(Queues.newArrayDeque());
    }

    public static Inbox withValues(Iterable<Value> values) {
        checkNotNull(values);
        return new DefaultInbox(Queues.newArrayDeque(values));
    }

    @Override
    public Value take() throws ProgramEndException {
        if (this.queue.isEmpty()) {
            throw new ProgramEndException();
        }

        return this.queue.remove();
    }
}
