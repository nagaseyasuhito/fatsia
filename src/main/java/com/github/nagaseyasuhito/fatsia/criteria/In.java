package com.github.nagaseyasuhito.fatsia.criteria;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.github.nagaseyasuhito.fatsia.entity.BaseEntity;
import com.google.common.collect.Lists;

public class In<T extends BaseEntity<?>> extends Criteria<T> implements List<T> {

    private List<T> value = Lists.newArrayList();

    @Override
    public void add(int index, T element) {
        this.value.add(index, element);
    }

    @Override
    public boolean add(T e) {
        return this.value.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return this.value.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return this.value.addAll(index, c);
    }

    @Override
    public void clear() {
        this.value.clear();
    }

    @Override
    public boolean contains(Object o) {
        return this.value.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.value.containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return this.value.equals(o);
    }

    @Override
    public T get(int index) {
        return this.value.get(index);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public int indexOf(Object o) {
        return this.value.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this.value.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.value.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.value.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.value.listIterator(index);
    }

    @Override
    public T remove(int index) {
        return this.value.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return this.value.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.value.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.value.retainAll(c);
    }

    @Override
    public T set(int index, T element) {
        return this.value.set(index, element);
    }

    @Override
    public int size() {
        return this.value.size();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this.value.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return this.value.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return this.value.toArray(a);
    }

}
