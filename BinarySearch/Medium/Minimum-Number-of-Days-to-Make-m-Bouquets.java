class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        // edge case
        if((long)(m * k) > (long)bloomDay.length) {
            return -1;
        }

        // find the range where the ans will lie
        int minDay = Integer.MAX_VALUE; 
        int maxDay = Integer.MIN_VALUE; 

        for(int i = 0 ; i < bloomDay.length ; i ++) {
            minDay = Math.min(bloomDay[i], minDay);
            maxDay = Math.max(bloomDay[i], maxDay);
        }

        long lo = minDay;
        long hi = maxDay;
        long ans = -1; // this will store the min no. of days needed to make m bouquets

        while(lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            long totalBq = getBouquetCount(bloomDay, k, mid);

            // if total no. of bouquets is equal to or more than m
            if(totalBq >= m) {
                ans = mid;
                hi = mid - 1; // try finding smaller value, since we need min no. of days
            } else { // total no. of bouquets less than m
                lo = mid + 1; 
            }
        }

        return (int)ans;
        
    }

    // calculating how many bouquets can be made for the given day
    private static long getBouquetCount(int[] bloomDay, int k, long day) {
        long count = 0; // keeps track of adjacent flowers bloomed
        long currBq = 0; // keeps track of total bouquets made

        // traverse through the bloomDayay and count how many bouquets can be made for the given day
        for(int i = 0 ; i < bloomDay.length ; i ++) {
            if(bloomDay[i] <= day) {
                count++;
            } else {
                currBq += (count / k);
                count = 0; // reset count to 0 for next adjacent bloomed flowers
            }
        }

        currBq +=  (count / k);

        return currBq;
    }
    
}