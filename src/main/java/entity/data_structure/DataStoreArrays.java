package entity.data_structure;

import java.util.ArrayList;

/**
 * Implementation of DataStore using arrays.
 * @param <T> item for data structure.
 */
public class DataStoreArrays<T> implements DataStore<T> {
    private final ArrayList<T> data;

    public DataStoreArrays() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        data.add(element);
    }

    @Override
    public void remove(T element) {
        data.remove(element);
    }

    @Override
    public T get(T element) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(element)) {
                return data.get(i);
            }
        }
        return null;
    }

    /**
     * Returns element by index.
     * @param index integer value of index.
     * @return value at index.
     */
    public T get(int index) {
        return data.get(index);
    }

    @Override
    public T pop(T element) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(element)) {
                final T temp = data.get(i);
                data.remove(i);
                return temp;
            }
        }
        return null;
    }

    @Override
    public Boolean contains(T element) {
        return data.contains(element);
    }

    @Override
    public Integer size() {
        return this.data.size();
    }
}
