package algorithm.divideAndConquer;

/**
 * 汉诺塔
 */
public class HanoiTower {
    public long count = 0L;

    /**
     * 汉诺塔的移动方法
     *
     * @param num 需要移动的个数
     * @param a   需要移动的塔
     * @param b   可以借助的塔
     * @param c   目标塔
     */
    public void towerMove(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第一个盘从" + a + "->" + c);
            count++;
        } else {
            //n>=2时始终视为2个盘  最上面的n-1视为一个盘 最下面的一个盘视为一个盘
            //先将最上面的盘移动到从A->B
            towerMove(num - 1, a, c, b);
            //将最下面的一个盘从A->C
            System.out.println("第一个盘从" + a + "->" + c);
            count++;
            //将B塔所有的盘移动到C
            towerMove(num - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.towerMove(3,'A','B','C');
        System.out.println(hanoiTower.count);
    }
}
