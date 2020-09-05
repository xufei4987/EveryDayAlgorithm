package datastructure.set;

import datastructure.map.HashMap;
import datastructure.map.Map;

public class HashSet<E> implements Set<E> {
    private HashMap<E,Object> hashMap = new HashMap<>();
    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public boolean contains(E e) {
        return hashMap.containsKey(e);
    }

    @Override
    public void add(E e) {
        hashMap.put(e,null);
    }

    @Override
    public void remove(E e) {
        hashMap.remove(e);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        hashMap.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
