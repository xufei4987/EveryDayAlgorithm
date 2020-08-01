package algorithm.greedy;

import java.util.*;

/**
 * 贪心算法：
 * 每步选取最优的策略，但不一定是全局最优
 * 通过贪心算法解决集合覆盖问题 
 */
public class SetCoveringProblem {
    public static void main(String[] args) {
        HashMap<String, Set<String>> map = new HashMap<>();
        HashSet<String> s1 = new HashSet<>();
        s1.add("北京");
        s1.add("上海");
        s1.add("天津");
        map.put("k1",s1);
        HashSet<String> s2 = new HashSet<>();
        s2.add("广州");
        s2.add("北京");
        s2.add("深圳");
        map.put("k2",s2);
        HashSet<String> s3 = new HashSet<>();
        s3.add("成都");
        s3.add("上海");
        s3.add("杭州");
        map.put("k3",s3);
        HashSet<String> s4 = new HashSet<>();
        s4.add("上海");
        s4.add("天津");
        map.put("k4",s4);
        HashSet<String> s5 = new HashSet<>();
        s5.add("杭州");
        s5.add("大连");
        map.put("k5",s5);

        Set<String> allCity = new HashSet<>();
        allCity.addAll(s1);
        allCity.addAll(s2);
        allCity.addAll(s3);
        allCity.addAll(s4);
        allCity.addAll(s5);

        HashSet<String> keySet = new HashSet<>();
        Set<String> citys;
        Set<String> curMaxCitySet;
        String maxKey;
        while (allCity.size() != 0){
            curMaxCitySet = null;
            maxKey = null;
            for (String key : map.keySet()){
                citys = map.get(key);
                citys.retainAll(allCity);
                if(maxKey == null || curMaxCitySet == null || citys.size() > curMaxCitySet.size()){
                    curMaxCitySet = citys;
                    maxKey = key;
                }
            }
            if(maxKey != null){
                keySet.add(maxKey);
                allCity.removeAll(curMaxCitySet);
            }
        }

        System.out.println(keySet);

    }
}
