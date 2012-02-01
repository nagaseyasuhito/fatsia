package com.github.nagaseyasuhito.fatsia.lang;

public class NumberCriteria {
    public static class Between<T extends Number & Comparable<? super T>> extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.Between<T> {
        private T from;

        private T to;

        @Override
        public void setFrom(T from) {
            this.from = from;
        }

        @Override
        public void setTo(T to) {
            this.to = to;
        }

        @Override
        public T getFrom() {
            return this.from;
        }

        @Override
        public T getTo() {
            return this.to;
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class GreaterEqual<T extends Number & Comparable<? super T>> extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterEqual<T> {
        private T value;

        @Override
        public T getValue() {
            return this.value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class GreaterThan<T extends Number & Comparable<? super T>> extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterThan<T> {
        private T value;

        @Override
        public T getValue() {
            return this.value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class LesserEqual<T extends Number & Comparable<? super T>> extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.LesserEqual<T> {
        private T value;

        @Override
        public T getValue() {
            return this.value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class LesserThan<T extends Number & Comparable<? super T>> extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.LesserThan<T> {
        private T value;

        @Override
        public T getValue() {
            return this.value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class And extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.And<Number> {
        private java.util.List<Number> values = new java.util.ArrayList<Number>();

        @Override
        public boolean add(Number e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, Number element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends Number> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends Number> c) {
            return this.values.addAll(index, c);
        }

        @Override
        public void clear() {
            this.values.clear();
        }

        @Override
        public boolean contains(Object o) {
            return this.values.contains(o);
        }

        @Override
        public boolean containsAll(java.util.Collection<?> c) {
            return this.values.containsAll(c);
        }

        @Override
        public Number get(int index) {
            return this.values.get(index);
        }

        @Override
        public int indexOf(Object o) {
            return this.values.indexOf(o);
        }

        @Override
        public boolean isEmpty() {
            return this.values.isEmpty();
        }

        @Override
        public java.util.Iterator<Number> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<Number> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<Number> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public Number remove(int index) {
            return this.values.remove(index);
        }

        @Override
        public boolean removeAll(java.util.Collection<?> c) {
            return this.values.removeAll(c);
        }

        @Override
        public boolean retainAll(java.util.Collection<?> c) {
            return this.values.retainAll(c);
        }

        @Override
        public Number set(int index, Number element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<Number> subList(int fromIndex, int toIndex) {
            return this.values.subList(fromIndex, toIndex);
        }

        @Override
        public Object[] toArray() {
            return this.values.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return this.values.toArray(a);
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class In extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.In<Number> {
        private java.util.List<Number> values = new java.util.ArrayList<Number>();

        @Override
        public boolean add(Number e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, Number element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends Number> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends Number> c) {
            return this.values.addAll(index, c);
        }

        @Override
        public void clear() {
            this.values.clear();
        }

        @Override
        public boolean contains(Object o) {
            return this.values.contains(o);
        }

        @Override
        public boolean containsAll(java.util.Collection<?> c) {
            return this.values.containsAll(c);
        }

        @Override
        public Number get(int index) {
            return this.values.get(index);
        }

        @Override
        public int indexOf(Object o) {
            return this.values.indexOf(o);
        }

        @Override
        public boolean isEmpty() {
            return this.values.isEmpty();
        }

        @Override
        public java.util.Iterator<Number> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<Number> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<Number> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public Number remove(int index) {
            return this.values.remove(index);
        }

        @Override
        public boolean removeAll(java.util.Collection<?> c) {
            return this.values.removeAll(c);
        }

        @Override
        public boolean retainAll(java.util.Collection<?> c) {
            return this.values.retainAll(c);
        }

        @Override
        public Number set(int index, Number element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<Number> subList(int fromIndex, int toIndex) {
            return this.values.subList(fromIndex, toIndex);
        }

        @Override
        public Object[] toArray() {
            return this.values.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return this.values.toArray(a);
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class Not extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.Not<Number> {
        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class Null extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.Null<Number> {
        private boolean isNull;

        @Override
        public boolean isNull() {
            return this.isNull;
        }

        @Override
        public void setNull(boolean isNull) {
            this.isNull = isNull;
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }

    public static class Or extends Number implements com.github.nagaseyasuhito.fatsia.criteria.operator.Or<Number> {
        private java.util.List<Number> values = new java.util.ArrayList<Number>();

        @Override
        public boolean add(Number e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, Number element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends Number> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends Number> c) {
            return this.values.addAll(index, c);
        }

        @Override
        public void clear() {
            this.values.clear();
        }

        @Override
        public boolean contains(Object o) {
            return this.values.contains(o);
        }

        @Override
        public boolean containsAll(java.util.Collection<?> c) {
            return this.values.containsAll(c);
        }

        @Override
        public Number get(int index) {
            return this.values.get(index);
        }

        @Override
        public int indexOf(Object o) {
            return this.values.indexOf(o);
        }

        @Override
        public boolean isEmpty() {
            return this.values.isEmpty();
        }

        @Override
        public java.util.Iterator<Number> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<Number> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<Number> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public Number remove(int index) {
            return this.values.remove(index);
        }

        @Override
        public boolean removeAll(java.util.Collection<?> c) {
            return this.values.removeAll(c);
        }

        @Override
        public boolean retainAll(java.util.Collection<?> c) {
            return this.values.retainAll(c);
        }

        @Override
        public Number set(int index, Number element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<Number> subList(int fromIndex, int toIndex) {
            return this.values.subList(fromIndex, toIndex);
        }

        @Override
        public Object[] toArray() {
            return this.values.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return this.values.toArray(a);
        }

        private boolean isNot;

        @Override
        public boolean isNot() {
            return this.isNot;
        }

        @Override
        public void setNot(boolean isNot) {
            this.isNot = isNot;
        }

        @Override
        public double doubleValue() {
            throw new IllegalAccessError();
        }

        @Override
        public float floatValue() {
            throw new IllegalAccessError();
        }

        @Override
        public int intValue() {
            throw new IllegalAccessError();
        }

        @Override
        public long longValue() {
            throw new IllegalAccessError();
        }
    }
}