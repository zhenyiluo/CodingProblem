import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eric on 2015-11-07.
 */
public class Solution {
    static int m = 52;
    public static void main(String[] args){
        int[] a = new int[m];
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < m; i++){
            list.add(i);
        }

        // generate a deck of cards.
        int max = m;
        for(int i = 0; i < m; ++i, --max){
            int index = (int)(Math.random() * max);
            a[i] = list.remove(index);
        }


        for(int i = 0; i < m; i++){
            System.out.print(a[i] + " ");
        }

        String s = encode(a);
        System.out.println();

        System.out.println("s.length == " + s.length());

        int[] b = decode(s);

        for(int i = 0; i < m; i++){
            System.out.print(b[i] + " ");
        }
    }

    private static String encode(int[] a){
        BigInteger big = new BigInteger("0");
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < m; i++){
            list.add(i);
        }

        int max = m;
        for(int i = 0; i < m; i++, --max){
            big = big.multiply(new BigInteger(String.valueOf(max)));
            big = big.add(new BigInteger(String.valueOf(list.indexOf(a[i]))));
            list.remove(list.indexOf(a[i]));
        }
        return big.toString(2);
    }

    private static int[] decode(String s){
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < m; i++){
            list.add(i);
        }
        BigInteger big = new BigInteger(String.valueOf(s), 2);
        int[] index = new int[m];
        index[m-1] = 0;
        for(int i = 2; i <= m; i++){
            index[m-i] = big.mod(new BigInteger(String.valueOf(i))).intValue();
            big = big.divide(new BigInteger(String.valueOf(i)));
        }
        int[] ret = new int[m];
        for (int i= 0; i < m; i++){
            ret[i] = list.remove(index[i]);
        }
        return ret;
    }
}