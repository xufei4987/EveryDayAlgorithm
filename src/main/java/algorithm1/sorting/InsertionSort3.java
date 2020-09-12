package algorithm1.sorting;

/**
 * 插入排序3
 * 利用二分查找进行优化，减少比较的次数
 * @param <E>
 */
public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            E val = array[begin];
            int index = searchInsertIndex(begin);
            for (int i = begin; i > index; i--){
                array[i] = array[i - 1];
            }
            array[index] = val;
        }
    }

    /**
     * 查询元素value在有序数组arr中的插入位置，插入规则为插入到第一个比value大的元素的位置，如果value为最大，插入到末尾
     * @param index 需要插入有序数组中的元素的索引
     * @return 插入位置
     */
    private int searchInsertIndex(int index) {
        int begin = 0;
        int end = index;
        while (begin < end){
            int mid = (begin + end) >> 1;
            if(compare(array[index],array[mid]) < 0){
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
