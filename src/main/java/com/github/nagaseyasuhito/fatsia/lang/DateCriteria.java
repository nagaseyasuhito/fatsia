package com.github.nagaseyasuhito.fatsia.lang;

public class DateCriteria {
    public static class Between extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.Between<java.sql.Date> {
        public Between() {
            super(0);
        }

        private java.sql.Date from;

        private java.sql.Date to;

        @Override
        public void setFrom(java.sql.Date from) {
            this.from = from;
        }

        @Override
        public void setTo(java.sql.Date to) {
            this.to = to;
        }

        @Override
        public java.sql.Date getFrom() {
            return this.from;
        }

        @Override
        public java.sql.Date getTo() {
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
    }

    public static class GreaterEqual extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterEqual<java.sql.Date> {
        public GreaterEqual() {
            super(0);
        }

        private java.sql.Date value;

        @Override
        public java.sql.Date getValue() {
            return this.value;
        }

        @Override
        public void setValue(java.sql.Date value) {
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
    }

    public static class GreaterThan extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.GreaterThan<java.sql.Date> {
        public GreaterThan() {
            super(0);
        }

        private java.sql.Date value;

        @Override
        public java.sql.Date getValue() {
            return this.value;
        }

        @Override
        public void setValue(java.sql.Date value) {
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
    }

    public static class LesserEqual extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.LesserEqual<java.sql.Date> {
        public LesserEqual() {
            super(0);
        }

        private java.sql.Date value;

        @Override
        public java.sql.Date getValue() {
            return this.value;
        }

        @Override
        public void setValue(java.sql.Date value) {
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
    }

    public static class LesserThan extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.LesserThan<java.sql.Date> {
        public LesserThan() {
            super(0);
        }

        private java.sql.Date value;

        @Override
        public java.sql.Date getValue() {
            return this.value;
        }

        @Override
        public void setValue(java.sql.Date value) {
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
    }

    public static class And extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.And<java.sql.Date> {
        public And() {
            super(0);
        }

        private java.util.List<java.sql.Date> values = new java.util.ArrayList<java.sql.Date>();

        @Override
        public boolean add(java.sql.Date e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, java.sql.Date element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends java.sql.Date> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends java.sql.Date> c) {
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
        public java.sql.Date get(int index) {
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
        public java.util.Iterator<java.sql.Date> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<java.sql.Date> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<java.sql.Date> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public java.sql.Date remove(int index) {
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
        public java.sql.Date set(int index, java.sql.Date element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<java.sql.Date> subList(int fromIndex, int toIndex) {
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
    }

    public static class In extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.In<java.sql.Date> {
        public In() {
            super(0);
        }

        private java.util.List<java.sql.Date> values = new java.util.ArrayList<java.sql.Date>();

        @Override
        public boolean add(java.sql.Date e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, java.sql.Date element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends java.sql.Date> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends java.sql.Date> c) {
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
        public java.sql.Date get(int index) {
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
        public java.util.Iterator<java.sql.Date> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<java.sql.Date> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<java.sql.Date> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public java.sql.Date remove(int index) {
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
        public java.sql.Date set(int index, java.sql.Date element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<java.sql.Date> subList(int fromIndex, int toIndex) {
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
    }

    public static class Not extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.Not<java.sql.Date> {
        public Not() {
            super(0);
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
    }

    public static class Null extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.Null<java.sql.Date> {
        public Null() {
            super(0);
        }

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
    }

    public static class Or extends java.sql.Date implements com.github.nagaseyasuhito.fatsia.criteria.operator.Or<java.sql.Date> {
        public Or() {
            super(0);
        }

        private java.util.List<java.sql.Date> values = new java.util.ArrayList<java.sql.Date>();

        @Override
        public boolean add(java.sql.Date e) {
            return this.values.add(e);
        }

        @Override
        public void add(int index, java.sql.Date element) {
            this.values.add(index, element);
        }

        @Override
        public boolean addAll(java.util.Collection<? extends java.sql.Date> c) {
            return this.values.addAll(c);
        }

        @Override
        public boolean addAll(int index, java.util.Collection<? extends java.sql.Date> c) {
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
        public java.sql.Date get(int index) {
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
        public java.util.Iterator<java.sql.Date> iterator() {
            return this.values.iterator();
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.values.lastIndexOf(o);
        }

        @Override
        public java.util.ListIterator<java.sql.Date> listIterator() {
            return this.values.listIterator();
        }

        @Override
        public java.util.ListIterator<java.sql.Date> listIterator(int index) {
            return this.values.listIterator(index);
        }

        @Override
        public boolean remove(Object o) {
            return this.values.remove(o);
        }

        @Override
        public java.sql.Date remove(int index) {
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
        public java.sql.Date set(int index, java.sql.Date element) {
            return this.values.set(index, element);
        }

        @Override
        public int size() {
            return this.values.size();
        }

        @Override
        public java.util.List<java.sql.Date> subList(int fromIndex, int toIndex) {
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
    }
}