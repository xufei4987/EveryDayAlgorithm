package datastructure.graph2;

public interface Graph<V, E> {
    /**
     * 边的数量
     * @return
     */
    int edgesSize();

    /**
     * 顶点的数量
     * @return
     */
    int verticesSize();

    /**
     * 添加顶点
     * @param v
     */
    void addVertex(V v);

    /**
     * 添加边
     * @param from
     * @param to
     */
    void addEdge(V from, V to);

    /**
     * 添加边，带权值
     * @param from
     * @param to
     * @param wight
     */
    void addEdge(V from, V to, E wight);

    /**
     * 删除顶点
     * @param v
     */
    void removeVertex(V v);

    /**
     * 删除边
     * @param from
     * @param to
     */
    void removeEdge(V from, V to);
}
