public class CapacityToShipPackagesWithinDdays {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        
        int ansBrute = findLeastCapacityBrute(arr, days);
        int ansOptimal = findLeastCapacityOptimal(arr, days);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);

    }

    // TC: O(totalWeight - maxWeight + 1) * O(n) -> O(n) is the TC for getDays()
    // SC: O(1)
    // Approach: In this we try all possible ship capacities from the maximum weight of a single package to the total weight of all packages.
    //           For each capacity, we calculate how many days are needed to ship all the packages with that capacity.
    //           If the number of days needed is less than or equal to the given days, we return that capacity as the answer.
    public static int findLeastCapacityBrute(int[] arr, int days) {
        // find the range of possible ship capacities where the answer will lie in the range [maxWeight....totalWeight]
        int maxWeight = Integer.MIN_VALUE;
        int totalWeight = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            maxWeight = Math.max(arr[i], maxWeight);
            totalWeight += arr[i];
        }

        // traverse for all possible ship capacities
        for(int cap = maxWeight ; cap <= totalWeight ; cap ++) {

            // find the days needed to ship all the packages if the capacity of the ship is "cap"
            int daysReq = getDays(arr, cap);

            // if the days needed is less than or equal to the given days, return that capacity
            if(daysReq <= days) {
                return cap;
            }
        }

        // if not found
        return -1;
    }

    // calculating how many days are needed to ship all the packages with the given capacity.
    /*
        The code logic is as follows:
        1. We initialize countDays to 1, as we need at least one day to ship the packages.
        2. We initialize currentLoad to 0, which keeps track of the weight loaded on the ship for the current day.
        3. We iterate through each package in the arr:
            - If adding the current package's weight to currentLoad exceeds the ship's capacity, we increment countDays by 1 
              (indicating a new day is needed) and set currentLoad to the weight of the current package.
            - If adding the current package's weight does not exceed the ship's capacity, we simply add the package's weight to 
              currentLoad.
        4. Finally, we return countDays, which represents the total number of days needed to ship all the packages with the given 
           capacity.
     */
    private static int getDays(int[] arr, int capacity) {
        int countDays = 1; // At least one day is needed
        int currentLoad = 0; // How much weight is on today's ship

        for(int i = 0 ; i < arr.length ; i ++) {
            // while loading the package into the ship, if total loadWeight exceeds the ship capacity
            if(currentLoad + arr[i] > capacity) {
                countDays += 1; // load that package the next day
                currentLoad = arr[i];  // start new day with this package
            } else { // if the capacity is not exceeding
                // keep loading the package the same day and add it's weight to the currentLoad
                currentLoad += arr[i];
            }
        }

        return countDays;
       
    }
    
    // TC: O(log (base 2) (totalWeight - maxWeight + 1)) * O(n) -> O(n) is the TC for getDays()
    // SC: O(1)
    // Approach: In this we'll use binary search to find the least ship capacity. We set our search boundaries from the maximum 
    //           weight of a single package to the total weight of all packages. For each mid value (possible ship capacity), 
    //           we calculate how many days are needed to ship all the packages with that capacity. If the number of days needed 
    //           is less than or equal to the given days, we update our answer and try to find a smaller capacity.
    //           If the number of days needed is more than the given days, we increase our capacity. 
    public static int findLeastCapacityOptimal(int[] arr, int days) {
        // find the range of possible ship capacities where the answer will lie in the range [maxWeight....totalWeight]
        int maxWeight = Integer.MIN_VALUE;
        int totalWeight = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            maxWeight = Math.max(arr[i], maxWeight);
            totalWeight += arr[i];
        }

        // set the boundaries
        int lo = maxWeight;
        int hi = totalWeight;
        
        int ans = -1; // if not found

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // find the days needed to ship all the packages if the capacity of the ship is "mid"
            int totalDays = getDays(arr, mid);

            // if the days needed is less than or equal to the given days
            if(totalDays <= days) {
                ans = mid; // store the possible ans
                hi = mid - 1; // look for more smaller capacity
            } else {
                lo = mid + 1; // no point of going more left since current capacity is giving days needed > given days, look for bigger capacity
            }
        }

        return lo; // OR return ans;
    }
}
