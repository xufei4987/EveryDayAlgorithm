package easy;

/**
 * Level: Easy Tags: [Bit Manipulation]
 * 把Integer A 转换成 Integer B 需要改变多少bits?
 * Example
 * Given n = 31, m = 14,return 2
 * (31)10=(11111)2
 * (14)10=(01110)2
 */
public class Convert_Integer_A_to_Integer_B {
    public static int diffBetweenIntegers(int a, int b){
        int count = 0;
        //按bit异或
        int c = a ^ b;
        for(int i = 0; i < 32; i++){
            count += (c >> i) & 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(diffBetweenIntegers(31, 14));
    }
}
