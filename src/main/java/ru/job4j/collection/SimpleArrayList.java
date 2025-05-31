package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        newSize();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T replaced = container[index];
        container[index] = newValue;
        return replaced;
    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modCount++;
        container[container.length - 1] = null;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
       return size;
    }

    private void newSize() {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        if (container.length == 0) {
            container = (T[]) new Object[2];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expModCount = modCount;
            int idx = 0;

            @Override
            public boolean hasNext() {
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return idx < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[idx++];
            }
        };
    }
}