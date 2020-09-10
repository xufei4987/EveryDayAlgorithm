package algorithm1.sorting;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        Integer[] arr = IntergerUtils.random(10000, 1, 1000000);
        testSort(arr,new BubbleSort(),new SelectionSort());
    }

    public static void testSort(Integer[] arr, Sort... sort){
        for (int i = 0; i < sort.length; i++) {
            sort[i].sort(IntergerUtils.arrayCopy(arr));
        }
        Arrays.sort(sort);
        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }
}
