package DataStructure.com.irshed.ds;

/**
 * Methods and Comments are added from William fisset Impl of Binary Heap
 */


import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap = null;

    public BinaryHeap() {
        this(1);
    }

    public BinaryHeap(int size) {
        heap = new ArrayList<>(size);
    }


    public BinaryHeap(T[] elems) {
        heap = new ArrayList<>(elems.length);
        for (int i = 0; i < elems.length; i++) {
            heap.add(elems[i]);
        }
        //heapify
        for (int i = Math.max(0, (heap.size() / 2) - 1); i >= 0; i--)
            sink(i);
    }


    public void add(T elem) {
        if (heap == null)
            heap = new ArrayList<>();
        heap.add(elem);
        swim(heap.size() - 1);
    }

    private void swim(int k) {
        int parentIndex = (k - 1) / 2;
        while (k > 0 && less(k, parentIndex)) {
            swap(k, parentIndex);
            k = parentIndex;
            parentIndex = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = k;
            if (left < heap.size() && less(left, smallest))
                smallest = left;
            if (right < heap.size() && less(right, smallest))
                smallest = right;

            if (smallest != k) {
                swap(k, smallest);
                sink(smallest);
            }else{
                break;
            }
        }
    }

    public boolean remove(T element) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(element)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElem);

       heap.remove(indexOfLastElem);

        if (i == indexOfLastElem) return removed_data;

        int parent = (i - 1) / 2;

        if(parent>0 && less(i,parent)){
            swim(i);
            return removed_data;
        }
        sink(i);
       
        return removed_data;
    }

    public boolean isMinHeap(int k) {
        if (k >= heap.size()) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        if (left < heap.size() && !less(k, left)) return false;
        if (right < heap.size() && !less(k, right)) return false;

        return isMinHeap(left) && isMinHeap(right);

    }

    private boolean less(int i, int j) {
        T first = heap.get(i);
        T second = heap.get(j);
        return first.compareTo(second) <= 0;
    }

    public boolean contains(T elem) {
        for (int i = 0; i < heap.size(); i++) {
            T temp = heap.get(i);
            if (temp.equals(elem))
                return true;
        }
        return false;
    }


    private void swap(int i, int j) {
        T first = heap.get(i);
        T second = heap.get(j);
        T temp = first;
        heap.set(i, second);
        heap.set(j, temp);
    }

    public T peek() {
        if (isEmpty())
            return null;
        return heap.get(0);
    }

    public T poll() {
        if (isEmpty())
            return null;
        return removeAt(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public int size() {
        return heap.size();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
