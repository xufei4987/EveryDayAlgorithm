package datastructure.union;

/**
 * quick union(基于rank进行优化(树的高度)，合并时，rank小的合并到rank大的)
 * 路径分裂（path spliting）
 */
public class UnionFind_QU_R_PS extends UnionFind_QU_R {

    public UnionFind_QU_R_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int p = parents[v];//保存父节点
            parents[v] = parents[parents[v]];//指向祖父节点
            v = p;
        }
        return v;
    }


    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind_QU_R_PS(12);
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
