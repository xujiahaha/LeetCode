/**
 * Time: O(V+2E)
 * Space: O(V+2E)
*/
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        int[] res = new int[numCourses];
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            res[count] = cur;
            count++;
            for(int i = 0; i < adj[cur].size(); i++) { // traverse all cur's neighbors
                int neighbor = (int) adj[cur].get(i);
                inDegree[neighbor]--; // update indegree
                if(inDegree[neighbor] == 0) queue.add(neighbor);  // add to queue if indegree is 0
            }
        }
        return count == numCourses ? res : new int[0];
    }
}
// DFS solution
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        Stack<Integer> stack = new Stack<>();
        int[] status = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(status[i] == 0) {
                if(dfs(adj, i, status, stack)) return new int[0];
            }
        }
        int[] res = new int[numCourses];
        int index = 0;
        while(!stack.isEmpty()) {
            res[index++] = stack.pop();
        }
        return res;
    }
    // return true if there is a cycle
    public boolean dfs(List[] adj, int cur, int[] status, Stack<Integer> stack) {
        // mark cur on current path
        status[cur] = 1;
        for(int i = 0; i < adj[cur].size(); i++) {
            int neighbor = (int) adj[cur].get(i);
            if(status[neighbor] == -1) continue;
            if(status[neighbor] == 1 || dfs(adj, neighbor, status, stack)) return true;
        }
        // mark cur as visited
        status[cur] = -1;
        stack.push(cur);
        return false;
    }
}