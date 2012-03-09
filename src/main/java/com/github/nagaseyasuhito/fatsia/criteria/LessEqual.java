package com.github.nagaseyasuhito.fatsia.criteria;

public class LessEqual<T extends Comparable<T>> extends SingleValueCriteria<T> {
	public LessEqual() {
	}

	public LessEqual(T value) {
		this.setValue(value);
	}
}
