package com.github.jmcampanini.hrm.emulator;

import org.immutables.value.Value;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a thing in the HRM CPU.
 */
@Value.Immutable
public interface Thing {

    String value();

    static Thing of(char value) {
        checkArgument(Character.isLetter(value));
        return ImmutableThing.builder()
                .value(String.valueOf(Character.toLowerCase(value)))
                .build();
    }

    static Thing of(int value) {
        return ImmutableThing.builder()
                .value(String.valueOf(value))
                .build();
    }

    static Thing of(String value) {
        checkArgument(value != null);

        try {
            return Thing.of(Integer.parseInt(value));

        } catch (NumberFormatException e) {
            checkArgument(value.length() == 1, "Must be single character");
            return Thing.of(value.charAt(0));
        }
    }
}
