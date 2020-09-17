package datastructure.graph2;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V,E>> edges = new HashSet<>();

    public void print(){
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
            vertices.put(from,fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to,toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, wight);
        //删除 再添加 就是更新
        if(fromVertex.outEdges.remove(edge)){
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
        while (inEdgeIterator.hasNext()){
            Edge<V, E> nextInEdge = inEdgeIterator.next();
            nextInEdge.from.outEdges.remove(nextInEdge);
            inEdgeIterator.remove();
            edges.remove(nextInEdge);
        }
        //删除从该顶点出去的边
        Iterator<Edge<V, E>> outEdgeIterator = vertex.outEdges.iterator();
        //利用迭代器进行删除
        while (outEdgeIterator.hasNext()){
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
        if (fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

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
            this(from,to,null);
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
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
