public class Solution {
    public int totalNQueens(int n) {
        if(n <= 0){
            return 0;
        }
        int[] ret = new int[1];
        int[] a = new int[n];
        dfs(0, n, a, ret);
        return ret[0];
    }
    
    private void dfs(int row, int n, int[] a, int[] ret){
        if(row == n){
            ret[0] ++;
            return;
        }
        
        for(int i = 0; i < n; i++){
            a[row] = i;
            if(check(row, a)){
                dfs(row+1, n, a, ret);
            }
        }
    }
    
    private boolean check(int row, int[] a){
        for(int i = 0; i < row; i++){
            if(a[i] == a[row] || Math.abs(a[i] - a[row]) == Math.abs(i - row)){
                return false;
            }
        }
        return true;
    }
}