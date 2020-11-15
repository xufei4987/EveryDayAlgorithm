package datastructure.list;

public class DoubleLinkedListTest {
    public static void main(String[] args) {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        System.out.println(list.remove(2));
        System.out.println(list);

    }
}
