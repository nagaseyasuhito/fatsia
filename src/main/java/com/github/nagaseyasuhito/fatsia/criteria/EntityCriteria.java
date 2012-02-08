package com.github.nagaseyasuhito.fatsia.criteria;

import com.github.nagaseyasuhito.fatsia.entity.BaseEntity;

public abstract class EntityCriteria<T extends BaseEntity<?>> extends Criteria<T> {

	public abstract <E> E getId();
}
