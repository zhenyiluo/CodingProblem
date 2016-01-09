import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int[] a = new int[N];
            for(int j = 0; j < N; j++){
                a[j] = sc.nextInt();
            }
            System.out.println(solve(a));
        }
    }
    
    private static long solve(int[] a){
        if(a.length == 1){
            return 0;
        }
        int len = a.length;
        int[] max = new int[len];
        max[len-1] = a[len -1];
        for(int i = len-2; i>= 0; i--){
            max[i] = Math.max(max[i+1], a[i]);
        }
        long profit = 0;
        for(int i = 0; i < len; i++){
            profit += max[i] - a[i];
        }
        return profit;
    }
}