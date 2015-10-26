import  java.util.*;
public class Solution{
    public static void main(String[] args){
        int[] ids = { 1, 2, 3};
        double[] prob = {0.1, 0.3, 0.6};
        for(int i = 0; i < 100; i++){
            System.out.println(new Solution().idGenerator(ids, prob));
        }
    }
    public int idGenerator(int[] ids, double[] prob){
        if(ids == null || prob == null || ids.length != prob.length || ids.length == 0){
            return Integer.MIN_VALUE;
        }
        int len = ids.length;
        double[] sum = new double[len];
        sum[0] = prob[0];
        for(int i = 1; i < len; i++){
            sum[i] = sum[i-1] + prob[i];
        }

        TreeMap<Double, Integer> tm = new TreeMap<Double, Integer>();
        for(int i = 0; i < len; i++){
            tm.put(sum[i], ids[i]);
        }
        double d = Math.random();
        return tm.get(tm.higherKey(d));
    }
}
