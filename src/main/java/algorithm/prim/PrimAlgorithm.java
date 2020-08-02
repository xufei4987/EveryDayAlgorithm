package algorithm.prim;

import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

/**
 * 顶点为：'A', 'B', 'C', 'D', 'E', 'F', 'G'
 * 数字为两个顶点间的权值
 * 最小生成树就是将所有顶点连接起来并且权值最小
 *      A   5   B
 *    7   2   3    9
 * C        G         D
 *   8    4   6    4
 *      E   5   F
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;
        int[][] weight = new int[][]{
                {MAX_VALUE, 5, 7, MAX_VALUE, MAX_VALUE, MAX_VALUE, 2},
                {5, MAX_VALUE, MAX_VALUE, 9, MAX_VALUE, MAX_VALUE, 3},
                {7, MAX_VALUE, MAX_VALUE, MAX_VALUE, 8, MAX_VALUE, MAX_VALUE},
                {MAX_VALUE, 9, MAX_VALUE, MAX_VALUE, MAX_VALUE, 4, MAX_VALUE},
                {MAX_VALUE, MAX_VALUE, 8, MAX_VALUE, MAX_VALUE, 5, 4},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 4, 5, MAX_VALUE, 6},
                {2, 3, MAX_VALUE, MAX_VALUE, 4, 6, MAX_VALUE}
        };

        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        Graph graph = primAlgorithm.createGraph(data, vertexs, weight);
        primAlgorithm.showGrap(graph);
        primAlgorithm.prim(graph, 0);
    }

    /**
     * prim查找最小生成树
     *
     * @param graph  图
     * @param vertex 从哪个顶点开始
     */
    private void prim(Graph graph, int vertex) {
        int vertexs = graph.getVertexs();
        boolean[] isVisited = new boolean[vertexs];
        isVisited[vertex] = true;
        int minWeight;
        int h1 = -1;
        int h2 = -1;
        for (int v = 1; v < vertexs; v++) {
            minWeight = MAX_VALUE;
            //找到已访问的节点与未访问节点中权值最小的，进行记录
            for (int i = 0; i < vertexs; i++){
                for (int j = 0; j < vertexs; j++){
                    if(isVisited[i] && !isVisited[j] && graph.getWeight()[i][j] < minWeight){
                        minWeight = graph.getWeight()[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            isVisited[h2] = true;
            System.out.println("<" + graph.getData()[h1] + "," + graph.getData()[h2] + "> 权值为: " + minWeight);
        }
    }

    private Graph createGraph(char[] data, int vertexs, int[][] weight) {
        Graph graph = new Graph(vertexs);
        graph.setData(data);
        graph.setWeight(weight);
        return graph;
    }

    private void showGrap(Graph graph) {
        for (int[] i : graph.getWeight()) {
            System.out.println(Arrays.toString(i));
        }
    }

    private static class Graph {
        int vertexs;
        char[] data;
        int[][] weight;

        public Graph(int vertexs) {
            this.vertexs = vertexs;
            this.data = new char[vertexs];
            this.weight = new int[vertexs][vertexs];
        }

        public void setVertexs(int vertexs) {
            this.vertexs = vertexs;
        }

        public void setData(char[] data) {
            this.data = data;
        }

        public void setWeight(int[][] weight) {
            this.weight = weight;
        }

        public int getVertexs() {
            return vertexs;
        }

        public char[] getData() {
            return data;
        }

        public int[][] getWeight() {
            return weight;
        }
    }
}
