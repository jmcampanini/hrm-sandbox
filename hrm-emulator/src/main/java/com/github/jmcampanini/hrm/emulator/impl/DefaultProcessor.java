package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@link Processor}.
 */
public class DefaultProcessor implements Processor {

    private final Inbox inbox;
    private final Outbox outbox;
    private final Worker worker;
    private final ProgramCounter programCounter;
    private final AtomicInteger steps;

    public DefaultProcessor(Inbox inbox, Outbox outbox, Worker worker, ProgramCounter programCounter) {
        this.inbox = checkNotNull(inbox);
        this.outbox = checkNotNull(outbox);
        this.worker = checkNotNull(worker);
        this.programCounter = checkNotNull(programCounter);
        this.steps = new AtomicInteger(0);
    }

    @Override
    public Inbox inbox() {
        return this.inbox;
    }

    @Override
    public Outbox outbox() {
        return this.outbox;
    }

    @Override
    public Worker worker() {
        return this.worker;
    }

    @Override
    public ProgramCounter programCounter() {
        return this.programCounter;
    }

    @Override
    public int steps() {
        return this.steps.get();
    }

    @Override
    public void run(List<Command> program) {
        try {
            this.programCounter.setToIndex(0);

            while (this.programCounter().get() < program.size()) {
                int nextCommandIndex = this.programCounter.get();
                Command nextCommand = program.get(nextCommandIndex);
                nextCommand.execute(this);
                this.steps.incrementAndGet();
            }

        } catch (ProgramEndSignal e) {
            // ending gracefully
        }
    }
}
