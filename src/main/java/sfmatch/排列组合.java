package sfmatch;

import java.util.HashMap;
import java.util.Scanner;

public class 排列组合 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        HashMap<String, Integer> map = new HashMap<>();
        int c = 0;
        while (true){
            String in = scanner.nextLine();
            if (in.equals("Q")) break;
            String[] ins = in.split(" ");
            String color = ins[0];
            int count = Integer.parseInt(ins[1]);
            map.put(color,count);
            c += count;
        }
        if (c + map.size() - 1 > n){
            System.out.print(0);
            return;
        } else if(c + map.size() - 1 == n){
            System.out.print(1);
            return;
        } else {
            int base = map.size() + (n - c - map.size() + 1);
            int var = map.size();
            System.out.println(permutation(var,base));
        }
    }


    private static int permutation(int k, int n) {
        int ans = 1;
        for(int i = 1; i <= k; i++) {
            ans *= (n - i + 1);
        }
        return ans;
    }
}
