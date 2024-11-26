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

    @Override
    public T pop() {
        final T last = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return last;
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
    public DataStore<T> intersection(DataStore<T> other) {
        final DataStoreArrays<T> intersection = new DataStoreArrays<>();

        for (T datum : data) {
            if (other.contains(datum) && !intersection.contains(datum)) {
                intersection.add(datum);
            }
        }
        return intersection;
    }

    @Override
    public DataStore<T> complement(DataStore<T> other) {
        final DataStoreArrays<T> complement = new DataStoreArrays<>();

        for (T datum : data) {
            if (!other.contains(datum) && !complement.contains(datum)) {
                complement.add(datum);
            }
        }
        return complement;
    }

    @Override
    public Iterable<T> getAll() {
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
