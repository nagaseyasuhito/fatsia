package com.github.nagaseyasuhito.fatsia.criteria;

public class Like<T extends CharSequence & Comparable<T>> extends SingleValueCriteria<T> {
	public Like() {
	}

	public Like(T value) {
		this.setValue(value);
	}
}
