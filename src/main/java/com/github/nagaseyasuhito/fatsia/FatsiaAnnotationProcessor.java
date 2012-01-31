package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.JavaFileObject;

import com.github.nagaseyasuhito.fatsia.criteria.ConditionalCriteria;
import com.github.nagaseyasuhito.fatsia.criteria.Criteria;
import com.github.nagaseyasuhito.fatsia.criteria.OperationalCriteria;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("javax.persistence.Entity")
public class FatsiaAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(Entity.class);

        try {
            for (Class<?> clazz : Collections2.transform(elements, new Function<Element, Class<?>>() {
                @Override
                public Class<?> apply(Element input) {
                    try {
                        return Class.forName(((TypeElement) input).getQualifiedName().toString());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            })) {
                CharSequence packageName = clazz.getPackage().getName();
                CharSequence className = clazz.getSimpleName();

                this.buildClass(clazz, packageName, className, ConditionalCriteria.values());
            }

            for (Class<?> clazz : new Class[] {CharSequence.class, Number.class, Date.class, Time.class, Timestamp.class }) {
                CharSequence packageName = this.getClass().getPackage().getName() + ".lang";
                CharSequence className = clazz.getSimpleName();

                this.buildClass(clazz, packageName, className, OperationalCriteria.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void buildClass(Class<?> clazz, CharSequence packageName, CharSequence className, Criteria[] criterias) throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("package " + packageName + ";");
        buffer.append("public class " + className + "Criteria {");
        for (Criteria criteria : criterias) {
            buffer.append(this.buildSource(criteria, clazz));
        }
        buffer.append("}");

        JavaFileObject javaFileObject = this.processingEnv.getFiler().createSourceFile(packageName + "." + className + "Criteria");
        Writer javaWriter = javaFileObject.openWriter();
        javaWriter.write(buffer.toString());
        javaWriter.close();
    }

    public CharSequence buildSource(Criteria criteria, Class<?> clazz) throws IOException {
        CharSequence fqcn = clazz.getCanonicalName();

        StringBuffer buffer = new StringBuffer();
        buffer.append("public static class " + criteria.getCriteriaClass().getSimpleName());
        if (clazz.isInterface()) {
            buffer.append(" implements " + fqcn);
            buffer.append(", " + criteria.getCriteriaClass().getCanonicalName());
        } else {
            buffer.append(" extends " + fqcn);
            buffer.append(" implements " + criteria.getCriteriaClass().getCanonicalName());
        }
        buffer.append("<" + fqcn + ">");
        buffer.append("{");
        buffer.append(criteria.buildMethods(fqcn));
        buffer.append("}");

        return buffer;
    }
}
