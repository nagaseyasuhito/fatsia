package com.github.nagaseyasuhito.fatsia.criteria;

public class Criteria<T> {
	private boolean not;

	public boolean isNot() {
		return this.not;
	}

	public void setNot(boolean not) {
		this.not = not;
	}

	public Criteria<T> not(boolean not) {
		this.setNot(not);
		return this;
	}
}