package com.jiaxu.cs504;

import java.util.*;

public class WeightedRandomSampling {

    private static Map<String, Integer> freq;  // map item to the number of times being selected

    public static void main(String[] args) {
	    // write your code here
        // Here the weights of each are represented in Integer.
        // If using decimal for weights, we can convert them to Integer first.
        // eg. weight of [0.2, 0.3, 0.4, 0.1] could be converted to [2, 3, 4, 1].
        Map<String, Integer> map = new HashMap<>();
        map.put("item0", 8);
        map.put("item1", 4);
        map.put("item2", 6);
        map.put("item3", 1);
        map.put("item4", 7);
        map.put("item5", 3);
        int total = 0;
        for (int values : map.values()) {
            total += values;
        }
        int m = 10000; // number of round of sampling
        List<String> res = weightedRandomSampling(map, m);
        double probSum = 0;
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            probSum += (double)entry.getValue()/m;
            System.out.println(entry.getKey() + " = " + entry.getValue()
                    + ", desired prob = " + (double) map.get(entry.getKey())/total
                    + ", real prob = " + (double)entry.getValue()/m);
        }
        System.out.println(probSum);
    }

    public static List<String> weightedRandomSampling(Map<String, Integer> map, int m) {
        String[] items = new String[map.size()];
        int[] weights = new int[map.size()];
        int index = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            items[index] = entry.getKey();
            weights[index] = entry.getValue();
            index++;
        }
        return weightedRandomSampling(items, weights, m);
    }

    private static List<String> weightedRandomSampling(String[] items, int[] weights, int m) {
        List<String> res = new ArrayList<>(m);
        freq = new HashMap<>();
        if(items.length == 0) return res;
        int len = items.length;
        int[] sum = new int[len+1];
        sum[1] = weights[0];
        for(int i = 2; i <= len; i++) {
            sum[i] = sum[i-1] + weights[i-1];
        }
        Random rand = new Random();
        int bound = sum[len];
        int num, idx = 0;
        for(int i = 0; i < m; i++) {
            num = rand.nextInt(bound);
            /* O(n) time
            for(int j = 0; j < len; j++) {
                if(num >= sum[j] && num < sum[j+1]) {
                    idx = j;
                    break;
                }
            }*/
            // use binary search. O(lgn) time
            int l = 0, r = len-1;
            while(l < r) {
                int mid = l + (r - l + 1)/2;
                if(sum[mid] <= num) l = mid;
                else r = mid-1;
            }
            idx = r;
            res.add(items[idx]);
            freq.put(items[idx], freq.getOrDefault(items[idx], 0)+1);
        }
        return res;
    }
}
/* Sample test result: 
item0 = 2659, desired prob = 0.27586206896551724, real prob = 0.2659
item2 = 2104, desired prob = 0.20689655172413793, real prob = 0.2104
item1 = 1369, desired prob = 0.13793103448275862, real prob = 0.1369
item4 = 2497, desired prob = 0.2413793103448276, real prob = 0.2497
item3 = 344, desired prob = 0.034482758620689655, real prob = 0.0344
item5 = 1027, desired prob = 0.10344827586206896, real prob = 0.1027
1.0
*/
