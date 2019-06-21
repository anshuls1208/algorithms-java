package caching.lru;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 * Least recently used cache implementation.
 * </p>
 *
 * @author anshulsharma1208@gmail.com
 */
public class LRUCache {

    public static void main(String[] args) {

        LRUCacheUsingQueue<Integer> cacheUsingQueue = new LRUCacheUsingQueue<>(5);

        cacheUsingQueue.touch(1);
        cacheUsingQueue.touch(2);
        cacheUsingQueue.touch(3);
        cacheUsingQueue.touch(4);
        cacheUsingQueue.print();
        System.out.println("LRU element: " + cacheUsingQueue.peekLRU());

        cacheUsingQueue.touch(2);
        cacheUsingQueue.print();
        System.out.println("LRU element: " + cacheUsingQueue.peekLRU());

        cacheUsingQueue.touch(5);
        cacheUsingQueue.touch(6);
        cacheUsingQueue.print();
        System.out.println("LRU element: " + cacheUsingQueue.peekLRU());

        cacheUsingQueue.touch(3);
        cacheUsingQueue.print();
        System.out.println("LRU element: " + cacheUsingQueue.peekLRU());
        
    }

}

/**
 * <p>
 * This implementation implements the LRU cache using a queue,
 * built on top of a doubly linked list. Most recently
 * used node appears towards the front of the queue
 * and least recently used appears towards the rear.
 * </p>
 *
 * @param <K>
 */
class LRUCacheUsingQueue<K> {

    private static final int default_cache_size = 16;

    // max size of cache
    private int size;

    // doubly linked list storing cache
    private Deque<K> deque;

    /*
    referenced keys, to ensure that there is only
    unique key set available in the cache.
    This could and should point to actual keys
    of the cache and that cache implementation could
    be any - A java hash map, or some third party cache.
     */
    private Set<K> referencedKeySet;

    LRUCacheUsingQueue() {
        this(default_cache_size);
    }

    LRUCacheUsingQueue(int size) {
        this.size = size;
        deque = new LinkedList<>();
        referencedKeySet = new HashSet<>();
    }

    /**
     * <p>
     * Touch cache, which should perform a
     * "least recently used" operation.
     * </p>
     *
     * @param k
     */
    public void touch(K k) {

        if (referencedKeySet.contains(k)) {

            int position = 0;
            int counter = 0;

            Iterator<K> iterator = deque.iterator();

            while (iterator.hasNext()) {

                if (iterator.next().equals(k)) {
                    position = counter;
                    break;
                }

                counter++;
            }

            // Complexity O(n) as per java implementation
            deque.remove(position);

        } else {

            if (deque.size() == size) {
                K last = deque.removeLast();
                referencedKeySet.remove(last);
            }
        }

        deque.push(k);
        referencedKeySet.add(k);

    }

    public K peekLRU() {
        return deque.getLast();
    }

    public void print() {
        deque.stream().forEach(value -> System.out.print(value + " "));
        System.out.println();
    }
}

/**
 * <p>
 * This implementations implements LRU cache using map.
 * </p>
 *
 * @param <K>
 */
class LRUCacheUsingMap<K> {

    private static final int default_cache_size = 16;

    // max size of cache
    private int size;

    public LRUCacheUsingMap() {
        this(default_cache_size);
    }

    public LRUCacheUsingMap(int size) {
        this.size = size;
    }

    public void touch(K k) {
    }

    public K peekLRU() {
        return null;
    }

    public void print() {
    }

}