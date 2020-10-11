package datastructure.graph2;

import datastructure.union.GenericUnionFind;
import datastructure.union.UnionFind;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (e1, e2) -> weightManager.compare(e1.weight, e2.weight);

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public void print() {
        edges.forEach(System.out::println);
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E wight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, wight);
        //删除 再添加 就是更新
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;
        //删除指向该顶点的边
        Iterator<Edge<V, E>> inEdgeIterator = vertex.inEdges.iterator();
        //利用迭代器进行删除
        while (inEdgeIterator.hasNext()) {
            Edge<V, E> nextInEdge = inEdgeIterator.next();
            nextInEdge.from.outEdges.remove(nextInEdge);
            inEdgeIterator.remove();
            edges.remove(nextInEdge);
        }
        //删除从该顶点出去的边
        Iterator<Edge<V, E>> outEdgeIterator = vertex.outEdges.iterator();
        //利用迭代器进行删除
        while (outEdgeIterator.hasNext()) {
            Edge<V, E> nextOutEdge = outEdgeIterator.next();
            nextOutEdge.to.inEdges.remove(nextOutEdge);
            outEdgeIterator.remove();
            edges.remove(nextOutEdge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        //if this set contained the specified element return true
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        HashSet<Vertex<V, E>> visitedVertexs = new HashSet<>();
        LinkedList<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        //添加到队列中就视为已访问
        visitedVertexs.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            visitor.visit(vertex.value);
            Set<Edge<V, E>> outEdges = vertex.outEdges;
            for (Edge<V, E> outEdge : outEdges) {
                if (visitedVertexs.contains(outEdge.to)) {
                    continue;
                }
                queue.offer(outEdge.to);
                //添加到队列中就视为已访问
                visitedVertexs.add(outEdge.to);
            }
        }
    }

    @Override
    public void dfs(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        HashSet<Vertex<V, E>> visitedVertexs = new HashSet<>();
        dfs(beginVertex, visitedVertexs, visitor);
    }

    private void dfs(Vertex<V, E> vertex, HashSet<Vertex<V, E>> visitedVertexs, Visitor<V> visitor) {
        visitedVertexs.add(vertex);
        visitor.visit(vertex.value);
        for (Edge<V, E> outEdge : vertex.outEdges) {
            if (visitedVertexs.contains(outEdge.to)) {
                return;
            }
            dfs(outEdge.to, visitedVertexs, visitor);
        }
    }

    @Override
    public void dfsWithoutRecursion(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Stack<Vertex<V, E>> stack = new Stack<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        //处理第一个节点
        stack.push(beginVertex);
        visitor.visit(beginVertex.value);
        visitedVertices.add(beginVertex);
        //处理后续的节点
        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                visitor.visit(edge.to.value);
                visitedVertices.add(edge.to);
                stack.push(edge.from);
                stack.push(edge.to);
                //处理完一条分支后需要跳出循环
                break;
            }
        }
    }

    @Override
    public List<V> topologicSort() {
        //顶点和入度的对应关系
        HashMap<Vertex<V, E>, Integer> inDegrees = new HashMap<>();
        //存放入度为0的顶点
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        //存放拓扑排序的值
        List<V> list = new ArrayList<>();
        //初始化inDegrees
        vertices.values().forEach(vertex -> {
            if (vertex.inEdges.size() == 0) {
                queue.offer(vertex);
            } else {
                inDegrees.put(vertex, vertex.inEdges.size());
            }
        });
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> outEdge : vertex.outEdges) {
                Integer inDegree = inDegrees.get(outEdge.to);
                inDegree--;
                if (inDegree == 0) {
                    queue.add(outEdge.to);
                } else {
                    inDegrees.put(outEdge.to, inDegree);
                }
            }
        }
        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
//        return prim();
        return kruskal();
    }

    /**
     * 利用prim算法得到最小生成树
     * 利用切分定理
     *
     * @return
     */
    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> it = vertices.values().iterator();
        if (!it.hasNext()) {
            return Collections.emptySet();
        }
        //存放最小生成树的边
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //随机获取有个顶点
        Vertex<V, E> vertex = it.next();
        //利用小顶堆来获取最小的边
        PriorityQueue<Edge<V, E>> heap = new PriorityQueue<>(edgeComparator);
        HashSet<Vertex<V, E>> addedVertices = new HashSet<>();
        vertex.outEdges.forEach(edge -> heap.offer(edge));
        addedVertices.add(vertex);
        while (!heap.isEmpty() && addedVertices.size() < this.vertices.size()) {
            Edge<V, E> edge = heap.poll();
            //过滤重复处理的边
            if (addedVertices.contains(edge.to)) continue;
            edgeInfos.add(edge.info());
            //将最小边连接顶点的所有边加入到heap中，需要过滤当前的edge，可以在下一次循环时处理
            edge.to.outEdges.forEach(e -> heap.offer(e));
            addedVertices.add(edge.to);
        }
        return edgeInfos;
    }

    /**
     * 利用kruskal算法得到最小生成树
     * 将边按权值进行排序，依次选出最小边，当从第三条边开始，就要判断是否形成了环，需要对形成环的边进行过滤
     * 是否形成了环可以用并查集来进行判断
     *
     * @return
     */
    private Set<EdgeInfo<V, E>> kruskal() {
        //初始化并查集
        GenericUnionFind<Vertex<V, E>> uf = new GenericUnionFind<>();
        vertices.values().forEach(vertex -> uf.makeSet(vertex));
        //存放最小生成树的边
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        PriorityQueue<Edge<V, E>> heap = new PriorityQueue<>(edgeComparator);
        edges.forEach(e -> heap.add(e));
        while (!heap.isEmpty() && edgeInfos.size() < vertices.size() - 1) {
            Edge<V, E> edge = heap.poll();
            //属于同一个集合  说明形成环了
            if (uf.isSame(edge.from, edge.to)) continue;
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }
        return edgeInfos;
    }


    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V v) {
        return bellmanFord(v);
    }

    /**
     * bellmanFord算法求最短路径(可以有负权值的边)
     * 核心概念：松弛操作更新最短路径的值
     * 对每一条边进行顶点数量-1次松弛操作
     * @param v
     * @return
     */
    private Map<V, PathInfo<V, E>> bellmanFord(V v){
        Vertex<V, E> beginVertex = vertices.get(v);
        if (beginVertex == null) return Collections.emptyMap();
        //顶点v到各个顶点确定的最短距离
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        PathInfo<V, E> beginPath = new PathInfo(weightManager.zero());
        selectedPaths.put(v,beginPath);
        int count = vertices.size() - 1;
        //每条边都进行v-1次松弛
        for (int i = 0; i < count; i++) {
            for (Edge<V,E> edge : edges){
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if(fromPath == null) continue;
                relaxForBellmanFord(edge,fromPath,selectedPaths);
            }
        }
        selectedPaths.remove(v);
        return selectedPaths;
    }
    /**
     * 松弛操作
     * @param edge 松弛的边
     * @param fromPath edge的from顶点的最短路径信息
     * @param paths 顶点v到各个顶点目前的最短距离（中间状态）
     */
    private void relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
        E newWight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWight, oldPath.weight) >= 0) return;
        if (oldPath == null) {
            oldPath = new PathInfo<>(newWight);
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
            oldPath.weight = newWight;
        }
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * dijsktra算法求最短路径(不能有负权值的边)
     * 核心概念：松弛操作更新最短路径的值
     *
     * @param v
     * @return
     */
    private Map<V, PathInfo<V, E>> dijsktra(V v) {
        Vertex<V, E> beginVertex = vertices.get(v);
        if (beginVertex == null) return Collections.emptyMap();
        //顶点v到各个顶点目前的最短距离（中间状态）
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        //顶点v到各个顶点确定的最短距离
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        //初始化paths
        beginVertex.outEdges.forEach(edge -> paths.put(edge.to, new PathInfo<V, E>(edge.weight, edge.info())));
        while (!paths.isEmpty()) {
            //找出最距离
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getShortestPath(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            //最短距离加入到selectedPaths中，并在paths中移除
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);
            //对minVertex的outEdges进行松弛操作(更新paths的最短距离)
            for (Edge<V, E> edge : minVertex.outEdges) {
                //已经确定的最短路径的顶点需要过滤
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relax(edge,minPath,paths);
            }
        }
        selectedPaths.remove(beginVertex);//无向图需要移除beginVertex
        return selectedPaths;
    }

    /**
     * 找出最短的边（可以用小顶堆进行优化）
     * @param paths
     * @return
     */
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getShortestPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> entry = iterator.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    /**
     * 松弛操作
     * @param edge 松弛的边
     * @param fromPath edge的from顶点的最短路径信息
     * @param paths 顶点v到各个顶点目前的最短距离（中间状态）
     */
    private void relax(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        E newWight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = paths.get(edge.to);
        if (oldPath != null && weightManager.compare(newWight, oldPath.weight) >= 0) return;
        if (oldPath == null) {
            oldPath = new PathInfo<>(newWight);
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
            oldPath.weight = newWight;
        }
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * 获取顶点V到各个顶点的最短路径
     *
     * @Override public Map<V, E> shortestPath(V v) {
     * Vertex<V, E> beginVertex = vertices.get(v);
     * if (beginVertex == null) return Collections.emptyMap();
     * //顶点v到各个顶点目前的最短距离（中间状态）
     * HashMap<Vertex<V, E>, E> paths = new HashMap<>();
     * //顶点v到各个顶点确定的最短距离
     * HashMap<V, E> selectedPaths = new HashMap<>();
     * //初始化paths
     * beginVertex.outEdges.forEach(edge -> paths.put(edge.to, edge.weight));
     * while (!paths.isEmpty()) {
     * //找出最距离
     * Map.Entry<Vertex<V, E>, E> minEntry = getShortestPath(paths);
     * Vertex<V, E> minVertex = minEntry.getKey();
     * //最短距离加入到selectedPaths中，并在paths中移除
     * selectedPaths.put(minVertex.value, minEntry.getValue());
     * paths.remove(minVertex);
     * //对minVertex的outEdges进行松弛操作(更新paths的最短距离)
     * for (Edge<V, E> edge : minVertex.outEdges) {
     * //已经确定的最短路径的顶点需要过滤
     * if(selectedPaths.containsKey(edge.to.value)) continue;
     * E newWight = weightManager.add(minEntry.getValue(), edge.weight);
     * E oldWight = paths.get(edge.to);
     * if (oldWight == null || weightManager.compare(newWight, oldWight) < 0) {
     * paths.put(edge.to, newWight);
     * }
     * }
     * }
     * selectedPaths.remove(beginVertex);//无向图需要移除beginVertex
     * return selectedPaths;
     * }
     * <p>
     * private Map.Entry<Vertex<V, E>, E> getShortestPath(HashMap<Vertex<V, E>, E> paths) {
     * Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
     * Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
     * while (iterator.hasNext()) {
     * Map.Entry<Vertex<V, E>, E> entry = iterator.next();
     * if (weightManager.compare(entry.getValue(), minEntry.getValue()) < 0) {
     * minEntry = entry;
     * }
     * }
     * return minEntry;
     * }
     */


    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex<?, ?> vertex = (Vertex<?, ?>) o;

            return value != null ? value.equals(vertex.value) : vertex.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this(from, to, null);
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge<?, ?> edge = (Edge<?, ?>) o;

            if (from != null ? !from.equals(edge.from) : edge.from != null) return false;
            return to != null ? to.equals(edge.to) : edge.to == null;
        }

        @Override
        public int hashCode() {
            int result = from != null ? from.hashCode() : 0;
            result = 31 * result + (to != null ? to.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
