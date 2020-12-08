package sfmatch;

import java.util.*;

public class T9 {
    static Map<Character, String> keyMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        if (n <= 0) return;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            String wp = scanner.nextLine();
            String[] wps = wp.split(" ");
            map.put(wps[0],Integer.valueOf(wps[1]));
        }
        int m = Integer.valueOf(scanner.nextLine());
        String[] lines = new String[m];
        for (int i = 0; i < m; i++){
            lines[i] = scanner.nextLine();
        }

        for (String line : lines){
            for (int i = 1; i < line.length(); i++){
                String letter = line.substring(0,i);
                List<String> allLetters = allLetters(letter);
                String ans = getMostProbability(allLetters,map);
                System.out.println(ans);
            }
            System.out.println();
        }
    }

    private static String getMostProbability(List<String> allLetters, HashMap<String, Integer> map) {
        HashMap<String, Integer> prefixAndfMap = new HashMap<>();
        for (String letter : allLetters){
            for (Map.Entry entry : map.entrySet()){
                String world = (String) entry.getKey();
                Integer frequence = (Integer) entry.getValue();
                if (world.startsWith(letter)){
                    Integer f = prefixAndfMap.get(letter);
                    if(f == null || f < frequence){
                        prefixAndfMap.put(letter,frequence);
                    }
                }
            }
        }
        String maxFrequence = "MANUALLY";
        int max = 0;
        for (Map.Entry<String, Integer> entry : prefixAndfMap.entrySet()) {
            if (entry.getValue() > max){
                max = entry.getValue();
                maxFrequence = entry.getKey();
            } else if(entry.getValue() == max){
                if (maxFrequence.compareTo(entry.getKey())>0){
                    maxFrequence = entry.getKey();
                }
            }
        }
        return maxFrequence;
    }


    public static List<String> allLetters(String digits) {
        if (digits == null) return null;
        List<String> strings = new ArrayList<>();
        char[] numbers = digits.toCharArray();
        if (numbers.length == 0) return strings;
        char[] cs = new char[numbers.length];
        dfs(0, numbers, cs, strings);
        return strings;
    }

    private static void dfs(int idx, char[] numbers, char[] cs, List<String> strings) {
        if (idx == numbers.length) {
            strings.add(new String(cs));
            return;
        }
        String s = keyMap.get(numbers[idx]);
        for (int i = 0; i < s.length(); i++) {
            cs[idx] = s.charAt(i);
            dfs(idx + 1, numbers, cs, strings);
        }
    }
}
