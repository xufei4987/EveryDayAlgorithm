package easy;

/**
 * 求2个超大数相乘的结果（100位 * 100位）
 */
public class BigNumberMultiplication {
    public static void main(String[] args) {
        System.out.println(bigNumMul("123", "456"));
    }

    public static String bigNumMul(String num1, String num2){
        int l1 = num1.length();
        int l2 = num2.length();
        int[] result = new int[l1 + l2];
        for (int i = l1-1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = l2-1; j >= 0; j--){
                int n2 = num2.charAt(j) - '0';
                //进位后面统一处理
                result[(l1-1-i) + (l2-1-j)] = n1 * n2;
            }
        }
        for (int i = 0; i < result.length; i++){
            if(result[i] > 9){
                result[i+1] += result[i]/10;
                result[i] = result[i]%10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = result.length - 1; i >= 0; i--) {
            if(i == result.length - 1 && result[i] == 0) continue;
            sb.append(String.valueOf(result[i]));
        }
        return sb.toString();
    }
}
