/**
 * Time: O(m*n)
 * Space: O(m*n)
*/
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            // we can revisit visited position as long as we can find a shorter route to reach that position
            // if we have found a shorter route, skip current one.
            if(dist[x][y] <= d) continue; 
            dist[x][y] = d;
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                int nd = d+1;
                // roll along this direction until hitting the wall
                while(nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] != 1) {
                    nx += dir[0];
                    ny += dir[1];
                    nd++;
                }
                nx -= dir[0];
                ny -= dir[1];
                nd--;
                queue.add(new int[]{nx, ny, nd});
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}