package com.github.jmcampanini.hrm.emulator;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a value in the HRM CPU.
 */
@org.immutables.value.Value.Immutable
public interface Value {

    String value();

    /**
     * Constructs a {@link Value} using a string as the value. The string cannot be null or empty.
     */
    static Value of(String value) {
        checkArgument(!Strings.isNullOrEmpty(value));
        return ImmutableValue.builder()
                .value(value)
                .build();
    }
}
