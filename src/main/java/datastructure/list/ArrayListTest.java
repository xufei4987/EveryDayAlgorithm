package datastructure.list;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        for (int i = 0; i < 50; i++) {
            list.remove(0);
        }
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
