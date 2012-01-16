package com.github.nagaseyasuhito.fatsia.dao;

import java.util.List;
import java.util.SortedMap;

import com.github.nagaseyasuhito.fatsia.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity<?>> {
	long countByCriteria(T criteria);

	T findByCriteria(T criteria);

	public List<T> findByCriteria(T criteria, SortedMap<String, Boolean> orders);

	public List<T> findByCriteria(T criteria, SortedMap<String, Boolean> orders, int firstResult, int maxResults);

	T findById(Long id);

	T merge(T entity);

	void persist(T entity);

	T persistOrMerge(T entity);

	void remove(T entity);
}
