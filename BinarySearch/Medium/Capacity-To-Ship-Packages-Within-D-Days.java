class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // find the range of possible ship capacities
        int maxWeight = Integer.MIN_VALUE;
        int totalWeight = 0;

        for(int i = 0 ; i < weights.length ; i ++) {
            maxWeight = Math.max(weights[i], maxWeight);
            totalWeight += weights[i];
        }

        // set the boundaries
        int lo = maxWeight;
        int hi = totalWeight;
        
        int ans = -1; // if not found

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // find the days needed to ship all the packages if the capacity of the ship is "mid"
            int totalDays = getDays(weights, mid);

            // if the days needed is less than or equal to the given days
            if(totalDays <= days) {
                ans = mid; // store the possible ans
                hi = mid - 1; // look for more smaller capacity
            } else {
                lo = mid + 1; 
            }
        }

        return ans; // OR return lo;
    }

    private static int getDays(int[] weights, int capacity) {
        int countDays = 1; // At least one day is needed
        int currentLoad = 0; // How much weight is on today's ship

        for(int i = 0 ; i < weights.length ; i ++) {
            // while loading the package into the ship, if total loadWeight exceeds the ship capacity
            if(currentLoad + weights[i] > capacity) {
                countDays += 1; // load that package the next day
                currentLoad = weights[i];  // start new day with this package
            } else { // if the capacity is not exceeding
                // keep loading the package the same day and add it's weight to the currentLoad
                currentLoad += weights[i];
            }
        }

        return countDays;
       
    }
    
}