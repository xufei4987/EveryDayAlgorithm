package algorithm1.sorting;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        Integer[] arr = IntergerUtils.random(15000, 1, 1000000);
        testSort(arr,
                new BubbleSort(),
                new SelectionSort(),
                new HeapSort(),
                new InsertionSort1(),
                new InsertionSort2(),
                new InsertionSort3(),
                new MergeSort());
    }

    public static void testSort(Integer[] arr, Sort... sort){
        for (int i = 0; i < sort.length; i++) {
            Integer[] arrayCopy = IntergerUtils.arrayCopy(arr);
            sort[i].sort(arrayCopy);
            if(!IntergerUtils.isAscOrder(arrayCopy)){
                throw new RuntimeException("测试不通过!");
            }
        }
        Arrays.sort(sort);
        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }
}
