package task.raif.enumContainer;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void byTitle() {
        assertEquals(Operations.MORE_THAN, Operations.byTitle("moreThan").get());
        assertEquals(Operations.LESS_THAN, Operations.byTitle("lessThan").get());
        assertEquals(Operations.EQUAL, Operations.byTitle("equal").get());
        assertEquals(Optional.empty(), Operations.byTitle("afas"));
        assertEquals(Optional.empty(), Operations.byTitle(null));
    }

    @Test
    void values() {
        assertArrayEquals(new Operations[] {Operations.LESS_THAN, Operations.MORE_THAN, Operations.EQUAL}
                , Operations.values());
    }

    @Test
    void valueOf() {
        assertEquals(Operations.MORE_THAN, Operations.valueOf("MORE_THAN"));
        assertEquals(Operations.LESS_THAN, Operations.valueOf("LESS_THAN"));
        assertEquals(Operations.EQUAL, Operations.valueOf("EQUAL"));
        assertThrows(IllegalArgumentException.class, () -> Operations.valueOf("afas"));
        assertThrows(NullPointerException.class, () -> Operations.valueOf(null));
    }

}