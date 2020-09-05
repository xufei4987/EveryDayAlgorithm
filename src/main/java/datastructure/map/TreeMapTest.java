package datastructure.map;

public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3,"haha");
        treeMap.put(4,"xxxx");
        treeMap.put(1,"haaaaha");
        treeMap.put(2,"hahddda");
        treeMap.traversal(new Map.Visitor<Integer, String>() {
            @Override
            public boolean visit(Integer key, String value) {
                System.out.println(key.toString() + "-" + value.toString());
                return true;
            }
        });
    }
}
