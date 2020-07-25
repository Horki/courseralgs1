package sedgewick.coursera.week5.interfaces;

// https://en.wikipedia.org/wiki/2%E2%80%933_tree
public interface TwoThreeTree<K, V> {
    V get(K key);

    void put(K key, V value);

    int height();

    int size();

    boolean isEmpty();

    boolean contains(K key);

    K min();

    K max();
}
