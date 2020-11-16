package algorithm1.string;

/**
 * 暴力匹配
 */
public class BruteForce {
    public static void main(String[] args) {
        System.out.println(indexOf2("abcba","bc"));
    }

    /**
     * 返回匹配串在字符串中的起始位置
     * @param text
     * @param pattern
     * @return
     */
    public static int indexOf2(String text,String pattern){
        if (text == null || text.length() < 1) return -1;
        if (pattern == null || pattern.length() < 1) return -1;
        char[] texts = text.toCharArray();
        char[] patterns = pattern.toCharArray();
        if (text.length() < pattern.length()) return -1;
        int tiMax = texts.length - patterns.length;
        for (int ti = 0; ti < tiMax; ti++) {
            int pi = 0;
            for (; pi < patterns.length; pi++) {
                if(texts[ti + pi] != patterns[pi]) break;

            }
            if (pi == patterns.length) return ti;
        }
        return -1;
    }

    /**
     * 返回匹配串在字符串中的起始位置
     * @param text
     * @param pattern
     * @return
     */
    public static int indexOf1(String text,String pattern){
        if (text == null || text.length() < 1) return -1;
        if (pattern == null || pattern.length() < 1) return -1;
        char[] texts = text.toCharArray();
        char[] patterns = pattern.toCharArray();
        if (text.length() < pattern.length()) return -1;
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            int ti = i;
            for (int j = 0; j < pattern.length(); j++){
                if(texts[ti] == patterns[j]){
                    ti += 1;
                } else {
                    break;
                }
            }
            if(ti - i == patterns.length){
                return i;
            }
        }
        return -1;
    }


}
