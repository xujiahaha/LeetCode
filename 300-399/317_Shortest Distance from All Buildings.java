/**
 * Time:
 *   Assume there are k buildings, and all empty lands can be reached by every building.
 *   For each building, we need to traverse the whole grid to calculate the shortest distance between current building and all empty lands.
 *   So total time is O(k*m*n)
 * Space:
 *   dist[m][n] is to store the total distance between an empty land to all buildings
 *   reach[m][n] is to store the number of buildings that this land can reach.
 *   visited[m][n] is to mark visited lands to avoid endless loop
 *   queue less than O(m*n)
 *   so total space is O(m*n)
*/
public class Solution {
    
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] reach = new int[m][n]; // number of buildings that this land can reach
        int buildingNum = 0; // number of buildings
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // (i, j) is a building, take (i, j) as level 0
                // do bfs to get the distance from building (i,j) to all other empty land
                if(grid[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[m][n];
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    int level = 1;
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        for(int k = 0; k < size; k++) {
                            int[] cur = queue.poll();
                            int x = cur[0];
                            int y = cur[1];
                            for(int[] dir : dirs) {
                                int nx = x + dir[0];
                                int ny = y + dir[1];
                                // if reach[nx][ny] is less than the number of building has been visited,
                                // pass this land since the valid land should be reachable by all buildings.
                                if(nx >= 0 && nx < m && ny >= 0 && ny < n && reach[nx][ny] == buildingNum && grid[nx][ny] == 0 && !visited[nx][ny]) {
                                    dist[nx][ny] += level;
                                    reach[nx][ny]++;
                                    queue.add(new int[]{nx, ny});
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                        level++;
                    }
                    buildingNum++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // must be an empty land and reachable by all buildings
                if(grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    min = Math.min(min, dist[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}