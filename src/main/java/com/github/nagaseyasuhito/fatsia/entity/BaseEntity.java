package com.github.nagaseyasuhito.fatsia.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.CompareToBuilder;

@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable, Comparable<BaseEntity<T>> {
    private static final long serialVersionUID = -1908110108935988177L;

    public abstract T getId();

    @Override
    public int compareTo(BaseEntity<T> o) {
        return new CompareToBuilder().append(this.getId(), o.getId()).toComparison();
    }

    @Override
    public int hashCode() {
        return this.getId() == null ? super.hashCode() : this.getId().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!this.getClass().isAssignableFrom(object.getClass()) && !object.getClass().isAssignableFrom(this.getClass())) {
            return false;
        }

        BaseEntity<?> entity = (BaseEntity<?>) object;
        if (this.getId() == null || entity.getId() == null) {
            return this.equals(entity);
        }

        return this.getId().equals(entity.getId());
    }
}
