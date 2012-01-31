package com.github.nagaseyasuhito.fatsia.criteria.operator;

public interface Null<T> extends Not<T> {
    boolean isNull();

    void setNull(boolean isNull);
}