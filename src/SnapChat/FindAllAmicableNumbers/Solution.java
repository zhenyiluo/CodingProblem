import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        solve(n);
        sc.close();
    }
    
    private static void solve(int n) {
        for(int i = 2; i <= n; i++){
            int other = getDivisorsSum(i);
            int sum = getDivisorsSum(other);
            if(i == sum && i < other) {
                System.out.println(i + " " + other);
            }
        }
    }
    
    private static int getDivisorsSum(int n) {
        HashSet<Integer> hs = new HashSet<>();
        hs.add(1);
        for(int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                hs.add(i);
                hs.add(n / i);
            }
        }
        int sum = 0;
        for(int val : hs) {
            sum += val;
        }
        return sum;
    }
}
