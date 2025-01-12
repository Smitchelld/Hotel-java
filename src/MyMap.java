import java.util.*;
import java.util.Map;

public class MyMap<K, V> {
    // Lists to store keys and values
    private List<K> keys;
    private List<V> values;

    /**
     * Constructs an empty MyMap instance.
     */
    public MyMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    /**
     * Puts a key-value pair into the map.
     * If the key already exists, updates its associated value.
     *
     * @param key The key to be added or updated in the map.
     * @param value The value to be associated with the key.
     * @return The previous value associated with the key, or null if the key was not already in the map.
     */
    public V put(K key, V value) {
        int index = keys.indexOf(key); // Find index of key
        if (index == -1) { // If key doesn't exist, add a new entry
            keys.add(key);
            values.add(value);
            return null;
        } else { // If key exists, update the value at the corresponding index
            return values.set(index, value);
        }
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        int index = keys.indexOf(key); // Find index of key
        if (index == -1) { // If key doesn't exist, return null
            return null;
        } else { // If key exists, return the corresponding value
            return values.get(index);
        }
    }

    /**
     * Returns a list of all the keys in the map.
     *
     * @return A list of all the keys in the map.
     */
    public List<K> keys() {
        return new ArrayList<>(keys);
    }

    /**
     * Returns a list of all the values in the map.
     *
     * @return A list of all the values in the map.
     */
    public List<V> values() {
        return new ArrayList<>(values);
    }

    /**
     * Removes the key-value pair associated with the given key.
     *
     * @param key The key whose associated key-value pair is to be removed.
     * @return The value associated with the removed key, or null if the key was not found.
     */
    public V remove(K key) {
        int index = keys.indexOf(key); // Find index of key
        if (index == -1) { // If key doesn't exist, return null
            return null;
        } else { // If key exists, remove the entry and return the associated value
            keys.remove(index);
            return values.remove(index);
        }
    }

    /**
     * Returns the number of key-value pairs in the map.
     *
     * @return The number of key-value pairs in the map.
     */
    public int size() {
        return keys.size();
    }

    /**
     * Checks if the map is empty.
     *
     * @return True if the map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    /**
     * Checks if the map contains the given key.
     *
     * @param key The key to check for in the map.
     * @return True if the key exists in the map, false otherwise.
     */
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    /**
     * Checks if the map contains the given value.
     *
     * @param value The value to check for in the map.
     * @return True if the value exists in the map, false otherwise.
     */
    public boolean containsValue(V value) {
        return values.contains(value);
    }

    /**
     * Clears all the key-value pairs in the map.
     */
    public void clear() {
        keys.clear();
        values.clear();
    }
}