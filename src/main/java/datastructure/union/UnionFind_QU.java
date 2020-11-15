package datastructure.union;

/**
 * quick union
 */
public class UnionFind_QU extends UnionFind {

    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]){
            v = parents[v];
        }
        return v;
    }

    //将v1的根节点的父节点指向v2的根节点
    @Override
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if(root1 == root2) return;
        parents[root1] = root2;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind_QU(12);
        unionFind.union(1,0);
        unionFind.union(1,4);
        unionFind.union(1,3);
        unionFind.union(2,3);
        unionFind.union(5,3);

        unionFind.union(6,7);

        unionFind.union(9,10);
        unionFind.union(8,10);
        unionFind.union(9,11);

        System.out.println(unionFind.isSame(0,6));
        System.out.println(unionFind.isSame(0,5));
    }
}
