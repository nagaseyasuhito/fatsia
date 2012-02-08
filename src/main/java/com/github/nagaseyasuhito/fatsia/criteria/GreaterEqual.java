package com.github.nagaseyasuhito.fatsia.criteria;

public class GreaterEqual<T extends Comparable<T>> extends SingleValueCriteria<T> {
	public GreaterEqual() {
	}

	public GreaterEqual(T value) {
		this.setValue(value);
	}
}
