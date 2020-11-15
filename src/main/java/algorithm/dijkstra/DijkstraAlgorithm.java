package algorithm.dijkstra;

import java.util.Arrays;

/**
 * dijkstra寻找2点间的最短路径：
 * 1、设置两个顶点集S和T，集合S中存放已经找到最短路径的顶点，集合T中存放着当前还未找到最短路径的顶点；
 * 2、初始状态下，集合S中只包含源点V1，T中为除了源点之外的其余顶点，此时源点到各顶点的最短路径为两个顶点所连的边上的权值，如果源点V1到该顶点没有边，则最小路径为无穷大；
 * 3、从集合T中选取到源点V1的路径长度最短的顶点Vi加入到集合S中；
 * 4、修改源点V1到集合T中剩余顶点Vj的最短路径长度。新的最短路径长度值为Vj原来的最短路径长度值与顶点Vi的最短路径长度加上Vi到Vj的路径长度值中的较小者；
 * 5、不断重复步骤3、4，直至集合T的顶点全部加入到集合S中。
 */
public class DijkstraAlgorithm {
    public static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.printGraph();
        //从顶点G开始，找到与其他顶点的最短距离
        int[] dis = graph.dijstra(6);
        System.out.println(Arrays.toString(dis));
    }

    private static class Graph {
        private char[] vertex;
        private int[][] matrix;

        public Graph(char[] vertex, int[][] matrix) {
            this.vertex = vertex;
            this.matrix = matrix;
        }

        public void printGraph() {
            for (int[] link : matrix) {
                System.out.println(Arrays.toString(link));
            }
        }

        /**
         * @param i 起始顶点
         * @return 顶点i到各个顶点的最短距离
         */
        public int[] dijstra(int i) {
            int[] dis = new int[vertex.length];
            boolean[] visited = new boolean[vertex.length];
            visited[i] = true;
            //初始化顶点i到其他各个顶点的距离
            for (int j = 0; j < matrix[i].length; j++) {
                dis[j] = matrix[i][j];
            }
            dis[i] = 0;
            for (int m = 0; m < vertex.length; m++) {
                int min = N;
                int minIdx = -1;
                for (int a = 0; a < vertex.length; a++) {
                    //找到从顶点i到没访问过的各个顶点的最小值
                    if (!visited[a] && dis[a] < min) {
                        minIdx = a;
                        min = dis[a];
                    }
                }
                if (minIdx != -1) {
                    visited[minIdx] = true;
                    for (int b = 0; b < vertex.length; b++) {
                        //顶点b是 顶点i通过顶点minIdx能访问到的顶点
                        //顶点b没有访问过 顶点minIdx能到达顶点b 顶点i到minIdx的距离+顶点minIdx到顶点b的距离 < 顶点i到顶点b的距离 则更新这个距离
                        if (!visited[b]
                                && matrix[minIdx][b] != N
                                && dis[minIdx] + matrix[minIdx][b] < dis[b]) {
                            dis[b] = dis[minIdx] + matrix[minIdx][b];
                        }
                    }
                }
            }
            return dis;
        }
    }
}
