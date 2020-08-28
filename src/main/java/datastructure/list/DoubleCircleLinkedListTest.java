package datastructure.list;

public class DoubleCircleLinkedListTest {
    public static void main(String[] args) {
        DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        josephus(list,3);
//        System.out.println(list);
//        System.out.println(list.remove(0));
//        System.out.println(list);
//        System.out.println(list.remove(list.size() - 1 ));
//        System.out.println(list);
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list);
    }

    /**
     * 约瑟夫问题
     */
    public static void josephus(DoubleCircleLinkedList list, int step){
        list.reset();
        while (!list.isEmpty()){
            for (int i = 1; i < step; i++) {
                list.next();
            }
            System.out.println(list.remove());
        }
    }
}
