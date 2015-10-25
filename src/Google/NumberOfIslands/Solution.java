public class Solution {
    int m;
    int n;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int ret = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    ret++;
                    visited[i][j] = true;
                    dfs(i, j, grid, visited);
                }
            }
        }

        return ret;
    }

    private void dfs(int x, int y, char[][] grid, boolean[][] visited){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == '1'){
                visited[nx][ny] = true;
                dfs(nx, ny, grid, visited);
            }
        }
    }
}

