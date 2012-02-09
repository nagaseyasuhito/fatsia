package com.github.nagaseyasuhito.fatsia.criteria;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.Lists;

public class In<T> extends Criteria<T> implements List<T> {

	private List<T> value = Lists.newArrayList();

	@Override
	public void add(int arg0, T arg1) {
		this.value.add(arg0, arg1);
	}

	@Override
	public boolean add(T arg0) {
		return this.value.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		return this.value.addAll(arg0);
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		return this.value.addAll(arg0, arg1);
	}

	@Override
	public void clear() {
		this.value.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.value.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.value.containsAll(arg0);
	}

	@Override
	public boolean equals(Object arg0) {
		return this.value.equals(arg0);
	}

	@Override
	public T get(int arg0) {
		return this.value.get(arg0);
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public int indexOf(Object arg0) {
		return this.value.indexOf(arg0);
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
	public int lastIndexOf(Object arg0) {
		return this.value.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<T> listIterator() {
		return this.value.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		return this.value.listIterator(arg0);
	}

	@Override
	public T remove(int arg0) {
		return this.value.remove(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		return this.value.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.value.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.value.retainAll(arg0);
	}

	@Override
	public T set(int arg0, T arg1) {
		return this.value.set(arg0, arg1);
	}

	@Override
	public int size() {
		return this.value.size();
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		return this.value.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return this.value.toArray();
	}

	@Override
	public <E> E[] toArray(E[] arg0) {
		return this.value.toArray(arg0);
	}

}
