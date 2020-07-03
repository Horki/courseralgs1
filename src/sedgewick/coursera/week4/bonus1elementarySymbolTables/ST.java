package sedgewick.coursera.week4.bonus1elementarySymbolTables;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.TreeMap;

public class ST<K extends Comparable<K>, V> implements Iterable<K> {
    private TreeMap<K, V> st;

    public ST() {
        st = new TreeMap<>();
    }

    public V get(K k) {
        if (k == null) {
            throw new IllegalArgumentException("get: Key is null");
        }
        return st.get(k);
    }

    public void put(K k, V v) {
        if (k == null) {
            throw new IllegalArgumentException("put: Key is null");
        }
        if (v == null) {
            st.remove(k);
        } else {
            st.put(k, v);
        }
    }

    public void delete(K k) {
        if (k == null) {
            throw new IllegalArgumentException("delete: Key is null");
        }
        st.remove(k);
    }

    public boolean contains(K k) {
        if (k == null) {
            throw new IllegalArgumentException("contains: Key is null");
        }
        return st.containsKey(k);
    }

    public int size() {
        return st.size();
    }

    public Iterable<K> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<K> iterator() {
        return st.keySet().iterator();
    }

    public K ceiling(K k) {
        if (k == null) {
            throw new IllegalArgumentException("ceiling: Key is null");
        }
        K key = st.ceilingKey(k);
        if (key == null) {
            throw new IllegalArgumentException("ceiling: Argument fail");
        }
        return key;
    }


    public K floor(K k) {
        if (k == null) {
            throw new IllegalArgumentException("floor: Key is null");
        }
        K key = st.floorKey(k);
        if (key == null) {
            throw new IllegalArgumentException("floor: Argument fail");
        }
        return key;
    }

    public static void main(String[] args) {
        ST<Character, Integer> st = new ST<>();
        String input = "SEARCHEXAMPLE";
        for (int i = 0; i < input.length(); ++i) {
            st.put(input.charAt(i), i);
        }
        for (Character key : st.keys()) {
            StdOut.println(key + ": " + st.get(key));
        }
    }
}
