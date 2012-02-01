package com.github.nagaseyasuhito.fatsia.lang;

public class CharSequenceCriteria {
    public static class Between<T extends CharSequence & Comparable<? super T>> implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.Between<T> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class GreaterEqual<T extends CharSequence & Comparable<? super T>> implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterEqual<T> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class GreaterThan<T extends CharSequence & Comparable<? super T>> implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterThan<T> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class LesserEqual<T extends CharSequence & Comparable<? super T>> implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.LesserEqual<T> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class LesserThan<T extends CharSequence & Comparable<? super T>> implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.LesserThan<T> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class Like<T extends CharSequence & Comparable<? super T>> implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.Like<T> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class And implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.And<CharSequence> {
        private java.util.List<CharSequence> values = new java.util.ArrayList<CharSequence>();

        @Override
        public boolean add(CharSequence e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, CharSequence element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends CharSequence> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends CharSequence> c) {
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
        public CharSequence get(int index) {
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
        public java.util.Iterator<CharSequence> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<CharSequence> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<CharSequence> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public CharSequence remove(int index) {
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
        public CharSequence set(int index, CharSequence element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<CharSequence> subList(int fromIndex, int toIndex) {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class In implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.In<CharSequence> {
        private java.util.List<CharSequence> values = new java.util.ArrayList<CharSequence>();

        @Override
        public boolean add(CharSequence e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, CharSequence element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends CharSequence> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends CharSequence> c) {
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
        public CharSequence get(int index) {
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
        public java.util.Iterator<CharSequence> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<CharSequence> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<CharSequence> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public CharSequence remove(int index) {
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
        public CharSequence set(int index, CharSequence element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<CharSequence> subList(int fromIndex, int toIndex) {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class Not implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.Not<CharSequence> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class Null implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.Null<CharSequence> {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }

    public static class Or implements CharSequence, com.github.nagaseyasuhito.fatsia.criteria.operator.Or<CharSequence> {
        private java.util.List<CharSequence> values = new java.util.ArrayList<CharSequence>();

        @Override
        public boolean add(CharSequence e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, CharSequence element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends CharSequence> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends CharSequence> c) {
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
        public CharSequence get(int index) {
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
        public java.util.Iterator<CharSequence> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<CharSequence> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<CharSequence> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public CharSequence remove(int index) {
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
        public CharSequence set(int index, CharSequence element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<CharSequence> subList(int fromIndex, int toIndex) {
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
        public char charAt(int argument0) {
            throw new IllegalAccessError();
        }

        @Override
        public int length() {
            throw new IllegalAccessError();
        }

        @Override
        public CharSequence subSequence(int argument0, int argument1) {
            throw new IllegalAccessError();
        }
    }
}