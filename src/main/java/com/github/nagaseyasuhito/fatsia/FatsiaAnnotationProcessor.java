package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.JavaFileObject;

import com.github.nagaseyasuhito.fatsia.dao.criteria.And;
import com.github.nagaseyasuhito.fatsia.dao.criteria.Between;
import com.github.nagaseyasuhito.fatsia.dao.criteria.In;
import com.github.nagaseyasuhito.fatsia.dao.criteria.Not;
import com.github.nagaseyasuhito.fatsia.dao.criteria.Null;
import com.github.nagaseyasuhito.fatsia.dao.criteria.Or;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("javax.persistence.Entity")
public class FatsiaAnnotationProcessor extends AbstractProcessor {

    private enum Criteria {
        And(And.class) {
            @Override
            protected CharSequence buildMethods(CharSequence clazz) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(this.obtainNotMethods());
                buffer.append(this.obtainListMethods(clazz));

                return buffer;
            }

        },
        Between(Between.class) {
            @Override
            protected CharSequence buildMethods(CharSequence clazz) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(this.obtainNotMethods());
                buffer.append(this.obtainBetweenMethods(clazz));

                return buffer;
            }
        },
        In(In.class) {
            @Override
            protected CharSequence buildMethods(CharSequence clazz) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(this.obtainNotMethods());
                buffer.append(this.obtainListMethods(clazz));

                return buffer;
            }
        },
        Not(Not.class) {
            @Override
            protected CharSequence buildMethods(CharSequence clazz) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(this.obtainNotMethods());

                return buffer;
            }
        },
        Null(Null.class) {
            @Override
            protected CharSequence buildMethods(CharSequence clazz) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(this.obtainNotMethods());
                buffer.append(this.obtainNullMethods());

                return buffer;
            }
        },
        Or(Or.class) {
            @Override
            protected CharSequence buildMethods(CharSequence clazz) {
                StringBuffer buffer = new StringBuffer();

                buffer.append(this.obtainNotMethods());
                buffer.append(this.obtainListMethods(clazz));

                return buffer;
            }
        };

        @SuppressWarnings("rawtypes")
        private Class<? extends Not> clazz;

        @SuppressWarnings("rawtypes")
        private Criteria(Class<? extends Not> clazz) {
            this.clazz = clazz;
        }

        protected abstract CharSequence buildMethods(CharSequence clazz);

        public void buildSource(ProcessingEnvironment processingEnv, TypeElement clazz) throws IOException {
            CharSequence fqcn = clazz.getQualifiedName();
            CharSequence packageName = ((PackageElement) clazz.getEnclosingElement()).getQualifiedName();
            CharSequence className = clazz.getSimpleName();

            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile(fqcn + this.name());

            StringBuffer buffer = new StringBuffer();
            buffer.append("package " + packageName + ";\n\n");
            buffer.append("public class " + className + this.name() + " extends " + className + " implements " + this.clazz.getCanonicalName() + "<" + className + "> {\n");

            buffer.append(this.buildMethods(fqcn));

            buffer.append("}");

            Writer javaWriter = javaFileObject.openWriter();
            javaWriter.write(buffer.toString());
            javaWriter.close();
        }

        protected CharSequence obtainBetweenMethods(CharSequence clazz) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private " + clazz + " from;");
            buffer.append("private " + clazz + " to;");

            buffer.append("public void setFrom(" + clazz + " from) { this.from = from; }");
            buffer.append("public void setTo(" + clazz + " to) { this.to = to; }");
            buffer.append("public " + clazz + " getFrom() { return this.from; }");
            buffer.append("public " + clazz + " getTo() { return this.to; }");

            return buffer;
        }

        protected CharSequence obtainListMethods(CharSequence clazz) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private java.util.List<" + clazz + "> values = new java.util.ArrayList<" + clazz + ">();");

            buffer.append("public boolean add(" + clazz + " e) { return this.values.add(e); }");
            buffer.append("public void add(int index, " + clazz + " element) { this.values.add(index, element); }");
            buffer.append("public boolean addAll(java.util.Collection<? extends " + clazz + "> c) { return this.values.addAll(c); }");
            buffer.append("public boolean addAll(int index, java.util.Collection<? extends " + clazz + "> c) { return this.values.addAll(index, c); }");
            buffer.append("public void clear() { this.values.clear(); }");
            buffer.append("public boolean contains(Object o) { return this.values.contains(o); }");
            buffer.append("public boolean containsAll(java.util.Collection<?> c) { return this.values.containsAll(c); }");
            buffer.append("public " + clazz + " get(int index) { return this.values.get(index); }");
            buffer.append("public int indexOf(Object o) { return this.values.indexOf(o); }");
            buffer.append("public boolean isEmpty() { return this.values.isEmpty(); }");
            buffer.append("public java.util.Iterator<" + clazz + "> iterator() { return this.values.iterator(); }");
            buffer.append("public int lastIndexOf(Object o) { return this.values.lastIndexOf(o); }");
            buffer.append("public java.util.ListIterator<" + clazz + "> listIterator() { return this.values.listIterator(); }");
            buffer.append("public java.util.ListIterator<" + clazz + "> listIterator(int index) { return this.values.listIterator(index); }");
            buffer.append("public boolean remove(Object o) { return this.values.remove(o); }");
            buffer.append("public " + clazz + " remove(int index) { return this.values.remove(index); }");
            buffer.append("public boolean removeAll(java.util.Collection<?> c) { return this.values.removeAll(c); }");
            buffer.append("public boolean retainAll(java.util.Collection<?> c) { return this.values.retainAll(c); }");
            buffer.append("public " + clazz + " set(int index, " + clazz + " element) { return this.values.set(index, element); }");
            buffer.append("public int size() { return this.values.size(); }");
            buffer.append("public java.util.List<" + clazz + "> subList(int fromIndex, int toIndex) { return this.values.subList(fromIndex, toIndex); }");
            buffer.append("public Object[] toArray() { return this.values.toArray(); }");
            buffer.append("public <T> T[] toArray(T[] a) { return this.values.toArray(a); }");

            return buffer;
        }

        protected CharSequence obtainNotMethods() {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private boolean isNot;");

            buffer.append("public boolean isNot() { return this.isNot; }");
            buffer.append("public void setNot(boolean isNot) { this.isNot = isNot; }");

            return buffer;
        }

        protected CharSequence obtainNullMethods() {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private boolean isNull;");

            buffer.append("public boolean isNull() { return this.isNull; }");
            buffer.append("public void setNull(boolean isNull) { this.isNull = isNull; }");

            return buffer;
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(Entity.class);

        try {
            for (Element element : elements) {
                TypeElement clazz = (TypeElement) element;

                for (Criteria criteria : Criteria.values()) {
                    criteria.buildSource(this.processingEnv, clazz);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
