package com.toma.pubgmc.util;

public class DynamicArray<E> {

    private E[] array;

    public DynamicArray(int size) {
        this.array = (E[]) new Object[size];
    }

    public DynamicArray(E[] array) {
        this.array = array;
    }

    public void add(E e) {
        this.add(e, 0);
    }

    public void add(E e, int index) {
        this.insertElementAt(e, index);
    }

    public void addLast(E e) {
        this.moveDown(e);
    }

    public E get(int index) {
        return this.array[index];
    }

    public int size() {
        return this.array.length;
    }

    public void clear() {
        int size = this.array.length;
        this.array = (E[]) new Object[size];
    }

    public int getFirstNull() {
        return -1;
    }

    public int getFirstNonnull() {
        return -1;
    }

    private void insertElementAt(E e, int idx) {
        for(int i = this.size() - 2; i >= idx; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[idx] = e;
    }

    private void moveDown(E e) {
        for(int i = 1; i < this.size(); i++) {
            this.array[i - 1] = this.array[i];
        }
        this.array[this.size()-1] = e;
    }
}
