package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Conf config = new Conf(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }
}