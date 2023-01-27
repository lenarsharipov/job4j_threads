package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MainTest {
    @Test
    void whenMultiCallHasNextThenTrue() {
        Main it = new Main(
                new int[] {1, 2, 3}
        );
        boolean rsl = it.hasNext();
        assertThat(rsl).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenReadSequence() {
        Main it = new Main(
                new int[] {1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }

}