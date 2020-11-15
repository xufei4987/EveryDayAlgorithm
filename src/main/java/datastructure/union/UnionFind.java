package datastructure.union;

public abstract class UnionFind {
    protected int[] parents;

    protected UnionFind(int capacity) {
        if(capacity <= 0){
            throw new IllegalArgumentException("capacity must >= 1");
        }
        this.parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查询v所属的集合（父节点）
     * @param v
     * @return
     */
    public abstract int find(int v);

    protected void rangeCheck(int v) {
        if(v < 0 || v >= parents.length){
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

    /**
     * 将2个节点合并到同一个集合（将v1合并到v2的集合）
     * @param v1
     * @param v2
     */
    public abstract void union(int v1, int v2);

    /**
     * 检测v1,v2是否属于同一个集合
     * @return
     */
    public boolean isSame(int v1, int v2){
        return find(v1) == find(v2);
    }
}
