package com.github.nagaseyasuhito.fatsia.criteria.operator;

public interface GreaterEqual<T extends Comparable<? super T>> extends Not<T> {
    T getValue();

    void setValue(T value);
}