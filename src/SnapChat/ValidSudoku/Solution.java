public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    String tmp = "(" + board[i][j] + ")";
                    if(!set.add(i + tmp) || !set.add(tmp + j) || !set.add(i/3 + tmp + j/3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}