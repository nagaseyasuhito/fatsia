package com.github.nagaseyasuhito.fatsia.dao.criteria;

public interface Null<T> extends Not<T> {
    boolean isNull();

    void setNull(boolean isNull);
}