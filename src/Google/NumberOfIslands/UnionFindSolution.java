public class UnionFindSolution {
    static int[] id;
    static int[] sz;
    static final int[] dx = {-1, 0};
    static final int[] dy = {0, -1};
    static int m;
    static int n;

    public int find(int x){
        while(x != id[x]){
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if(i == j){
            return;
        }
        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
    }
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        m = grid.length;
        n = grid[0].length;
        id = new int[m *n];
        sz = new int[m * n];
        for(int i = 0; i < m *n ; i++){
            id[i] = i;
            sz[i] = 1;
        }
        int cnt = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    cnt = getCount(i, j, cnt, grid);
                }
            }
        }
        return cnt;
    }

    public int getCount(int x, int y, int cnt, char[][] grid){
        cnt ++;
        for(int i = 0; i < 2; i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int p = find(x * n + y);
            if(nx >= 0 && ny >= 0 && nx < m && ny < n && grid[nx][ny] == '1'){
                int q = find(nx * n + ny);
                if(p != q){
                    cnt --;
                    union(p, q);
                }
            }
        }
        return cnt;
    }
}


