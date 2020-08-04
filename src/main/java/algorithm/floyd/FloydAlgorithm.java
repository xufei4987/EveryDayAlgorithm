package algorithm.floyd;

import java.util.Arrays;
import java.util.Stack;

public class FloydAlgorithm {
    public static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(matrix, vertex);
        graph.floyd();
        graph.show();
    }

    private static class Graph {
        private char[] vertex;
        private int[][] dis; //各个顶点到各个顶点的距离
        private int[][] pre; //到达目标顶点的前驱顶点的下标

        public Graph(int[][] matrix, char[] vertex) {
            this.vertex = vertex;
            this.dis = matrix;
            this.pre = new int[vertex.length][vertex.length];
            for (int i = 0; i < vertex.length; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        public void show() {
            for (int k = 0; k < dis.length; k++) {
                System.out.println(Arrays.toString(dis[k]));
            }
            System.out.println("---------------------");
            for (int k = 0; k < pre.length; k++) {
                System.out.println(Arrays.toString(pre[k]));
            }
            System.out.println("---------------------");
            for (int i = 0; i < pre.length; i++) {
                for (int j = 0; j < pre.length; j++) {
                    if(vertex[i] != vertex[j]){
                        System.out.print(vertex[i] + "到" + vertex[j] + "的最短路径:");
                        printMinPath(i,j);
                    }
                }
            }
        }

        private void printMinPath(int i, int j) {
            Stack<String> strings = new Stack<>();
            strings.push(String.valueOf(vertex[j]));
            int tmp = pre[i][j];
            while (true){
                strings.push(String.valueOf(vertex[tmp]));
                if(tmp == i){
                    break;
                }
                tmp = pre[i][tmp];
            }
            while (!strings.isEmpty()){
                System.out.print("->" + strings.pop());
            }
            System.out.println();
        }

        /**
         * Lij = Lik + Lkj
         * i-j间的最短路径 = i-k间的最短路径 + k-j间的最短路径
         * i起始节点 k中间节点 j终止节点
         */
        public void floyd() {
            //中间节点
            for (int k = 0; k < vertex.length; k++) {
                //起始节点
                for (int i = 0; i < vertex.length; i++) {
                    //终止节点
                    for (int j = 0; j < vertex.length; j++) {
                        if (dis[i][j] > dis[i][k] + dis[k][j]) {
                            dis[i][j] = dis[i][k] + dis[k][j];
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
        }
    }
}
