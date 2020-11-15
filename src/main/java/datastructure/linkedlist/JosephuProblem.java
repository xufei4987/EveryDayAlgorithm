package datastructure.linkedlist;


/**
 * 约瑟夫问题：
 * 根据报数数m，求出出圈的顺序
 * 如 1->2->3->4->5->1  5个数组成了环形链表   从1开始数数  每次数2出圈   出圈顺序为 2->4->1->5->3
 */
public class JosephuProblem {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
//        circleSingleLinkedList.addBoy(5);
//        circleSingleLinkedList.showBoys();
        //根据用户输入的参数，输出小孩出圈的顺序
        circleSingleLinkedList.countBoy(2,3,10);
    }


    private static class CircleSingleLinkedList {
        private Boy first = null;

        public void addBoy(int num) {
            if (num < 1) {
                System.out.println("num is incorrect");
                return;
            }
            Boy curBoy = null;
            for (int i = 1; i <= num; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;
                    first.next = first;
                    curBoy = first;
                } else {
                    curBoy.next = boy;
                    boy.next = first;
                    curBoy = boy;
                }
            }
        }

        public void showBoys(){
            if(first == null){
                System.out.println("链表为空");
                return;
            }
            Boy curBoy = first;
            do {
                System.out.println("小孩的编号是：" + curBoy.no);
                curBoy = curBoy.next;
            }while (curBoy != first);
        }

        /**
         * 根据参数，计算出圈的顺序
         * @param startNo 从第几个小孩开始数
         * @param countNum 数几个数出圈
         * @param boyCount 小孩的数量
         */
        public void countBoy(int startNo, int countNum, int boyCount){
            addBoy(boyCount);
            if(first == null || startNo < 1 || startNo > boyCount){
                System.out.println("参数错误");
                return;
            }
            //定义一个辅助指针，指向最后一个节点（因为节点出圈需要用到前一个节点）
            Boy helper = first;
            while (true){
                if(helper.next == first){
                    break;
                }
                helper = helper.next;
            }
            //报数前，先移动到startNo处（startNo-1次）
            for(int i = 0; i < startNo - 1; i++){
                first = first.next;
                helper = helper.next;
            }
            //出圈
            do{
                for (int i = 0; i < countNum - 1; i++){
                    first = first.next;
                    helper = helper.next;
                }
                System.out.println(first.no + "号小孩出圈");
                first = first.next;
                helper.next = first;
            }while (first != helper);

            System.out.println(first.no + "号小孩是圈内的最后一个");
        }
    }

    private static class Boy {
        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }
}
