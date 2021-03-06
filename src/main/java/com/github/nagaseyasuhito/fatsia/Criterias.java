package com.github.nagaseyasuhito.fatsia;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.github.nagaseyasuhito.fatsia.criteria.And;
import com.github.nagaseyasuhito.fatsia.criteria.Between;
import com.github.nagaseyasuhito.fatsia.criteria.Criteria;
import com.github.nagaseyasuhito.fatsia.criteria.EntityCriteria;
import com.github.nagaseyasuhito.fatsia.criteria.Equal;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterEqual;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterThan;
import com.github.nagaseyasuhito.fatsia.criteria.In;
import com.github.nagaseyasuhito.fatsia.criteria.LessEqual;
import com.github.nagaseyasuhito.fatsia.criteria.LessThan;
import com.github.nagaseyasuhito.fatsia.criteria.Like;
import com.github.nagaseyasuhito.fatsia.criteria.Null;
import com.github.nagaseyasuhito.fatsia.criteria.Or;
import com.google.common.collect.Lists;

public class Criterias {
	private static final Criterias INSTANCE = new Criterias();

	private Criterias() {
	}

	protected List<Predicate> buildChildQuery(EntityCriteria<?> criteria, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Path<?> path) {
		List<Predicate> predicates = Lists.newArrayList();

		for (String property : criteria.getTargetProperties()) {
			PropertyDescriptor propertyDescriptor;
			try {
				propertyDescriptor = new PropertyDescriptor(property, criteria.getClass());
			} catch (IntrospectionException e) {
				throw new RuntimeException(e);
			}

			Method readMethod = propertyDescriptor.getReadMethod();

			Predicate predicate;
			try {
				predicate = this.buildExpression(criteriaBuilder, criteriaQuery, path, property, readMethod.invoke(criteria, new Object[0]));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			if (predicate != null) {
				predicates.add(predicate);
			}
		}

		return predicates;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Predicate buildExpression(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Path<?> path, String propertyName, Object property) {
		if (property == null) {
			return null;
		}

		if (property instanceof And<?>) {
			List<Predicate> predicates = Lists.newArrayList();
			for (Object object : (And<?>) property) {
				Predicate predicate = this.buildExpression(criteriaBuilder, criteriaQuery, path, propertyName, object);
				if (predicate != null) {
					predicates.add(predicate);
				}
			}
			return predicates.size() == 0 ? null : this.processNotPredicate((And<?>) property, criteriaBuilder, criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		}

		if (property instanceof In<?>) {
			return this.processNotPredicate((In<?>) property, criteriaBuilder, path.get(propertyName).in((In<?>) property));
		}

		if (property instanceof Or<?>) {
			List<Predicate> predicates = Lists.newArrayList();
			for (Object object : (Or<?>) property) {
				Predicate predicate = this.buildExpression(criteriaBuilder, criteriaQuery, path, propertyName, object);
				if (predicate != null) {
					predicates.add(predicate);
				}
			}
			return predicates.size() == 0 ? null : this.processNotPredicate((Or<?>) property, criteriaBuilder, criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		}

		if (property instanceof Between<?>) {
			Comparable<?> from = ((Between<?>) property).getFrom();
			Comparable<?> to = ((Between<?>) property).getTo();
			return from == null || to == null ? null : this.processNotPredicate((Between<?>) property, criteriaBuilder, criteriaBuilder.between(path.<Comparable> get(propertyName), from, to));
		}

		if (property instanceof Equal<?>) {
			Comparable<?> value = ((Equal<?>) property).getValue();
			return value == null ? null : this.processNotPredicate((Equal<?>) property, criteriaBuilder, criteriaBuilder.equal(path.<Comparable> get(propertyName), value));
		}

		if (property instanceof GreaterEqual<?>) {
			Comparable<?> value = ((GreaterEqual<?>) property).getValue();
			return value == null ? null : this.processNotPredicate((GreaterEqual<?>) property, criteriaBuilder, criteriaBuilder.greaterThanOrEqualTo(path.<Comparable> get(propertyName), value));
		}

		if (property instanceof GreaterThan<?>) {
			Comparable<?> value = ((GreaterThan<?>) property).getValue();
			return value == null ? null : this.processNotPredicate((GreaterThan<?>) property, criteriaBuilder, criteriaBuilder.greaterThan(path.<Comparable> get(propertyName), value));
		}

		if (property instanceof LessEqual<?>) {
			Comparable<?> value = ((LessEqual<?>) property).getValue();
			return value == null ? null : this.processNotPredicate((LessEqual<?>) property, criteriaBuilder, criteriaBuilder.lessThanOrEqualTo(path.<Comparable> get(propertyName), value));
		}

		if (property instanceof LessThan<?>) {
			Comparable<?> value = ((LessThan<?>) property).getValue();
			return value == null ? null : this.processNotPredicate((LessThan<?>) property, criteriaBuilder, criteriaBuilder.lessThan(path.<Comparable> get(propertyName), value));
		}

		if (property instanceof Like<?>) {
			CharSequence value = ((Like<?>) property).getValue();
			return value == null ? null : this.processNotPredicate((Like<?>) property, criteriaBuilder, criteriaBuilder.like(path.<String> get(propertyName), value.toString()));
		}

		if (property instanceof Null) {
			return this.processNotPredicate((Null) property, criteriaBuilder, criteriaBuilder.isNull(path.get(propertyName)));
		}

		if (property instanceof EntityCriteria<?>) {
			List<Predicate> predicates = this.buildChildQuery((EntityCriteria<?>) property, criteriaBuilder, criteriaQuery, ((From<?, ?>) path).join(propertyName));
			return predicates.size() == 0 ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		}

		throw new IllegalAccessError("unexpected path");
	}

	protected <X> Root<X> buildQuery(EntityCriteria<X> criteria, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<X> root) {
		criteriaQuery.where(this.buildChildQuery(criteria, criteriaBuilder, criteriaQuery, root).toArray(new Predicate[0]));

		return root;
	}

	protected Predicate processNotPredicate(Criteria<?> property, CriteriaBuilder criteriaBuilder, Predicate predicate) {
		if (property.isNot()) {
			return criteriaBuilder.not(predicate);
		} else {
			return predicate;
		}
	}

	public static <T> T find(EntityManager entityManager, EntityCriteria<T> criteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(criteria.getEntityClass());
		Root<T> root = criteriaQuery.from(criteria.getEntityClass());

		return entityManager.createQuery(criteriaQuery.select(Criterias.INSTANCE.buildQuery(criteria, criteriaBuilder, criteriaQuery, root))).getSingleResult();
	}

	public static <T> List<T> find(EntityManager entityManager, EntityCriteria<T> criteria, SortedMap<String, Boolean> orders) {
		return Criterias.createTypedQuery(entityManager, criteria, orders).getResultList();
	}

	public static <T> List<T> find(EntityManager entityManager, EntityCriteria<T> criteria, SortedMap<String, Boolean> orders, int firstResult, int maxResults) {
		TypedQuery<T> query = Criterias.createTypedQuery(entityManager, criteria, orders);
		if (firstResult >= 0) {
			query.setFirstResult(firstResult);
		}
		if (maxResults >= 0) {
			query.setMaxResults(maxResults);
		}
		return query.getResultList();
	}

	private static <T> TypedQuery<T> createTypedQuery(EntityManager entityManager, EntityCriteria<T> criteria, SortedMap<String, Boolean> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(criteria.getEntityClass());
		Root<T> root = criteriaQuery.from(criteria.getEntityClass());

		for (Map.Entry<String, Boolean> entry : orders.entrySet()) {
			if (entry.getValue()) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(entry.getKey())));
			} else {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(entry.getKey())));
			}
		}

		return entityManager.createQuery(criteriaQuery.select(Criterias.INSTANCE.buildQuery(criteria, criteriaBuilder, criteriaQuery, root)).distinct(true));
	}

	public static <T> long count(EntityManager entityManager, EntityCriteria<T> criteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(criteria.getEntityClass());

		return entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(Criterias.INSTANCE.buildQuery(criteria, criteriaBuilder, criteriaQuery, root)))).getSingleResult();
	}

	public static <T> And<T> and() {
		return new And<T>();
	}

	public static <T> In<T> in() {
		return new In<T>();
	}

	public static <T> Or<T> or() {
		return new Or<T>();
	}

	public static <T> Null<T> nullable() {
		return new Null<T>();
	}

	public static <T extends CharSequence & Comparable<T>> Like<T> like(T value) {
		return new Like<T>(value);
	}

	public static <T extends Comparable<T>> Between<T> between(T from, T to) {
		return new Between<T>(from, to);
	}

	public static <T extends Comparable<T>> Equal<T> eq(T value) {
		return new Equal<T>(value);
	}

	public static <T extends Comparable<T>> GreaterEqual<T> ge(T value) {
		return new GreaterEqual<T>(value);
	}

	public static <T extends Comparable<T>> GreaterThan<T> gt(T value) {
		return new GreaterThan<T>(value);
	}

	public static <T extends Comparable<T>> LessEqual<T> le(T value) {
		return new LessEqual<T>(value);
	}

	public static <T extends Comparable<T>> LessThan<T> lt(T value) {
		return new LessThan<T>(value);
	}
}
