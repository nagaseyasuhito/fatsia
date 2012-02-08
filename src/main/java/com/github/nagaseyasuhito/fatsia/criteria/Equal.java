package com.github.nagaseyasuhito.fatsia.criteria;

public class Equal<T extends Comparable<T>> extends SingleValueCriteria<T> {

	public Equal() {
	}

	public Equal(T value) {
		this.setValue(value);
	}
}
