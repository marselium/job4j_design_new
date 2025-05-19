package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNamesIsZero() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }


    @Test
    void checkNameNotContainsEquality() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(() -> nl.parse("doesn't have equality"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol");
    }

    @Test
    void checkStartsWithEquality() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(() -> nl.parse("= start"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkDoesNotContainValue() {
        NameLoad nl = new NameLoad();
        assertThatThrownBy(() -> nl.parse("start = "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }
}