package com.github.jmcampanini.hrm.emulator.impl;

import com.github.jmcampanini.hrm.emulator.Floor;
import com.github.jmcampanini.hrm.emulator.Thing;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DefaultFloor}.
 */
public class DefaultFloorTests {

    @Test(expected = IndexOutOfBoundsException.class)
    public void error_on_index_out_of_bounds() {
        int capacity = 2;
        Floor floor = DefaultFloor.empty(capacity);
        floor.get(capacity + 1);
    }

    @Test
    public void create_empty_floor() {
        int capacity = 5;
        Floor floor = DefaultFloor.empty(capacity);

        for (int i = 0; i < capacity; i++) {
            assertThat(floor.get(i).isPresent(), equalTo(false));
        }
    }

    @Test
    public void create_preloaded_floor() {
        Floor floor = DefaultFloor.prepopulated(5,
                Lists.newArrayList(
                        Thing.of("a"),
                        null,
                        Thing.of("b"),
                        null,
                        Thing.of("c")));

        assertThat(floor.get(0).get().value(), equalTo("a"));
        assertThat(floor.get(1).isPresent(), equalTo(false));
        assertThat(floor.get(2).get().value(), equalTo("b"));
        assertThat(floor.get(3).isPresent(), equalTo(false));
        assertThat(floor.get(4).get().value(), equalTo("c"));
    }

    @Test
    public void get_and_set_values() {
        int capacity = 5;
        int times = 10;
        Random rnd = new Random();
        Floor floor = DefaultFloor.empty(capacity);

        for (int i = 0; i < times; i++) {
            int tileNum = rnd.nextInt(capacity);
            char randomLetter = (char)(rnd.nextInt(26) + 'a');
            Thing randomThing = Thing.of(randomLetter);

            floor.set(tileNum, randomThing);
            assertThat(floor.get(tileNum).isPresent(), equalTo(true));
            assertThat(floor.get(tileNum).get(), equalTo(randomThing));
        }
    }
}
