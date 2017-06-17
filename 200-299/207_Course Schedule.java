/**
 * Time: O(V+2E)
 * Space: O(V+2E)
*/
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        List[] adj = new List[numCourses];
        int[] inDegree = new int[numCourses];
        // intialize adjacency list
        for(int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        // fill out the adjacency list and get indegree of every course
        for(int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int prev = prerequisites[i][1];
            inDegree[cur]++;
            adj[prev].add(cur);
        }
        // add all course with inDegree of 0 into queue
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) queue.add(i);
        }
        // poll node from queue, visit its neighbor and update their indegree. 
        // Add those neighbors with indegree of 0 into queue
        // Note: we also need to count the number of nodes we poll out from queue.
        int count = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for(int i = 0; i < adj[cur].size(); i++) { // traverse all cur's neighbors
                int neighbor = (int) adj[cur].get(i);
                inDegree[neighbor]--; // update indegree
                if(inDegree[neighbor] == 0) queue.add(neighbor);  // add to queue if indegree is 0
            }
        }
        return count == numCourses;
    }
}
// DFS solution: check if there is a cycle in the graph.
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        List[] adj = new List[numCourses];
        // intialize adjacency list
        for(int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        // fill out the adjacency list and get indegree of every course
        for(int i = 0; i < prerequisites.length; i++) {
            int cur = prerequisites[i][0];
            int prev = prerequisites[i][1];
            adj[prev].add(cur);
        }
        // status is used to mark the status of one node. there are 3 status: 
        // 1) visited, marked as -1; 2) unvisited, marked as 0; 3) on current path, marked as 1
        int[] status = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            // find the entrance of a path
            if(status[i] == 0) {
                // function dfs() will return true if there is a cycle in the graph
                if(dfs(i, adj, status)) return false;
            }
        }
        return true;
    }
    
    public boolean dfs(int cur, List[] adj, int[] status) {
        // mark cur is on current path
        status[cur] = 1;
        for(int i = 0; i < adj[cur].size(); i++) {
            int neighbor = (int) adj[cur].get(i);
            if(status[neighbor] == 1 || dfs(neighbor, adj, status)) {
                return true;
            } 
        }
        // no cycle, mark cur as visited
        status[cur] = -1; // do not forget this line
        return false;
    }
    
}