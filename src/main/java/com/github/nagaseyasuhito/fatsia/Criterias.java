package com.github.nagaseyasuhito.fatsia;

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

import org.apache.commons.beanutils.BeanMap;

import com.github.nagaseyasuhito.fatsia.criteria.And;
import com.github.nagaseyasuhito.fatsia.criteria.Between;
import com.github.nagaseyasuhito.fatsia.criteria.Criteria;
import com.github.nagaseyasuhito.fatsia.criteria.EntityCriteria;
import com.github.nagaseyasuhito.fatsia.criteria.Equal;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterEqual;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterThan;
import com.github.nagaseyasuhito.fatsia.criteria.In;
import com.github.nagaseyasuhito.fatsia.criteria.LesserEqual;
import com.github.nagaseyasuhito.fatsia.criteria.LesserThan;
import com.github.nagaseyasuhito.fatsia.criteria.Like;
import com.github.nagaseyasuhito.fatsia.criteria.Null;
import com.github.nagaseyasuhito.fatsia.criteria.Or;
import com.google.common.collect.Lists;

public class Criterias {
    private static final Criterias INSTANCE = new Criterias();

    private Criterias() {
    }

    @SuppressWarnings("unchecked")
    protected List<Predicate> buildChildQuery(Criteria<?> criteria, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Path<?> path) {
        List<Predicate> predicates = Lists.newArrayList();

        for (Map.Entry<String, Object> entry : ((Map<String, Object>) new BeanMap(criteria)).entrySet()) {
            if (entry.getKey().equals("not") || entry.getKey().equals("class") || entry.getKey().equals("entityClass")) {
                continue;
            }
            Predicate predicate = this.buildExpression(criteriaBuilder, criteriaQuery, path, entry.getKey(), entry.getValue());
            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
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

        if (property instanceof LesserEqual<?>) {
            Comparable<?> value = ((LesserEqual<?>) property).getValue();
            return value == null ? null : this.processNotPredicate((LesserEqual<?>) property, criteriaBuilder, criteriaBuilder.lessThanOrEqualTo(path.<Comparable> get(propertyName), value));
        }

        if (property instanceof LesserThan<?>) {
            Comparable<?> value = ((LesserThan<?>) property).getValue();
            return value == null ? null : this.processNotPredicate((LesserThan<?>) property, criteriaBuilder, criteriaBuilder.lessThan(path.<Comparable> get(propertyName), value));
        }

        if (property instanceof Like<?>) {
            CharSequence value = ((Like<?>) property).getValue();
            return value == null ? null : this.processNotPredicate((Like<?>) property, criteriaBuilder, criteriaBuilder.like(path.<String> get(propertyName), value.toString()));
        }

        if (property instanceof Null) {
            return this.processNotPredicate((Null) property, criteriaBuilder, criteriaBuilder.isNull(path.get(propertyName)));
        }

        if (property instanceof EntityCriteria<?>) {
            List<Predicate> predicates = this.buildChildQuery((Criteria<?>) property, criteriaBuilder, criteriaQuery, ((From<?, ?>) path).join(propertyName));
            return predicates.size() == 0 ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }

        throw new IllegalAccessError("unexpected path");
    }

    protected <X> Root<X> buildQuery(Criteria<X> criteria, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<X> root) {
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

        return entityManager.createQuery(criteriaQuery.select(INSTANCE.buildQuery(criteria, criteriaBuilder, criteriaQuery, root))).getSingleResult();
    }

    public static <T> List<T> find(EntityManager entityManager, EntityCriteria<T> criteria, SortedMap<String, Boolean> orders) {
        return find(entityManager, criteria, orders, -1, -1);
    }

    public static <T> List<T> find(EntityManager entityManager, EntityCriteria<T> criteria, SortedMap<String, Boolean> orders, int firstResult, int maxResults) {
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

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery.select(INSTANCE.buildQuery(criteria, criteriaBuilder, criteriaQuery, root)).distinct(true));
        if (firstResult >= 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults >= 0) {
            query.setMaxResults(maxResults);
        }
        return query.getResultList();

    }

    public static <T> long count(EntityCriteria<T> criteria, EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(criteria.getEntityClass());

        return entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(INSTANCE.buildQuery(criteria, criteriaBuilder, criteriaQuery, root)))).getSingleResult();
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

    public static <T extends Comparable<T>> LesserEqual<T> le(T value) {
        return new LesserEqual<T>(value);
    }

    public static <T extends Comparable<T>> LesserThan<T> lt(T value) {
        return new LesserThan<T>(value);
    }
}
