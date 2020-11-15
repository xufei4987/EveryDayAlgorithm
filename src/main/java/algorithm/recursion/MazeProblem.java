package algorithm.recursion;

/**
 * 迷宫问题
 * 如何找到迷宫的最短路径：
 * 路径和策略有关
 * 穷举策略（4*3*2*1 = 24种策略） 然后统计最短路径（maze中2最少）
 */
public class MazeProblem {
    public static void main(String[] args) {
        //构建一个迷宫
        int[][] maze = new int[8][7];
        for(int i = 0; i < 7; i++){
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0; i < 8; i++){
            maze[i][0] = 1;
            maze[i][6] = 1;
        }

        maze[4][2] = 1;
        maze[5][2] = 1;
        maze[6][2] = 1;

        //输出迷宫
        System.out.println("===========初始化迷宫===========");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        findWay(maze,1,1);
        //输出迷宫
        System.out.println("===========找到路径后的迷宫===========");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 7; j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     *
     * @param maze 迷宫
     * @param i 开始的横坐标
     * @param j 开始的纵坐标
     * @return
     * 目标：找到maze[6][5]的路径
     * 0代表没走过、1代表墙、2代表通路可以走、3代表走过但走不通
     * 策略：下->右->上->左
     */
    public static boolean findWay(int[][] maze, int i, int j){
        if(maze[6][5] == 2){
            return true;
        }
        if(maze[i][j] == 0){
            maze[i][j] = 2;
            if(findWay(maze,i+1,j)){
                return true;
            }else if(findWay(maze,i,j+1)){
                return true;
            }else if(findWay(maze,i-1,j)){
                return true;
            }else if(findWay(maze,i,j-1)){
                return true;
            }else {
                maze[i][j] = 3;
                return false;
            }
        }else {
            return false;
        }
    }
}
