package algorithm1.recursion;

/**
 * 当n=1时，直接从p1挪动到p3，结束
 * 当n>1时，有3个步骤：
 * 1、将n-1个盘子视为一个整体从p1移动到p2（借助p3）
 * 2、将第n个盘子移动到p3
 * 3、将n-1个盘子从p2移动到p3（借助p1）
 */
public class Hanoi {
    /**
     * 将n个碟子从p1挪动到p3
     * @param n
     * @param p1
     * @param p2
     * @param p3
     */
    public static void hanoi(int n, String p1, String p2, String p3){
        if(n == 1){
            move(n,p1,p3);
            return;
        }
        hanoi(n-1,p1,p3,p2);
        move(n,p1,p3);
        hanoi(n-1,p2,p1,p3);
    }

    private static void move(int no, String from, String to){
        System.out.println("将第" + no + "号盘子从" + from + "移动到" + to);
    }

    public static void main(String[] args) {
        hanoi(3,"A","B","C");
    }
}
