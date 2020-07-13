package datastructure.linkedlist;

public class SingleLinkedOrderedList<E extends Comparable>  {

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

    public void addByOrder(E e){
        Node node = new Node(e, null);
        if(headNode.next == null){
            headNode.next = node;
            return;
        }
        Node tmpNode = headNode;
        while (tmpNode.next != null){
            if(tmpNode.next.item.compareTo(e) > 0){
                node.next = tmpNode.next;
                tmpNode.next = node;
                return;
            }else if(tmpNode.next.item.compareTo(e) == 0){
                System.out.printf("item %s is exist",node);
                return;
            }
            tmpNode = tmpNode.next;
        }
        tmpNode.next = node;
    }

    //更新节点
    public void update(E e){
        Node<E> node = new Node<>(e, null);
        if(headNode.next == null){
            System.out.println("linked list is empty");
            return;
        }
        Node tmpNode = headNode;
        while (tmpNode.next != null){
            if(tmpNode.next.item.compareTo(e) == 0){
                node.next = tmpNode.next.next;
                tmpNode.next = node;
                return;
            }
            tmpNode = tmpNode.next;
        }
        System.out.println("没有找到可以更新的节点");
    }

    public void remove(E e){
        if(headNode.next == null){
            System.out.println("linked list is empty");
            return;
        }
        Node tmpNode = headNode;
        while (tmpNode.next != null){
            if(tmpNode.next.item.compareTo(e) == 0){
                tmpNode.next = tmpNode.next.next;
                return;
            }
            tmpNode = tmpNode.next;
        }
        System.out.println("没有找到需要删除的节点");
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

    /**
     * 单链表逆序打印
     */
    public void reverseShowList(){
        if(headNode.next == null || headNode.next.next == null){
            showList();
        }
        reverseShowList0(headNode.next);
    }

    private void reverseShowList0(Node node) {
        if(node.next != null){
            reverseShowList0(node.next);
        }
        System.out.println(node.item.toString());
    }

    /**
     * 单链表反转
     */
    public void reverse(){
        if(headNode.next == null || headNode.next.next == null){
            return;
        }
        Node newHeadNode = new Node(null, null);
        Node tmpNode = headNode.next;
        Node nextNode = null;
        while (tmpNode != null){
            //先保存当前节点的下一个节点
            nextNode = tmpNode.next;
            //从头部插入节点
            tmpNode.next = newHeadNode.next;
            newHeadNode.next = tmpNode;
            //获取下一个节点 进入下一次循环
            tmpNode = nextNode;
        }
        headNode.next = newHeadNode.next;
    }

    private static class Node<E extends Comparable> {
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
        SingleLinkedOrderedList<HeroItem> singleLinkedList = new SingleLinkedOrderedList<>();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.showList();
//        HeroItem hero5 = new HeroItem("林冲111", "豹子头", 5);
//        singleLinkedList.update(hero5);
//        singleLinkedList.showList();
//        HeroItem hero6 = new HeroItem("林冲", "豹子头", 4);
//        singleLinkedList.remove(hero6);
//        singleLinkedList.showList();
//        singleLinkedList.showList();
//        System.out.println("单链表反转");
//        singleLinkedList.reverse();
//        singleLinkedList.showList();
        singleLinkedList.reverseShowList();
    }
}


