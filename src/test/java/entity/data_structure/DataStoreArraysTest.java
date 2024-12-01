package entity.data_structure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataStoreArraysTest {

    @Test
    void testAddAndSize() {
        DataStore<String> dataStore = new DataStoreArrays<>();
        assertEquals(0, dataStore.size());

        dataStore.add("Element1");
        assertEquals(1, dataStore.size());

        dataStore.add("Element2");
        dataStore.add("Element3");
        assertEquals(3, dataStore.size());
    }

    @Test
    void testRemove() {
        DataStore<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");
        dataStore.add("Element2");

        assertEquals("Element1", dataStore.remove("Element1"));
        assertFalse(dataStore.contains("Element1"));
        assertEquals(1, dataStore.size());
    }

    @Test
    void testGet() {
        DataStore<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");
        dataStore.add("Element2");

        assertEquals("Element1", dataStore.get("Element1"));
        assertNull(dataStore.get("NonExistent"));
    }

    @Test
    void testPop() {
        DataStore<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");
        dataStore.add("Element2");

        assertEquals("Element2", dataStore.pop());
        assertEquals(1, dataStore.size());
        assertEquals("Element1", dataStore.pop());
        assertEquals(0, dataStore.size());
    }

    @Test
    void testContains() {
        DataStore<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");

        assertTrue(dataStore.contains("Element1"));
        assertFalse(dataStore.contains("Element2"));
    }

    @Test
    void testIntersection() {
        DataStore<String> dataStore1 = new DataStoreArrays<>();
        dataStore1.add("Element1");
        dataStore1.add("Element2");
        dataStore1.add("Element3");

        DataStore<String> dataStore2 = new DataStoreArrays<>();
        dataStore2.add("Element2");
        dataStore2.add("Element3");
        dataStore2.add("Element4");

        DataStore<String> intersection = dataStore1.intersection(dataStore2);

        List<String> expected = List.of("Element2", "Element3");
        List<String> result = new ArrayList<>();
        intersection.getAll().forEach(result::add);

        assertEquals(expected, result);
    }

    @Test
    void testComplement() {
        DataStore<String> dataStore1 = new DataStoreArrays<>();
        dataStore1.add("Element1");
        dataStore1.add("Element2");
        dataStore1.add("Element3");

        DataStore<String> dataStore2 = new DataStoreArrays<>();
        dataStore2.add("Element2");

        DataStore<String> complement = dataStore1.complement(dataStore2);

        List<String> expected = List.of("Element1", "Element3");
        List<String> result = new ArrayList<>();
        complement.getAll().forEach(result::add);
        // Complement should contain elements in the first but not in the second
        assertEquals(expected, result);
    }

    @Test
    void testGetAll() {
        DataStore<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");
        dataStore.add("Element2");

        List<String> result = new ArrayList<>();
        dataStore.getAll().forEach(result::add);

        List<String> expected = List.of("Element1", "Element2");
        assertEquals(expected, result);
    }

    @Test
    void testGetByIndex() {
        DataStoreArrays<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");
        dataStore.add("Element2");

        assertEquals("Element1", dataStore.getByIndex(0));
        assertEquals("Element2", dataStore.getByIndex(1));
        assertThrows(IndexOutOfBoundsException.class, () -> dataStore.getByIndex(2));
    }

    @Test
    void testToArrayList() {
        DataStoreArrays<String> dataStore = new DataStoreArrays<>();
        dataStore.add("Element1");
        dataStore.add("Element2");

        ArrayList<String> arrayList = dataStore.toArrayList();

        assertEquals(2, arrayList.size());
        assertTrue(arrayList.contains("Element1"));
        assertTrue(arrayList.contains("Element2"));
    }

    @Test
    void testToDataStore() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Element1");
        arrayList.add("Element2");

        DataStoreArrays<String> dataStore = new DataStoreArrays<>();
        DataStore<String> convertedDataStore = dataStore.toDataStore(arrayList);

        assertEquals(2, convertedDataStore.size());
        assertTrue(convertedDataStore.contains("Element1"));
        assertTrue(convertedDataStore.contains("Element2"));
    }
}
