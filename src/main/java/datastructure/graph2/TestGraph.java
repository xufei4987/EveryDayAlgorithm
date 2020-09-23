package datastructure.graph2;

import java.util.List;
import java.util.Set;

public class TestGraph {
    public static Graph.WeightManager<Integer> weightManager = new Graph.WeightManager<Integer>() {
        @Override
        public int compare(Integer w1, Integer w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Integer add(Integer w1, Integer w2) {
            return w1 + w2;
        }
    };
    public static void main(String[] args) {
//        test1();
//        test2();
//        testTopologicSort();
        testMst();
    }

    private static void test1(){
        ListGraph<String, Integer> graph = new ListGraph<>(weightManager);
        graph.addEdge("V0","V4",6);
        graph.addEdge("V1","V0",9);
        graph.addEdge("V1","V2",3);
        graph.addEdge("V2","V0",2);
        graph.addEdge("V2","V3",5);
        graph.addEdge("V3","V4",1);
        graph.print();
        System.out.println("----------remove edge-----------");
        graph.removeEdge("V0","V4");
        graph.print();
        System.out.println("----------remove vertex-----------");
        graph.removeVertex("V2");
        graph.print();
    }

    private static void test2(){
        ListGraph<String, Integer> graph = new ListGraph<>(weightManager);
        graph.addEdge("V0","V4",6);
        graph.addEdge("V1","V0",9);
        graph.addEdge("V1","V2",3);
        graph.addEdge("V2","V0",2);
        graph.addEdge("V2","V3",5);
        graph.addEdge("V3","V4",1);
        System.out.println("-------bfs-----");
        graph.bfs("V1", System.out::println);
        System.out.println("-------dfs1-----");
        graph.dfs("V1", System.out::println);
        System.out.println("-------dfs2-----");
        graph.dfsWithoutRecursion("V1", System.out::println);
    }

    private static void testTopologicSort(){
        ListGraph<String, Integer> graph = new ListGraph<>(weightManager);
        graph.addEdge("0","2");
        graph.addEdge("2","5");
        graph.addEdge("2","6");
        graph.addEdge("5","7");
        graph.addEdge("6","4");
        graph.addEdge("7","6");
        graph.addEdge("1","0");
        graph.addEdge("3","1");
        graph.addEdge("3","5");
        graph.addEdge("3","7");
        List<String> list = graph.topologicSort();
        System.out.println(list);
    }

    /**
     * EdgeInfo{from=5, to=6, wight=4}
     * EdgeInfo{from=2, to=5, wight=3}
     * EdgeInfo{from=0, to=2, wight=2}
     * EdgeInfo{from=5, to=7, wight=5}
     * EdgeInfo{from=2, to=4, wight=4}
     * EdgeInfo{from=5, to=1, wight=1}
     * EdgeInfo{from=7, to=3, wight=9}
     */
    private static void testMst(){
        ListGraph<String, Integer> graph = new ListGraph<>(weightManager);
        graph.addEdge("0","2",2);
        graph.addEdge("2","0",2);
        graph.addEdge("0","4",7);
        graph.addEdge("4","0",7);
        graph.addEdge("1","2",3);
        graph.addEdge("2","1",3);
        graph.addEdge("1","5",1);
        graph.addEdge("5","1",1);
        graph.addEdge("1","6",7);
        graph.addEdge("6","1",7);
        graph.addEdge("2","4",4);
        graph.addEdge("4","2",4);
        graph.addEdge("2","5",3);
        graph.addEdge("5","2",3);
        graph.addEdge("2","6",6);
        graph.addEdge("6","2",6);
        graph.addEdge("3","7",9);
        graph.addEdge("7","3",9);
        graph.addEdge("4","6",8);
        graph.addEdge("6","4",8);
        graph.addEdge("5","6",4);
        graph.addEdge("6","5",4);
        graph.addEdge("5","7",5);
        graph.addEdge("7","5",5);
        Set<Graph.EdgeInfo<String, Integer>> mst = graph.mst();
        mst.forEach(System.out::println);
    }
}
