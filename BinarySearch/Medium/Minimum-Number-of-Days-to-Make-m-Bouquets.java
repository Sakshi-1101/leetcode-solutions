1class Solution {
2    public int minDays(int[] bloomDay, int m, int k) {
3        // edge case
4        if((long)(m * k) > (long)bloomDay.length) {
5            return -1;
6        }
7
8        // find the range where the ans will lie
9        int minDay = Integer.MAX_VALUE; 
10        int maxDay = Integer.MIN_VALUE; 
11
12        for(int i = 0 ; i < bloomDay.length ; i ++) {
13            minDay = Math.min(bloomDay[i], minDay);
14            maxDay = Math.max(bloomDay[i], maxDay);
15        }
16
17        long lo = minDay;
18        long hi = maxDay;
19        long ans = -1; // this will store the min no. of days needed to make m bouquets
20
21        while(lo <= hi) {
22            long mid = lo + (hi - lo) / 2;
23
24            long totalBq = getBouquetCount(bloomDay, k, mid);
25
26            // if total no. of bouquets is equal to or more than m
27            if(totalBq >= m) {
28                ans = mid;
29                hi = mid - 1; // try finding smaller value, since we need min no. of days
30            } else { // total no. of bouquets less than m
31                lo = mid + 1; 
32            }
33        }
34
35        return (int)ans;
36        
37    }
38
39    // calculating how many bouquets can be made for the given day
40    private static long getBouquetCount(int[] bloomDay, int k, long day) {
41        long count = 0; // keeps track of adjacent flowers bloomed
42        long currBq = 0; // keeps track of total bouquets made
43
44        // traverse through the bloomDayay and count how many bouquets can be made for the given day
45        for(int i = 0 ; i < bloomDay.length ; i ++) {
46            if(bloomDay[i] <= day) {
47                count++;
48            } else {
49                currBq += (count / k);
50                count = 0; // reset count to 0 for next adjacent bloomed flowers
51            }
52        }
53
54        currBq +=  (count / k);
55
56        return currBq;
57    }
58    
59}