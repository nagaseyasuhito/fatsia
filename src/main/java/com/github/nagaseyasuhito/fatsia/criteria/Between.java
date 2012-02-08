package com.github.nagaseyasuhito.fatsia.criteria;

public class Between<T extends Comparable<T>> extends Criteria<T> {

	private T from;

	private T to;

	public Between() {
	}

	public Between(T from, T to) {
		this.from = from;
		this.to = to;
	}

	public T getFrom() {
		return this.from;
	}

	public void setFrom(T from) {
		this.from = from;
	}

	public T getTo() {
		return this.to;
	}

	public void setTo(T to) {
		this.to = to;
	}
}
