package datastructure.linkedlist;

import java.io.IOException;

public class SingleLinkedList<E>  {

    private Node headNode = new Node(null, null);

    public void add(E e){
        Node node = new Node(e, null);
        if(headNode.next == null){
            headNode.next = node;
            return;
        }
        Node tmpNode = headNode.next;
        while (true){
            if(tmpNode.next == null){
                tmpNode.next = node;
                break;
            }
            tmpNode = tmpNode.next;
        }
    }

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

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        HeroItem hero1 = new HeroItem("宋江", "及时雨", 1);
        HeroItem hero2 = new HeroItem("卢俊义", "玉麒麟", 2);
        HeroItem hero3 = new HeroItem("吴用", "智多星", 3);
        HeroItem hero4 = new HeroItem("林冲", "豹子头", 4);
        SingleLinkedList<HeroItem> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.showList();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


