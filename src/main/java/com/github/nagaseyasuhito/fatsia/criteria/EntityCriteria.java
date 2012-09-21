package com.github.nagaseyasuhito.fatsia.criteria;

import java.util.Collection;

public abstract class EntityCriteria<T> extends Criteria<T> {
	public abstract Class<T> getEntityClass();

	public abstract Collection<String> getTargetProperties();
}
