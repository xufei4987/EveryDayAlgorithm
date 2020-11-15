package algorithm.dynamicProgramming;

import java.util.Arrays;

/**
 * 0-1背包问题
 *
 * 吉他G 体积1 价格1500
 * 音响S 体积4 价格3000
 * 电脑L 体积3 价格2000
 * 空N
 *
 * 横坐标为背包容量，纵坐标为需要放入的物品
 *     0   1   2   3   4
 * N   0   0   0   0   0
 * G   0   G   G   G   G
 * S   0   G   G   G   S
 * L   0   G   G   L   L+G
 */
public class PackageProblem {
    public static void main(String[] args) {
        int[] weight = {1,4,3};//物品的重量
        int[] value = {1500,3000,2000};//物品的价值
        String[] name = {"吉他G","音响S","电脑L"};
        int capacity = 4;//背包的容量
        int count = value.length;//物品的个数

        int[][] v = new int[count + 1][capacity + 1];//存放背包在某一容量下能存放物品的最大价值
        int[][] path = new int[count + 1][capacity + 1];
        for (int i = 1; i < v.length; i++){
            for (int j = 1; j < v[0].length; j++){
                if(weight[i - 1] > j){
                    //当前物品的体积大于背包的容量时，取当前背包容量能放的最大价值
                    v[i][j] = v[i-1][j];
                } else {
                    //当物品的体积小于背包容量时，取当前背包容量能放的最大价值 和 当前物品价值加上背包剩余空间能放的最大价值 中的最大值
                    v[i][j] = Math.max(v[i-1][j],value[i-1] + v[i-1][j - weight[i-1]]);
                    path[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < v.length; i++){
            System.out.println(Arrays.toString(v[i]));
        }
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.println("将" + name[i - 1] + "放入背包");
                j = j - weight[i-1];//剩余容量还能放入什么
            }
            i--;
        }
    }
}
