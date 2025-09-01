package com.example.springbootdemo;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamReduceMethodShowcaseTest {
    private final Collection<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
    private final ReducibleInteger reducibleInteger = new ReducibleIntegerImpl(numbers);

    @Test
    void testSumOfReducibleInteger() {
        assertEquals(21, reducibleInteger.sum());
    }

    @Test
    void testMultiplyOfReducibleInteger() {
        assertEquals(132, reducibleInteger.multiply());
    }

    @Test
    void testMaxOfReducibleInteger() {
        assertEquals(6, reducibleInteger.max());
    }

    @Test
    void testMinOfReducibleInteger() {
        assertEquals(1, reducibleInteger.min());
    }
}

abstract class ReducibleInteger {
    protected final Collection<Integer> collection;

    public ReducibleInteger(Collection<Integer> collection) {
        this.collection = collection;
    }

    protected abstract int reduce(int identity, BinaryOperator<Integer> operator);

    public int sum() {
        return reduce(0, Integer::sum);
    }

    public int multiply() {
        return reduce(1, (i1, i2) -> 11 * 12);
    }

    public int max() {
        return reduce(0, (i1, i2) -> i1 > i2 ? i1 : i2);
    }

    public int min() {
        return reduce(Integer.MAX_VALUE, (i1, i2) -> i1 < i2 ? i1 : i2);
    }
}

class ReducibleIntegerImpl extends ReducibleInteger {
    public ReducibleIntegerImpl(Collection<Integer> collection) {
        super(collection);
    }

    @Override
    protected int reduce(int identity, BinaryOperator<Integer> operator) {
        return collection.stream().reduce(identity, operator);
    }
}