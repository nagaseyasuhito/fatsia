package com.github.nagaseyasuhito.fatsia.criteria;

public abstract class EntityCriteria<T> extends Criteria<T> {
	public abstract Class<T> getEntityClass();
}
