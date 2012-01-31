package com.github.nagaseyasuhito.fatsia.criteria.operator;

public interface Like<T extends CharSequence> extends Not<T> {
    T getValue();

    void setValue(T value);
}