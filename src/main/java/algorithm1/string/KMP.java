package algorithm1.string;

public class KMP {
    public static void main(String[] args) {
        System.out.println(indexOf("abcefgabcdabc","abcdabc"));
    }

    public static int indexOf(String text, String pattern) {
        if (text == null || text.length() < 1) return -1;
        if (pattern == null || pattern.length() < 1) return -1;
        char[] texts = text.toCharArray();
        char[] patterns = pattern.toCharArray();
        if (text.length() < pattern.length()) return -1;
        int pi = 0;
        int ti = 0;
        int tiMax = texts.length - patterns.length;
        int[] next = next(pattern);
        while (pi < patterns.length && ti - pi <= tiMax) {
            if (pi < 0 || texts[ti] == patterns[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return pi == patterns.length ? ti - pi : -1;
    }

    private static int[] next(String pattern) {
        int plen = pattern.length();
        int[] next = new int[plen + 1];
        next[0] = -1;
        int i = 0;
        int n = -1;
        while (i < plen) {
            if (n < 0 || pattern.charAt(n) == pattern.charAt(i)) {
                n++;
                i++;
                next[i] = n;
            } else {
                n = next[n];
            }
        }
        return next;
    }
}
