package algorithm1.sorting;

public class IntergerUtils {
    public static Integer[] random(int count, int min, int max){
        if(count <= 0 || min > max){
            return null;
        }
        Integer[] integers = new Integer[count];
        int delta = max - min + 1;
        for (int i = 0; i < count; i++){
            integers[i] = (int)(Math.random() * delta) + min;
        }
        return integers;
    }

    public static Integer[] arrayCopy(Integer[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        Integer[] integers = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            integers[i] = arr[i];
        }
        return integers;
    }

}
