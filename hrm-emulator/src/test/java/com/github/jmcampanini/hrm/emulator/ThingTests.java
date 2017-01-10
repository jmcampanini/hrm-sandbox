package com.github.jmcampanini.hrm.emulator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link Thing}.
 */
public class ThingTests {

    @Test
    public void of_constructor_works_with_strings() {
        String expectedValue = "a";
        Thing a = Thing.of(expectedValue);
        assertThat(a.value(), equalTo(expectedValue));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_be_empty_string() {
        Thing.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_be_null_string() {
        Thing.of(null);
    }
}
