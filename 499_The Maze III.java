/**
 * Time: O(m*n)
 * Space: O(m*n)
*/
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    private final String[] dirsSign = {"u", "d", "l", "r"};
    // For each position, we need to store x, y, d and also r. 
    // Since they are not same datatype, we need create a Point class to store all these information.
    // In MazeII, all wanted information are integer, so we use int[3] to store them. 
    private static class Point {
        int x, y, d;
        String r;
        public Point (int x, int y, int d, String r) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.r = r;
        }
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        String[][] route = new String[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(ball[0], ball[1], 0, ""));
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            // we can revisit visited position as long as we can find a shorter route to reach that position
            // if we have found a shorter route, skip current one.
            if(dist[cur.x][cur.y] < cur.d || (dist[cur.x][cur.y] == cur.d && route[cur.x][cur.y].compareTo(cur.r) <= 0)) continue; 
            route[cur.x][cur.y] = cur.r;
            dist[cur.x][cur.y] = cur.d;
            for(int i = 0; i < 4; i++) {
                int[] dir = dirs[i];
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];
                int nd = cur.d + 1;
                String nr = cur.r+dirsSign[i];
                // keep rolling along this direction until hitting the wall or meet the hole
                while(nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] != 1) {
                    if(nx == hole[0] && ny == hole[1]) break;
                    nx += dir[0];
                    ny += dir[1];
                    nd++;
                }
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && nx == hole[0] && ny == hole[1]) {
                    queue.add(new Point(nx, ny, nd, nr));
                    continue;
                }
                nx -= dir[0];
                ny -= dir[1];
                nd--;
                queue.add(new Point(nx, ny, nd, nr));
            }
        }
        return dist[hole[0]][hole[1]] == Integer.MAX_VALUE ? "impossible" : route[hole[0]][hole[1]];
    }
}