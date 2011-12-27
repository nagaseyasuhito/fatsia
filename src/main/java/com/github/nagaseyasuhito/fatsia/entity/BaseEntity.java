package com.github.nagaseyasuhito.fatsia.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable, Comparable<BaseEntity<T>> {
    private static final long serialVersionUID = 1412022620966513223L;

    public abstract T getId();
}
