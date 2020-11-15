package algorithm.kmp;

/**
 * KMP算法详解视频
 * https://www.bilibili.com/video/BV1S64y1u74P?t=2464
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String longStr = "BBC ABCDAB ABCDABCDABDE";
        String shortStr = "ABCDABD";
        System.out.println(indexKmp(longStr,shortStr));
    }

    public static int indexKmp(String longStr, String shortStr) {
        //生成短串的next表
        int[] next = kmpNext(shortStr);
        char[] longChars = longStr.toCharArray();
        char[] shortChars = shortStr.toCharArray();
        int l = 0;
        int s = 0;
        while (l < longStr.length() && s < shortStr.length()) {
            // s == -1 是为了防止死循环 人为设置了next[0] = -1
            if (s == -1 || longChars[l] == shortChars[s]) {
                l++;
                s++;
            } else {
                //发生失配时，s需要回溯到当前字符前的字符串的最长的相等的真前缀和真后缀 也就是next[s]
                s = next[s];
            }
        }
        return s == shortChars.length ? l - s : -1;
    }

    /**
     * 获取一个字符串的部分匹配值表
     *      0  1  2  3  4  5  6
     *      A  B  C  D  A  B  D
     * next -1 0  0  0  0  1  2
     * @param shortStr
     * @return
     */
    public static int[] kmpNext(String shortStr) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[shortStr.length()];
        //s的第一个字符的部分匹配值为-1
        next[0] = -1;
        char[] chars = shortStr.toCharArray();
        int l = 0;
        int s = -1;
        while (l < shortStr.length() - 1){
            if(s == -1 || chars[l] == chars[s]){
                s++;
                l++;
                next[l] = next[l - 1] + 1; //或者写为next[l] = s
            } else {
                s = next[s];
            }
        }
        return next;
    }


}
