/**
 * Time: O(m*n)
 * Space: O(m*n) worst case when all cells are '1'
*/
public class Solution {
    
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    
    public int numIslands(char[][] grid) {
        // corner case
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // find an entrance of an island
                if(grid[i][j] == '1') {
                    // set all connected lands to '0'
                    dfs(grid, i, j, m, n);
                    // bfs(grid, i, j, m , n);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void dfs(char[][] grid, int x, int y, int m, int n) {
        grid[x][y]  = '0';
        for(int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1') {
                dfs(grid, nx, ny, m, n);
            }
        }
    }
    
    public void bfs(char[][] grid, int x, int y, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        grid[x][y] = '0';
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1') {
                    grid[nx][ny] = '0';
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}