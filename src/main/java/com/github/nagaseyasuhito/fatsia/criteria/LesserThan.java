package com.github.nagaseyasuhito.fatsia.criteria;

public class LesserThan<T extends Comparable<T>> extends SingleValueCriteria<T> {
	public LesserThan() {
	}

	public LesserThan(T value) {
		this.setValue(value);
	}
}
