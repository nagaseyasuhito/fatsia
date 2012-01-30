package com.github.nagaseyasuhito.fatsia.dao.criteria;

public interface Between<T extends Comparable<? super T>> extends Not<T> {
    T getFrom();

    T getTo();

    void setFrom(T from);

    void setTo(T to);
}