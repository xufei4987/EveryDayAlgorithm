package datastructure.union;

/**
 * quick find
 */
public class UnionFind_QF extends UnionFind {

    public UnionFind_QF(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    //将v1与其兄弟节点的父节点都指向v2
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) {
            return;
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == parent1) {
                parents[i] = parent2;
            }
        }
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind_QF(12);
        unionFind.union(1, 0);
        unionFind.union(1, 4);
        unionFind.union(1, 3);
        unionFind.union(2, 3);
        unionFind.union(5, 3);

        unionFind.union(6, 7);

        unionFind.union(9, 10);
        unionFind.union(8, 10);
        unionFind.union(9, 11);

        System.out.println(unionFind.isSame(0, 6));
        System.out.println(unionFind.isSame(0, 5));
    }
}
