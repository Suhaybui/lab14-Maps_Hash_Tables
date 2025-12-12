import java.util.ArrayList;
import java.util.LinkedList;

class SeparateChainingMap<K, V> implements MapADT<K, V> {
    private ArrayList<LinkedList<Entry<K, V>>> table;
    private int size = 0;
    private final int N = 11;

    public SeparateChainingMap() {
        table = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            table.add(new LinkedList<>());
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % N);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public V get(K key) {
        int h = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(h);
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V put(K key, V value) {
        int h = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(h);

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.setValue(value);
            }
        }

        bucket.addFirst(new Entry<>(key, value));
        size++;
        return null;
    }

    public V remove(K key) {
        int h = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(h);

        Entry<K, V> toRemove = null;
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                toRemove = entry;
                break;
            }
        }

        if (toRemove != null) {
            V oldValue = toRemove.getValue();
            bucket.remove(toRemove);
            size--;
            return oldValue;
        }
        return null;
    }
}

public class SeparateChainingMapDemo {
    public static void main(String[] args) {
        SeparateChainingMap<String, Integer> map = new SeparateChainingMap<>();

        map.put("cat", 1);
        map.put("tac", 2);
        map.put("act", 3);

        System.out.println(map.get("cat"));
        System.out.println(map.get("tac"));
        System.out.println(map.get("act"));
    }
}
