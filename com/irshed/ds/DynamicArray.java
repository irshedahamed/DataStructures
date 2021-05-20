package DataStructure.com.irshed.ds;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
    int size = 0;
    int limit = 0;
    T[] arr;

    public DynamicArray(int limit) {
        this.limit = limit;
        arr = (T[]) new Object[limit];
    }

    public void add(T element) {
        if (size == limit) {
            limit *= 2;
            arr = Arrays.copyOf(arr, limit);
        }
        arr[size++] = element;
    }

    public boolean remove(T element) {
        int removeIndex = indexOf(element);
        return removeAt(removeIndex);
    }

    private boolean removeAt(int removeIndex) {
        T newArr[] = (T[]) new Object[limit];
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (i == removeIndex && ++i == size)
                continue;
            newArr[j] = arr[i];
        }
        size--;
        arr = newArr;
        return true;
    }


    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == element)
                return i;
        }
        return -1;
    }

    public T get(int index) {
        return arr[index];
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        if (size >= 1)
            str.append(arr[0]);
        for (int i = 1; i < size; i++) {
            str.append(",").append(arr[i]);
        }
        str.append("]");
        return str.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int itr = 0;

            @Override
            public boolean hasNext() {
                return itr < size;
            }

            @Override
            public T next() {
                return get(itr++);
            }
        };
    }


}
