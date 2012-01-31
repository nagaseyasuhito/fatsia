package com.github.nagaseyasuhito.fatsia.criteria;

import com.github.nagaseyasuhito.fatsia.Source;
import com.github.nagaseyasuhito.fatsia.dao.operator.And;
import com.github.nagaseyasuhito.fatsia.dao.operator.In;
import com.github.nagaseyasuhito.fatsia.dao.operator.Not;
import com.github.nagaseyasuhito.fatsia.dao.operator.Null;
import com.github.nagaseyasuhito.fatsia.dao.operator.Or;

public enum ConditionalCriteria implements Criteria {
    And(And.class, Source.List), In(In.class, Source.List), Not(Not.class, Source.Not), Null(Null.class, Source.Null), Or(Or.class, Source.List);

    @SuppressWarnings("rawtypes")
    private Class<? extends Not> clazz;

    private Source[] sources;

    @SuppressWarnings("rawtypes")
    private ConditionalCriteria(Class<? extends Not> clazz, Source... sources) {
        this.clazz = clazz;
        this.sources = sources;
    }

    @Override
    public CharSequence buildMethods(CharSequence clazz) {
        StringBuffer buffer = new StringBuffer();

        for (Source source : this.sources) {
            buffer.append(source.buildFieldAndMethod(clazz));
        }

        return buffer;
    }

    @Override
    public Class<?> getCriteriaClass() {
        return this.clazz;
    }
}