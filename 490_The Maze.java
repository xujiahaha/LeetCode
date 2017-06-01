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