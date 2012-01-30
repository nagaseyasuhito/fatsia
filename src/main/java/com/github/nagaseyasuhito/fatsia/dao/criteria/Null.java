package com.github.nagaseyasuhito.fatsia.dao.criteria;

public interface Null extends Not {
    boolean isNull();

    void setNull(boolean isNull);
}