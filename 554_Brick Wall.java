public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if(wall == null || wall.size() == 0) return 0;
        
        int m = wall.size(); // number of rows
        // map brick edge position to its frequency. the result is m-max(frequency)
        Map<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;
        // scan each row, map brick edge position and update maxFreq
        for(int i = 0; i < m; i++) {
            List<Integer> cur = wall.get(i);
            int sum = 0;
            // note: no need to add last brick since its edge is edge of the wall
            for(int j = 0; j < cur.size()-1; j++) { 
                sum += cur.get(j);
                map.put(sum, map.getOrDefault(sum, 0)+1);
                maxFreq = Math.max(maxFreq, map.get(sum));
            }
        }
        return m-maxFreq;
    }
}