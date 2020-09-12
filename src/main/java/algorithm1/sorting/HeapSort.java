package algorithm1.sorting;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        //原地建堆(自下而上的下滤)，从最后一个非叶子节点开始下滤
        int i = (heapSize >> 1) - 1;
        while (i >= 0) {
            siftDown(i);
            i--;
        }
        while (heapSize > 1) {
            //交换堆顶和堆尾元素
            swap(0, heapSize - 1);
            //排除堆尾元素
            heapSize--;
            //下滤堆顶元素
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        E e = array[index];
        //有子节点（第一个叶子节点的index = size / 2）
        while ((index << 1) + 1 < heapSize) {
            int childIdx = (index << 1) + 1;
            E child = array[childIdx];
            int rightChildIdx = childIdx + 1;
            //选出子节点中最大的一个
            if (rightChildIdx < heapSize && compare(array[rightChildIdx], array[childIdx]) > 0) {
                child = array[rightChildIdx];
                childIdx = rightChildIdx;
            }
            if (compare(e, child) >= 0) break;
            array[index] = child;
            index = childIdx;
        }
        array[index] = e;
    }

    @Override
    protected boolean isStable() {
        return false;
    }
}
