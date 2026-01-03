public class KokoEatingBananas {

    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 11};
        int h = 8;

        int ansBrute = findMinBananasBrute(arr, h);
        int ansOptimal = findMinBananasOptimal(arr, h);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(max(arr[i]) * n) => where n represents the traversal done in getTotalHrs() method
    // SC: O(1)
    // Approach: In this brute force approach, we try all possible eating speeds from 1 banana/hr to max(arr[i]) bananas/hr.
    //           For each eating speed, we calculate the total hours it would take Koko to eat all the bananas.
    //           If the total hours is within the given deadline h, we return that eating speed as the answer.
    //           If no eating speed allows Koko to finish within h hours, we return max(arr[i]) as the worst-case scenario.
    public static int findMinBananasBrute(int[] arr, int h) {
        // this represents the maximum no. of bananas that Koko can eat within h hours limit.
        int maxVal = Integer.MIN_VALUE;

        // find max in array
        for(int i = 0 ; i < arr.length ; i ++) {
            maxVal = Math.max(arr[i], maxVal);
        }

        // traverse through 1 to maxVal where i represents i bananas/hr
        for(int i = 1 ; i <= maxVal ; i ++) {
            // find the total time it'll take to eat i no. of banana/hr
            int hrs = getTotalHrs(arr, i);

            // if total time is within the given deadline time, then return i
            if(hrs <= h) {
                return i; // i represents koko can eat i bananas/hr to finish all bananas within h hours.
            }
        }

        // if not found, return maxVal bcoz in worst case, koko has to eat maxVal bananas/hr to finish all within h hours.
        return maxVal;
    }

    // calculating the total hrs it will take to eat all bananas if we assume Koko eats x bananas/hr
    private static int getTotalHrs(int[] arr, int x) {
        int totalHrs = 0;

        // traverse through the array and calculate total hrs it takes to eat ith pile of bananas if eating speed is x bananas/hr
        for(int i = 0 ; i < arr.length ; i ++) {
            /*
                NOTE:
                1. We are calculating Math.ceil()
                => bcoz if Koko has to eat 3 bananas and her speed is 2 bananas/hr,
                   then in 1 hour she can eat 2 bananas, but for the remaining 1 banana, she will need another hour.
                   So total time taken will be 2 hours. Hence we use Math.ceil to round up the hours bcoz 3/2 = 1.5 ~ 2 hours.
                2. We need to add (double) 
                => bcoz if we do (arr[i] / x) directly, Java performs INTEGER division because both operands are ints, but we need 
                   floating-point division to get accurate hours.
                   Eg: arr[i] = 3, x = 2 => 3 / 2 = 1 (INTEGER division) but we need 1.5 (FLOATING-POINT division)
             */
            totalHrs += (int)Math.ceil((double)arr[i] / x);
        }

        return totalHrs;
    }
    

    // TC: O(N) * O(log (base 2) M)
    // SC: O(1)
    // Approach: We'll use binary search to find the minimum eating speed. We set our search boundaries from 1 banana/hr 
    //           to max(arr[i]) bananas/hr. For each mid value (eating speed), we calculate the total hours it would take Koko 
    //           to eat all the bananas. If the total hours is within the given deadline h, we update our answer and try to find 
    //           a smaller eating speed. If the total hours exceed h, we increase our eating speed.
    public static int findMinBananasOptimal(int[] arr, int h) {
        // this represents the maximum no. of bananas that Koko can eat within h hours limit.
        int maxVal = Integer.MIN_VALUE;

        // find max in array
        for(int i = 0 ; i < arr.length ; i ++) {
            maxVal = Math.max(arr[i], maxVal);
        }

        // set binary search boundaries
        int lo = 1;
        int hi = maxVal;

        // set ans to maxVal bcoz in worst case, koko has to eat maxVal bananas/hr to finish all within h hours.
        int ans = maxVal;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // find the total time it'll take to eat mid no. of banana/hr
            int hrs = getTotalHrs(arr, mid);

            // if total time is within the given deadline time, then update ans and try to find a smaller eating speed
            if(hrs <= h) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo; // OR return ans;

    }
}
