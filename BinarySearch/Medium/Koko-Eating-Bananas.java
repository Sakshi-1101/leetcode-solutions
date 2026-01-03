1class Solution {
2    public int minEatingSpeed(int[] piles, int h) {
3
4        long maxVal = Integer.MIN_VALUE;
5
6        // find max in pilesay
7        for(int i = 0 ; i < piles.length ; i ++) {
8            maxVal = Math.max(piles[i], maxVal);
9        }
10
11        long lo = 1;
12        long hi = maxVal;
13
14        while(lo <= hi) {
15            long mid = lo + (hi - lo) / 2;
16
17            long hrs = getTotalHrs(piles, mid);
18
19            if(hrs <= h) {
20                hi = mid - 1;
21            } else {
22                lo = mid + 1;
23            }
24        }
25
26        return (int)lo;
27        
28    }
29
30    // traverse through the pilesay and calculate total hrs it takes to eat ith pile of bananas if eating speed is x  bananas/hr
31    private static long getTotalHrs(int[] piles, long x) {
32        long totalHrs = 0;
33        for(int i = 0 ; i < piles.length ; i ++) {
34            totalHrs += (long)Math.ceil((double)piles[i] / x);
35        }
36
37        return totalHrs;
38    }
39}