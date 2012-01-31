package com.github.nagaseyasuhito.fatsia.criteria;

import com.github.nagaseyasuhito.fatsia.Source;
import com.github.nagaseyasuhito.fatsia.criteria.operator.Between;
import com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterEqual;
import com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterThan;
import com.github.nagaseyasuhito.fatsia.criteria.operator.LesserEqual;
import com.github.nagaseyasuhito.fatsia.criteria.operator.LesserThan;
import com.github.nagaseyasuhito.fatsia.criteria.operator.Like;
import com.github.nagaseyasuhito.fatsia.criteria.operator.Not;

public enum OperationalCriteria implements Criteria {
    Between(Between.class, Source.Between), GreaterEqual(GreaterEqual.class, Source.Comparison), GreaterThan(GreaterThan.class, Source.Comparison), LesserEqual(LesserEqual.class,
            Source.Comparison), LesserThan(LesserThan.class, Source.Comparison), Like(Like.class, Source.Comparison);

    @SuppressWarnings("rawtypes")
    private Class<? extends Not> clazz;

    private Source[] sources;

    @SuppressWarnings("rawtypes")
    private OperationalCriteria(Class<? extends Not> clazz, Source... sources) {
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