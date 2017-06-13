/**
 * Time: O(m*n)
 * Space: O(m*n)
*/
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n]; // mark visited position
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                // roll along this direction until hitting the wall
                while(nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] != 1) {
                    nx += dir[0];
                    ny += dir[1];
                }
                nx -= dir[0];
                ny -= dir[1];
                if(visited[nx][ny]) continue;
                if(nx == destination[0] && ny == destination[1]) return true;
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        return false;
    }
}
// DFS solution
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n]; // mark visited position
        return dfs(maze, visited, start, destination, m , n);
    }
    public boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination, int m, int n) {
        if(start[0] == destination[0] && start[1] == destination[1]) return true;
        visited[start[0]][start[1]] = true;
        for(int[] dir : dirs) {
            int nx = start[0] + dir[0];
            int ny = start[1] + dir[1];
            while(nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] != 1) {
                nx += dir[0];
                ny += dir[1];
            }
            nx -= dir[0];
            ny -= dir[1];
            if(visited[nx][ny]) continue;
            if(dfs(maze, visited, new int[]{nx, ny}, destination, m, n)) return true;
        }
        return false;
    }
}