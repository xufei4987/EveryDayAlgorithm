package datastructure.union;

/**
 * quick union(基于size进行优化，合并时，size小的合并到size大的)
 */
public class UnionFind_QU_S extends UnionFind {

    private int[] sizes;

    public UnionFind_QU_S(int capacity) {
        super(capacity);
        sizes = new int[parents.length];
        for (int i = 0; i < parents.length; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    //将v1的根节点的父节点指向v2的根节点
    @Override
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) return;
        if (sizes[root1] > sizes[root2]) {
            parents[root2] = root1;
            sizes[root1] += sizes[root2];
        } else {
            parents[root1] = root2;
            sizes[root2] += sizes[root1];
        }

    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind_QU_S(12);
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
