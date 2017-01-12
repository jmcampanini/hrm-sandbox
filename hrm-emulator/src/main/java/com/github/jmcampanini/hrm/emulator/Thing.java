package com.github.jmcampanini.hrm.emulator;

import com.google.common.base.Strings;
import org.immutables.value.Value;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a thing in the HRM CPU.
 */
@Value.Immutable
public interface Thing {

    String value();

    /**
     * Constructs a {@link Thing} using a string as the thing.
     *
     * @throws IllegalArgumentException if the string is null or empty
     */
    static Thing of(String value) {
        checkArgument(!Strings.isNullOrEmpty(value));
        return ImmutableThing.builder()
                .value(value)
                .build();
    }

    static Thing of(char value) {
        return of(String.valueOf(value));
    }
}
