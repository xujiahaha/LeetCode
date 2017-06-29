package com.jiaxu.cs504;

import java.util.*;

public class WeightedRandomSampling {

    private static List<String> res;  // used for storing items selected in round i

    public static void main(String[] args) {
	    // write your code here
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
        Map<String, Integer> freq = weightedRandomSampling(map, m);
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue()
                    + ", desired prob = " + (double) map.get(entry.getKey())/total
                    + ", real prob = " + (double)entry.getValue()/m);
        }
    }

    public static Map<String, Integer> weightedRandomSampling(Map<String, Integer> map, int m) {
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

    private static Map<String, Integer> weightedRandomSampling(String[] items, int[] weights, int m) {
        res = new ArrayList<>(m);
        Map<String, Integer> freq = new HashMap<>();
        if(items.length == 0) return freq;
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
        return freq;
    }
}
