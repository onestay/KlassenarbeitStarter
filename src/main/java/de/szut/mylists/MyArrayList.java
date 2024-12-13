package de.szut.mylists;

public class MyArrayList {
    private int[] storage = new int[10];
    private int elements = 0;
    private int capacity = 10;

    public int size() {
        return elements;
    }

    private void growStorage() {
        var oldCapacity = capacity;
        capacity += 10;
        var newList = new int[capacity];
        System.arraycopy(storage, 0, newList, 0, oldCapacity);
        storage = newList;
    }

    public void add(int value) {
        if (elements >= capacity) {
            growStorage();
        }

        storage[elements] = value;
        elements += 1;
    }

    public int get(int index) {
        boundsCheck(index);
        return storage[index];
    }

    public void remove(int index) {
        boundsCheck(index);
        // copy all elements starting at index + 1 to index
        // basically shifting everything bigger than index one to the left

        // elements remaining after the index
        // len = 5, index = 3
        // remaining = 5 - (3 + 1) = 1
        // meaning after index 3 there is one more element in the array we have to copy
        int remaining = storage.length - (index + 1);

        System.arraycopy(storage, index + 1, storage, index, remaining);
        elements -= 1;
    }

    private void boundsCheck(int index) {
        if (index > elements) {
            throw new IndexOutOfBoundsException("Dieser Index existiert nicht!");
        }
    }

    public boolean contains(int number) {
        for (int i = 0; i < elements; i++) {
            if (storage[i] == number) {
                return true;
            }
        }

        return false;
    }
}
