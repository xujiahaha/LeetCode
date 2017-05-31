/**
 * Time complexity: O(m*n) 
 * Space complexity: O(m*n) worst case when all rooms are filled with 0. 
*/
public class Solution {

    private final int INF = Integer.MAX_VALUE;
    // four directions: up, down, left, right
    private int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};

    public void wallsAndGates(int[][] rooms) {
        // check corner case
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // add all gate position into queue, which are level 0.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rooms[i][j] == 0) queue.add(new int[]{i,j});
            }
        }
        // bfs
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            // visit neighbors
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && rooms[nx][ny] == INF) {
                    rooms[nx][ny] = rooms[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}