public class MinDaysToMakeMBoquets {

    public static void main(String[] args) {
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int m = 2;
        int k = 3;

        int ansBrute = findMinDaysBrute(arr, m, k);
        int ansOptimal = findMinDaysOptimal(arr, m, k);
        
        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(maxDay - minDay + 1) * O(n) -> where O(n) -> getBouquetCount()
    // SC: O(1)
    // Approach: In this brute force approach, we try all possible days from the minimum bloom day to the maximum bloom day.
    //           For each day, we calculate how many bouquets can be made with the flowers bloomed up to that day.
    //           If the number of bouquets equals m, we return that day as the answer. If no such day is found, we return -1.
    public static int findMinDaysBrute(int[] arr, int m , int k) {
        // edge case -> if total no. of flowers needed to make m bouquets is more than the available flowers, return -1
        if(m * k > arr.length) {
            return -1;
        }

        // find the range where the ans will lie
        int minDay = Integer.MAX_VALUE; // the min value day when the least no. of flowers will bloom
        int maxDay = Integer.MIN_VALUE; // the max value day when all the flowers will bloom

        for(int i = 0 ; i < arr.length ; i ++) {
            minDay = Math.min(arr[i], minDay);
            maxDay = Math.max(arr[i], maxDay);
        }

        // traverse through the range
        for(int i = minDay ; i <= maxDay ; i ++) {

            // for each day in the range, check how many bouquets can be made. If it equals m, return that day no need to check 
            // further since we need min no. of days.
            if(getBouquetCount(arr, k, i) == m) {
                return i;
            }
        }

        // if not found, return -1
        return -1;
    }

    // calculating how many bouquets can be made for the given day
    private static int getBouquetCount(int[] arr, int k, int day) {
        int count = 0; // keeps track of adjacent flowers bloomed
        int currBq = 0; // keeps track of total bouquets made

        // traverse through the array and count how many bouquets can be made for the given day
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] <= day) {
                count++;
            } else { // flower not bloomed, so calculate no. of bouquets that can be made from the adjacent bloomed flowers till now
                currBq += (count / k);
                count = 0; // reset count to 0 for next adjacent bloomed flowers
            }
        }

        // after the loop ends, if the last flowers were bloomed, we need to calculate the bouquets that can be made from them as well
        currBq +=  (count / k);

        return currBq;
    }
    
    // TC: o(log (base 2) (maxDay - minDay + 1)) * O(N)
    // SC: O(1)
    // Approach: In this we'll use binary search to find the minimum bloom day. We set our search boundaries from the minimum bloom day
    //           to the maximum bloom day. For each mid value (bloom day), we calculate how many bouquets can be made with the flowers bloomed up to that day.
    //           If the number of bouquets is equal to or more than m, we update our answer and try to find a smaller bloom day.
    //           If the number of bouquets is less than m, we increase our bloom day.
    public static int findMinDaysOptimal(int[] arr, int m, int k) {
        // edge case -> if total no. of flowers needed to make m bouquets is more than the available flowers, return -1
        if(m * k > arr.length) {
            return -1;
        }

        // find the range where the ans will lie
        int minDay = Integer.MAX_VALUE; // the min value day when the least no. of flowers will bloom
        int maxDay = Integer.MIN_VALUE; // the max value day when all the flowers will bloom

        for(int i = 0 ; i < arr.length ; i ++) {
            minDay = Math.min(arr[i], minDay);
            maxDay = Math.max(arr[i], maxDay);
        }

        int lo = minDay;
        int hi = maxDay;
        int ans = -1; // this will store the min no. of days needed to make m bouquets

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int totalBq = getBouquetCount(arr, k, mid);

            // if total no. of bouquets is equal to or more than m
            if(totalBq >= m) {
                ans = mid;
                hi = mid - 1; // try finding smaller value, since we need min no. of days
            } else { // total no. of bouquets less than m
                // no point on checking on the left side since it wasn't able to make m bouquets, so we need more no. of 
                // bloom days so check on the right side
                lo = mid + 1; 
            }
        }

        return lo; // OR return ans;
    }
}
