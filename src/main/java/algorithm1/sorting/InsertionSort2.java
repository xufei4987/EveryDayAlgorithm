package algorithm1.sorting;

/**
 * 插入排序2
 *
 * @param <E>
 */
public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int cur = i;
            E curVal = array[i];
            while (cur > 0 && compare(array[cur - 1], curVal) > 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = curVal;
        }
    }
}
