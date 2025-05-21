package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index = 0;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    public boolean hasNext() {
        boolean rsl = false;
        if (data.length > 0) {
            while (index <= data.length - 1 && data[index] == null) {
                index++;
            }
        }
        if (index <= data.length - 1 && data[index] != null) {
            return true;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}