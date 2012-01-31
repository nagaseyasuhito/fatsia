package com.github.nagaseyasuhito.fatsia.dao.operator;

public interface GreaterThan<T extends Comparable<? super T>> extends Not<T> {
    T getValue();

    void setValue(T value);
}