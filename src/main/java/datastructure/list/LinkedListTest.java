package datastructure.list;

public class LinkedListTest {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        ((LinkedList) list).reverse1();
        System.out.println(list);
//        System.out.println(list);
//        list.remove(1);
//        System.out.println(list);
//        list.insert(1,2);
//        System.out.println(list);
//        list.insert(list.size(),4);
//        System.out.println(list);
    }
}
