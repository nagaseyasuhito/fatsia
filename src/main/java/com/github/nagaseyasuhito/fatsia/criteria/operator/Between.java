package com.github.nagaseyasuhito.fatsia.criteria.operator;

public interface Between<T extends Comparable<?>> extends Not<T> {
    T getFrom();

    T getTo();

    void setFrom(T from);

    void setTo(T to);
}