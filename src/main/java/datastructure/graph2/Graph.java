package datastructure.graph2;

import java.util.List;
import java.util.Set;

public abstract class Graph<V, E> {

    protected WeightManager<E> weightManager;

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    /**
     * 边的数量
     * @return
     */
    public abstract int edgesSize();

    /**
     * 顶点的数量
     * @return
     */
    public abstract int verticesSize();

    /**
     * 添加顶点
     * @param v
     */
    public abstract void addVertex(V v);

    /**
     * 添加边
     * @param from
     * @param to
     */
    public abstract void addEdge(V from, V to);

    /**
     * 添加边，带权值
     * @param from
     * @param to
     * @param wight
     */
    public abstract void addEdge(V from, V to, E wight);

    /**
     * 删除顶点
     * @param v
     */
    public abstract void removeVertex(V v);

    /**
     * 删除边
     * @param from
     * @param to
     */
    public abstract void removeEdge(V from, V to);

    /**
     * 广度优先遍历
     */
    public abstract void bfs(V begin, Visitor<V> visitor);

    /**
     * 深度优先遍历
     */
    public abstract void dfs(V begin, Visitor<V> visitor);

    /**
     * 深度优先遍历(非递归)
     */
    public abstract void dfsWithoutRecursion(V begin, Visitor<V> visitor);

    /**
     * 拓扑排序 必须是有向无环图
     */
    public abstract List<V> topologicSort();

    /**
     * 最小生成树
     */
    public abstract Set<EdgeInfo<V,E>> mst();

    public interface Visitor<V>{
        void visit(V v);
    }

    public interface WeightManager<E>{
        int compare(E w1, E w2);
        E add(E w1, E w2);
    }

    public static class EdgeInfo<V,E>{
        private V from;
        private V to;
        private E wight;

        public EdgeInfo(V from, V to, E wight) {
            this.from = from;
            this.to = to;
            this.wight = wight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWight() {
            return wight;
        }

        public void setWight(E wight) {
            this.wight = wight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", wight=" + wight +
                    '}';
        }
    }
}
