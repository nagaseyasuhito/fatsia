package com.github.nagaseyasuhito.fatsia.criteria;

public class GreaterThan<T extends Comparable<T>> extends SingleValueCriteria<T> {
	public GreaterThan() {
	}

	public GreaterThan(T value) {
		this.setValue(value);
	}
}
