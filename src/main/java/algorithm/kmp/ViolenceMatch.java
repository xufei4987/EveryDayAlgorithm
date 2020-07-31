package algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "abcdeabcabcabcdefg";
        String s2 = "abcdef";
        System.out.println(violenceMatch(s1, s2));
    }

    /**
     * 找到S2在S1中匹配的字符串并返回在S1中的下标
     * 暴力匹配算法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int violenceMatch(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int i1 = 0;
        int i2 = 0;

        while (i1 < chars1.length && i2 < chars2.length) {
            if (chars1[i1] == chars2[i2]) {
                i1++;
                i2++;
            } else {
                i1 = i1 - i2 + 1;
                i2 = 0;
            }
        }
        return i2 == chars2.length ? i1 - i2 : -1;
    }
}
