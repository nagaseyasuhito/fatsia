package com.github.nagaseyasuhito.fatsia.criteria.operator;

public interface Comparison<T extends Comparable<?>> extends Not<T> {
    T getValue();

    void setValue(T value);
}
