package com.github.nagaseyasuhito.fatsia.criteria;

public class LessThan<T extends Comparable<T>> extends SingleValueCriteria<T> {
	public LessThan() {
	}

	public LessThan(T value) {
		this.setValue(value);
	}
}
