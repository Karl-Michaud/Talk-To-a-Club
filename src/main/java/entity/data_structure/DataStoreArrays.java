package entity.data_structure;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of DataStore using arrays.
 * @param <T> item for data structure.
 */
public class DataStoreArrays<T> implements DataStore<T>, Iterable<T> {
    private final ArrayList<T> data;

    public DataStoreArrays() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        data.add(element);
    }

    @Override
    public T remove(T element) {
        data.remove(element);
        return element;
    }

    @Override
    public T get(T element) {
        T temp = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(element)) {
                temp = data.get(i);
            }
        }
        return temp;
    }

    /**
     * Returns element by index.
     * @param index integer value of index.
     * @return value at index.
     */
    public T getByIndex(int index) {
        return data.get(index);
    }

    /**
     * Removes and returns the element the last element.
     * @param element element to pop.
     * @return element that has been popped.
     */
    public T pop(T element) {
        T temp = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(element)) {
                temp = data.get(i);
                data.remove(i);
            }
        }
        return temp;
    }

    @Override
    public Boolean contains(T element) {
        return data.contains(element);
    }

    @Override
    public Integer size() {
        return this.data.size();
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
