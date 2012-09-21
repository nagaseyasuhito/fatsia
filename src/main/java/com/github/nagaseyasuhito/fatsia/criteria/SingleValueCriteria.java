package com.github.nagaseyasuhito.fatsia.criteria;

abstract class SingleValueCriteria<T extends Comparable<T>> extends Criteria<T> {

	private T value;

	public T getValue() {
		return this.value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
