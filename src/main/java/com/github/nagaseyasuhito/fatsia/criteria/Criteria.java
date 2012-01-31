package com.github.nagaseyasuhito.fatsia.criteria;

public interface Criteria {
    CharSequence buildMethods(CharSequence clazz);

    Class<?> getCriteriaClass();
}