package datastructure.linkedlist;

public class DoubleLinkedList<E extends Comparable> {

    private Node headNode = new Node(null,null,null);

    //遍历
    public void showList(){
        if(headNode.next == null){
            System.out.println("linked list is empty");
            return;
        }
        Node tmpNode = headNode.next;
        while (tmpNode != null){
            System.out.println(tmpNode.item.toString());
            tmpNode = tmpNode.next;
        }
    }
    //新增
    public void add(E e){
        Node<E> node = new Node<>(e, null, null);
        Node tmpNode = headNode;
        while (tmpNode.next != null){
            tmpNode = tmpNode.next;
        }
        tmpNode.next = node;
        node.prev = tmpNode;
    }
    //修改
    public void update(E e){
        if(headNode.next == null){
            System.out.println("没有可以更新的节点");
            return;
        }
        Node tmpNode = headNode.next;
        boolean find = false;
        while (tmpNode != null){
            if(tmpNode.item.compareTo(e) == 0){
                find = true;
                break;
            }
            tmpNode = tmpNode.next;
        }
        if (find){
            tmpNode.item = e;
            System.out.println("更新成功");
        }else{
            System.out.println("没有可以更新的节点");
        }
    }
    //删除
    public void remove(E e){
        if(headNode.next == null){
            System.out.println("没有可以删除的节点");
            return;
        }
        Node tmpNode = headNode.next;
        while (tmpNode != null){
            if(tmpNode.item.compareTo(e) == 0){
                tmpNode.prev.next = tmpNode.next;
                tmpNode.next.prev = tmpNode.prev;
                System.out.println("删除成功");
                return;
            }
            tmpNode = tmpNode.next;
        }
        System.out.println("没有可以删除的节点");
    }

    private static class Node<E extends Comparable> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E element, Node<E> prev, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        HeroItem hero1 = new HeroItem("宋江", "及时雨", 1);
        HeroItem hero2 = new HeroItem("卢俊义", "玉麒麟", 2);
        HeroItem hero3 = new HeroItem("吴用", "智多星", 3);
        HeroItem hero4 = new HeroItem("林冲", "豹子头", 4);
        DoubleLinkedList<HeroItem> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.showList();
        HeroItem hero01 = new HeroItem("宋江1", "及时雨1", 1);
        doubleLinkedList.update(hero01);
        doubleLinkedList.showList();
        doubleLinkedList.remove(hero01);
        doubleLinkedList.showList();
    }
}
