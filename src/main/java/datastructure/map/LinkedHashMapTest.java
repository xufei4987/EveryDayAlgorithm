package datastructure.map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<Object, Integer> hashMap = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hashMap.put(new HashMapTest.Person("youxu" + i,i+10,1.80f + i*1.0f/100), i);
        }
        for (int i = 1; i <= 3; i++) {
            hashMap.remove(new HashMapTest.Person("youxu" + i, i + 10, 1.80f + i * 1.0f / 100));
        }
        int i = 5;
        hashMap.remove(new HashMapTest.Person("youxu" + i, i + 10, 1.80f + i * 1.0f / 100));
        hashMap.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "---" + value.toString());
                return false;
            }
        });
    }
}
