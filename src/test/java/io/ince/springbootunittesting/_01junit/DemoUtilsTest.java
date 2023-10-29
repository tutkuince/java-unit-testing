package io.ince.springbootunittesting._01junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTest {

    @Test
    void testEqualsAndNotEquals() {
        DemoUtils demoUtils = new DemoUtils();
        assertEquals(6, demoUtils.add(2, 4), "2 + 4 must be 6");
        assertNotEquals(1, demoUtils.add(1, 1), "1 + 1 must be 2");
    }

    @Test
    void testObjectIsNull() {
        DemoUtils demoUtils = new DemoUtils();
        assertNull(demoUtils.checkNull(null), "Return should be null");
    }

    @Test
    void testObjectIsNotNull() {
        DemoUtils demoUtils = new DemoUtils();
        assertEquals(demoUtils, demoUtils.checkNull(demoUtils), "Return should be null");
    }
}