package algorithm1.sorting;

public class BubbleSort extends Sort {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            //初始值为1是为了解决一开始数据就有序的问题
            int lastSwapIdx = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin - 1, begin) > 0) {
                    swap(begin, begin - 1);
                    //记录最后一次交换的index，表示后面的数据都有序了
                    lastSwapIdx = begin;
                }
            }
            end = lastSwapIdx;
        }
    }

//    private static void bubbleSort1(Integer[] arr) {
//        boolean swaped = false;
//        for (int end = arr.length - 1; end > 0; end--) {
//            swaped = false;
//            for (int begin = 1; begin <= end; begin++) {
//                if (arr[begin - 1] > arr[begin]) {
//                    IntergerUtils.swap(arr, begin, begin - 1);
//                    swaped = true;
//                }
//            }
//            if (!swaped) break;
//        }
//    }
}
