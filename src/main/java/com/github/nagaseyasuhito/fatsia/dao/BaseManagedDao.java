package com.github.nagaseyasuhito.fatsia.dao;

import com.github.nagaseyasuhito.fatsia.entity.BaseManagedEntity;

public abstract class BaseManagedDao<T extends BaseManagedEntity> extends BaseDao<Long, T> {
}
