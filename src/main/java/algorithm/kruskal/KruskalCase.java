package algorithm.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 顶点为：'A', 'B', 'C', 'D', 'E', 'F', 'G'
 * 数字为两个顶点间的权值
 * 最小生成树就是将所有顶点连接起来并且权值最小
 */
public class KruskalCase {
    private int edgeNum; //边的数目
    private char[] vertexs; //顶点集合
    private int[][] matrix; //邻接矩阵

    private static final int INF = Integer.MAX_VALUE;

    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点
        this.vertexs = new char[vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化邻接矩阵
        this.matrix = new int[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void printMatrix() {
        System.out.println("邻接矩阵为:");
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.printf("%10d\t", col);
            }
            System.out.println();
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (ch == vertexs[i]) {
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges() {
        EData[] edges = new EData[edgeNum];
        int idx = 0;
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[idx++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 查询顶点对应的终点的下标
     *
     * @param ends 各个顶点对应连接的顶点的下标
     * @param i    需要查询终点的顶点的下标
     * @return
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * n个顶点有n-1条边进行连通，n-1条边满足不构成回路就是最小生成树，这就是kruskal算法的原来。
     *
     * @return
     */
    public List<EData> kruskal() {
        int[] ends = new int[edgeNum];
        EData[] edges = getEdges();
        List<EData> edgeList = Arrays.asList(edges);
        Collections.sort(edgeList);
        ArrayList<EData> res = new ArrayList<>();
        for (int i = 0; i < edgeList.size(); i++) {
            int startIdx = getPosition(edgeList.get(i).start);
            int endIdx = getPosition(edgeList.get(i).end);
            //没有相同的终点 不会构成回路
            if (getEnd(ends, startIdx) != getEnd(ends, endIdx)) {
                ends[startIdx] = endIdx; //设置当前顶点连接的另一个顶点
                res.add(edgeList.get(i));  //加入到最小生成树的结果集合中
            }
        }
        return res;
    }


    private static class EData implements Comparable<EData> {
        private char start;//边的起点
        private char end;//边的终点
        private int weight;//边的权值

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EData{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    "}\n";
        }

        @Override
        public int compareTo(EData eData) {
            return this.weight - eData.weight;
        }
    }

    public static void main(String[] args) {
        char[] vertexs = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.printMatrix();
        List<EData> dataList = kruskalCase.kruskal();
        System.out.println(dataList);
    }


}
