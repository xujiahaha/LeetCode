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