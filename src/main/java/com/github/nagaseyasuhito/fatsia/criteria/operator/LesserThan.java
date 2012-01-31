package com.github.nagaseyasuhito.fatsia.criteria.operator;

public interface LesserThan<T extends Comparable<? super T>> extends Not<T> {
    T getValue();

    void setValue(T value);
}