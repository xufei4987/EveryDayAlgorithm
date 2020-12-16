package basic;

import java.math.BigDecimal;

public class Number {
    public static void main(String[] args) {
        compare();
        scale();
    }

    /**
     * BigDecimal比较大小
     */
    private static void compare() {
        double a = 1.0d - 0.9d;
        double b = 1.2d - 1.1d;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == b);
        System.out.println("--------------");
        BigDecimal bigDecimal_a = new BigDecimal("0.9");
        BigDecimal bigDecimal_b = new BigDecimal("1.0");
        BigDecimal bigDecimal_c = new BigDecimal("1.1");
        BigDecimal x = bigDecimal_c.subtract(bigDecimal_b);
        BigDecimal y = bigDecimal_b.subtract(bigDecimal_a);
        System.out.println(x);
        System.out.println(y);
        System.out.println(x.compareTo(y) == 0);
        System.out.println("--------------");
    }
    /**
     * 保留几位小数
     */
    private static void scale() {
        BigDecimal a = new BigDecimal("1.12351");
        System.out.println(a.setScale(3,BigDecimal.ROUND_HALF_DOWN));
        System.out.println("--------------");
    }
}
