import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        System.out.println(new Solution().calculate(s));
    }
    public int calculate(String s) {
        if(s == null) {
            return 0;
        }

        s = s.trim();
        if(s.length() == 0) {
            return 0;
        }

        int len = s.length();

        Stack<Integer> stNum = new Stack<>();
        Stack<Character> stChar = new Stack<>();
        int cur = 0;
        while (cur < len) {
            char c = s.charAt(cur);
            if (isDigit(c)) {
                int j = cur;
                while(j < len && isDigit(s.charAt(j))) {
                    j++;
                }
                String sub = s.substring(cur, j);
                int num = Integer.valueOf(sub);
                stNum.push(num);
                cur = j;
            } else if("(+-".indexOf(c) != -1) {
                if (c != '(') {
                    if(!stChar.isEmpty() && stChar.peek() != '(') {
                        char tmp = stChar.pop();
                        int val2 = stNum.pop();
                        int val1 = stNum.pop();
                        int val = calc(tmp, val1, val2);
                        stNum.push(val);
                    }
                }
                stChar.push(c);
                cur ++;
            } else if(c == ')') {
                while(!stChar.isEmpty() && stChar.peek() != '(') {
                    char tmp = stChar.pop();
                    int val2 = stNum.pop();
                    int val1 = stNum.pop();
                    int val = calc(tmp, val1, val2);
                    stNum.push(val);
                }
                stChar.pop();
                cur ++;
            } else{
                cur ++;
            }
        }

        while(!stChar.isEmpty()) {
            char tmp = stChar.pop();
            int val2 = stNum.pop();
            int val1 = stNum.pop();
            int val = calc(tmp, val1, val2);
            stNum.push(val);
        }

        return stNum.peek();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private int calc(char c, int val1, int val2) {
        if (c == '+') {
            return val1 + val2;
        } else {
            return val1 - val2;
        }
    }
}