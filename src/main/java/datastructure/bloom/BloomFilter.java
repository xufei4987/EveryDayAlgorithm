package datastructure.bloom;

/**
 * 通过数据规模n和期望达到的误判率p 可以通过公式求出布隆过滤器的二进制长度和哈希函数的个数
 *
 * @param <T>
 */
public class BloomFilter<T> {
    /**
     * 二进制位的长度
     */
    private int bitSize;

    /**
     * 二进制向量
     * 一个long可以表示8 * 8个二进制向量
     */
    private long[] bits;

    /**
     * 哈希函数的个数
     */
    private int hashSize;

    /**
     * @param n 数据的规模
     * @param p 误判率 取值范围（0,1）
     */
    public BloomFilter(int n, double p) {
        if (n < 1 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("不合法的参数");
        }
        double ln2 = Math.log(2);
        //通过公式求出布隆过滤器的二进制长度和哈希函数的个数
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        hashSize = (int) (bitSize * ln2 / n);
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
        System.out.println("二进制位的长度:" + bitSize);
        System.out.println("哈希函数的个数:" + hashSize);
        System.out.println("bits数组的长度:" + bits.length);
    }

    public void put(T value){
        if (value == null) {
            throw new IllegalArgumentException("不合法的参数");
        }
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if(combinedHash < 0){
                combinedHash = ~combinedHash;
            }
            setBit(combinedHash % bitSize);
        }
    }

    public boolean contains(T value){
        if (value == null) {
            throw new IllegalArgumentException("不合法的参数");
        }
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if(combinedHash < 0){
                combinedHash = ~combinedHash;
            }
            //任意一个索引位置上为0，代表这个值在bloom过滤器中一定不存在
            if(!getBit(combinedHash % bitSize)){
                return false;
            }
        }
        //都为1则表示可能存在
        return true;
    }

    private void setBit(int idx) {
        long value = bits[idx / Long.SIZE];
        int innerIdx = 1 << (idx % Long.SIZE);
        bits[idx / Long.SIZE] = value | innerIdx;
    }

    private boolean getBit(int idx) {
        long value = bits[idx / Long.SIZE];
        int innerIdx = 1 << (idx % Long.SIZE);
        return (innerIdx & value) == innerIdx;
    }

    public static void main(String[] args) {
        BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.01);
        for (int i = 1; i <= 10_0000; i++){
            bf.put(i);
        }
        int errorCount = 0;
        for (int i = 10_0001; i <= 20_0000; i++){
            if (bf.contains(i)) {
                errorCount++;
            }
        }
        System.out.println(errorCount);
    }
}
