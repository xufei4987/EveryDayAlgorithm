package datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图的表示方式：
 * 1、邻接矩阵 ： 二维数组
 * 2、邻接表 ： 一维数组加链表
 */
public class Graph {
    private List<String> vertexList; //存储顶点的集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numberOfEdges; //边的数目

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
    }

    /**
     * 插入顶点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入顶点
     *
     * @param v1     顶点1的下标
     * @param v2     顶点2的下标
     * @param weight 边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }

    public int getNumberOfVertex() {
        return vertexList.size();
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public String getVertexByIdx(int idx) {
        return vertexList.get(idx);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void printGrap() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 图的深度优先遍历
     */
    public void depthFirstSearch() {
        boolean[] isVisited = new boolean[vertexList.size()];
        //防止从第一个节点出发不能到达所以节点的情况
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getVertexByIdx(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 获取当前节点的第一个邻接节点
     *
     * @param idx
     * @return
     */
    private int getFirstNeighbor(int idx) {
        for (int i = 0; i < edges[idx].length; i++) {
            if (edges[idx][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据当前节点和邻接节点获取下一个邻接节点
     *
     * @param idx1
     * @param idx2
     * @return
     */
    private int getNextNeighbor(int idx1, int idx2) {
        for (int i = idx2 + 1; i < edges[idx1].length; i++) {
            if (edges[idx1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 图的广度优先遍历
     */
    public void broadFirstSearch() {
        boolean[] isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    private void bfs(boolean[] isVisited, int i) {
        //记录节点的访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getVertexByIdx(i) + "->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            int u = queue.removeFirst();
            int w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getVertexByIdx(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public static void main(String[] args) {
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(vertexs.length);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边 A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.printGrap();
        System.out.println("深度优先遍历");
        graph.depthFirstSearch();
        System.out.println();
        System.out.println("广度优先遍历");
        graph.broadFirstSearch();
        System.out.println();
    }
}
