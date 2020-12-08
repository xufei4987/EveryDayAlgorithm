package sfmatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class 部落 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        boolean[] bs = new boolean[n + 1];
        ArrayList<HashSet<Integer>> sets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String ns = scanner.nextLine();
            String[] nss = ns.split(" ");
            int v1 = Integer.parseInt(nss[0]);
            int v2 = Integer.parseInt(nss[1]);
            boolean added = false;
            for (HashSet<Integer> set : sets){
                if (set.contains(v1)) {
                    set.add(v2);
                    bs[v2] = true;
                    added = true;
                }
                if (set.contains(v2)){
                    set.add(v1);
                    added = true;
                    bs[v1] = true;
                }
            }
            if (!added){
                HashSet<Integer> set = new HashSet<>();
                set.add(v1);
                set.add(v2);
                sets.add(set);
                bs[v1] = true;
                bs[v2] = true;
            }
        }
        HashSet<Integer> lastSet = new HashSet<>();
        for (int i = 1; i < bs.length; i++) {
            if (bs[i]) continue;
            lastSet.add(i);
        }
        if (!lastSet.isEmpty()){
            sets.add(lastSet);
        }
        int count = sets.size();
        int max = 0;
        for (HashSet<Integer> set : sets){
            max = Math.max(max,set.size());
        }
        System.out.print(count + " " + max);
    }

}
