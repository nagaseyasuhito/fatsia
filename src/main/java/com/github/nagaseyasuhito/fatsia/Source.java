package com.github.nagaseyasuhito.fatsia;


public enum Source {
    Between {
        @Override
        public CharSequence buildFieldAndMethod(CharSequence clazz) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private " + clazz + " from;");
            buffer.append("private " + clazz + " to;");

            buffer.append("public void setFrom(" + clazz + " from) { this.from = from; }");
            buffer.append("public void setTo(" + clazz + " to) { this.to = to; }");
            buffer.append("public " + clazz + " getFrom() { return this.from; }");
            buffer.append("public " + clazz + " getTo() { return this.to; }");

            buffer.append(Not.buildFieldAndMethod(clazz));

            return buffer;
        }
    },
    Not {
        @Override
        public CharSequence buildFieldAndMethod(CharSequence clazz) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private boolean isNot;");

            buffer.append("public boolean isNot() { return this.isNot; }");
            buffer.append("public void setNot(boolean isNot) { this.isNot = isNot; }");

            return buffer;
        }
    },
    List {
        @Override
        public CharSequence buildFieldAndMethod(CharSequence clazz) {
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

            buffer.append(Not.buildFieldAndMethod(clazz));

            return buffer;
        }
    },
    Comparison {
        @Override
        public CharSequence buildFieldAndMethod(CharSequence clazz) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private " + clazz + "  value;");

            buffer.append("public " + clazz + "  getValue() { return this.value; }");
            buffer.append("public void setValue(" + clazz + "  value) { this.value = value; }");

            buffer.append(Not.buildFieldAndMethod(clazz));

            return buffer;
        }
    },
    Null {
        @Override
        public CharSequence buildFieldAndMethod(CharSequence clazz) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("private boolean isNull;");

            buffer.append("public boolean isNull() { return this.isNull; }");
            buffer.append("public void setNull(boolean isNull) { this.isNull = isNull; }");

            buffer.append(Not.buildFieldAndMethod(clazz));

            return buffer;
        }
    };

    public abstract CharSequence buildFieldAndMethod(CharSequence clazz);
}
