package com.github.nagaseyasuhito.fatsia.criteria;

public class LesserEqual<T extends Comparable<T>> extends SingleValueCriteria<T> {
	public LesserEqual() {
	}

	public LesserEqual(T value) {
		this.setValue(value);
	}
}
