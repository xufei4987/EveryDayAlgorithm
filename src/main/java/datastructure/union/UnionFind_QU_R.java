package datastructure.union;

/**
 * quick union(基于rank进行优化(树的高度)，合并时，rank小的合并到rank大的)
 */
public class UnionFind_QU_R extends UnionFind {

    private int[] ranks;

    public UnionFind_QU_R(int capacity) {
        super(capacity);
        ranks = new int[parents.length];
        for (int i = 0; i < parents.length; i++) {
            ranks[i] = 1;
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
        if (ranks[root1] > ranks[root2]) {
            parents[root2] = root1;
        } else if(ranks[root1] < ranks[root2]){
            parents[root1] = root2;
        } else {
            parents[root2] = root1;
            ranks[root1] += 1;
        }

    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind_QU_R(12);
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
