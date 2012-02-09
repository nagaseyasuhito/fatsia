package com.github.nagaseyasuhito.fatsia;

import com.github.nagaseyasuhito.fatsia.criteria.And;
import com.github.nagaseyasuhito.fatsia.criteria.Between;
import com.github.nagaseyasuhito.fatsia.criteria.Equal;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterEqual;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterThan;
import com.github.nagaseyasuhito.fatsia.criteria.In;
import com.github.nagaseyasuhito.fatsia.criteria.LesserEqual;
import com.github.nagaseyasuhito.fatsia.criteria.LesserThan;
import com.github.nagaseyasuhito.fatsia.criteria.Like;
import com.github.nagaseyasuhito.fatsia.criteria.Null;
import com.github.nagaseyasuhito.fatsia.criteria.Or;
import com.github.nagaseyasuhito.fatsia.entity.BaseEntity;

public class Criterias {

    public static <T> And<T> and() {
        return new And<T>();
    }

    public static <T extends BaseEntity<?>> In<T> in() {
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
