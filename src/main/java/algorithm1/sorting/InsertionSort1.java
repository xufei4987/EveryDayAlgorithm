package algorithm1.sorting;

/**
 * 插入排序1
 * @param <E>
 */
public class InsertionSort1<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int cur = i;
            while (cur > 0 && compare(cur-1,cur) > 0){
                swap(cur-1, cur);
                cur--;
            }
        }
    }
}
