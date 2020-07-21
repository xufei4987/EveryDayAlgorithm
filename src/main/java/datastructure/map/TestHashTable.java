package datastructure.map;

public class TestHashTable {
    public static void main(String[] args) {
        HashTable<Integer, Emp> hashTable = new HashTable<>(10);
        for (int i = 0; i < 20; i++){
            Emp emp = new Emp(i + 1, "No." + (i + 1));
            hashTable.put(emp.getId(),emp);
        }
        System.out.println("查询到的节点：" + hashTable.get(1));
        hashTable.listTable();
        System.out.println("被删除的节点：" + hashTable.delete(1));
        hashTable.listTable();
    }
}
