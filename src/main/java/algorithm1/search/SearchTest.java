package algorithm1.search;

public class SearchTest {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,4,4,7,8,9};
        System.out.println(BinarySearch.indexOf(arr,1));
        System.out.println(BinarySearch.indexOf(arr,0));
        System.out.println(BinarySearch.searchInsertIndex(arr,10));
    }
}
