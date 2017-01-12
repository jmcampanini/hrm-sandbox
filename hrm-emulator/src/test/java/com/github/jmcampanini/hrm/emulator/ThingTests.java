package com.github.jmcampanini.hrm.emulator;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link Thing}.
 */
public class ThingTests {

    @Test(expected = IllegalArgumentException.class)
    public void char_cannot_be_blank() {
        Thing.of(' ');
    }

    @Test(expected = IllegalArgumentException.class)
    public void char_must_be_letter() {
        Thing.of('-');
    }

    @Test
    public void char_will_be_lowercased() {
        assertThat(Thing.of('A').value(), equalTo("a"));
    }

    @Test
    public void int_works() {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            int vali = rnd.nextInt(10) - 5;
            assertThat(Thing.of(vali).value(), equalTo(String.valueOf(vali)));
        }
    }
}
