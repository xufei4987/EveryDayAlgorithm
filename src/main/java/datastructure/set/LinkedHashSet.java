package datastructure.set;

import datastructure.map.LinkedHashMap;
import datastructure.map.Map;

public class LinkedHashSet<E> implements Set<E> {
    private LinkedHashMap<E,Object> linkedHashMap = new LinkedHashMap<>();
    @Override
    public int size() {
        return linkedHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedHashMap.isEmpty();
    }

    @Override
    public void clear() {
        linkedHashMap.clear();
    }

    @Override
    public boolean contains(E e) {
        return linkedHashMap.containsKey(e);
    }

    @Override
    public void add(E e) {
        linkedHashMap.put(e,null);
    }

    @Override
    public void remove(E e) {
        linkedHashMap.remove(e);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        linkedHashMap.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
