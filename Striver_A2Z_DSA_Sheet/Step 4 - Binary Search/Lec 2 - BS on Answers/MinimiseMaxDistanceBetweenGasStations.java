
import java.util.*;

// BETTER APPROACH USING PRIORITY QUEUE (MAX-HEAP)
// define a Pair class to store the maximum section length and the index of the section in the priority queue
class Pair {
    double maxSectionLength;
    int maxSectionIdx;

    Pair(double maxSectionLength, int maxSectionIdx) {
        this.maxSectionLength = maxSectionLength;
        this.maxSectionIdx = maxSectionIdx;
    }
}

public class MinimiseMaxDistanceBetweenGasStations {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};        int k = 4;

        double ansBrute = findMinDistBrute(arr, k);
        double ansBetter = findMinDistBetter(arr, k);
        double ansOptimal = findMinDistOptimal(arr, k);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);

    }

    // TC: O(k * N) + O(N) -> O(k * N) for adding k gas stations + O(N) for finding the maximum distance between two gas stations
    // SC: O(N - 1) -> for sections array
    // Approach: In this approach, we will add gas stations one by one in such a way that the maximum distance between two gas stations is minimized.
    /*
        The code logic is as follows:
        1. We will maintain an array sections[] where sections[i] represents the number of gas stations added between arr[i] and 
           arr[i + 1]. This will help in calculating the maximum distance between two gas stations after adding k gas stations.
        2. We will start adding gas stations one by one. For each gas station, we will traverse the array and find the section 
           with maximum distance between two gas stations so that we can add the current gas station there to minimize the 
           maximum distance between two gas stations.
        3. After finding the section with maximum distance between two gas stations, we will add the current gas station there 
           and update the sections array to reflect that we have added one more gas station in that section.
        4. After adding all k gas stations, we will find the maximum distance between two gas stations.
        5. This will be the minimized maximum distance between two gas stations because we added the gas stations in such a way 
           that the maximum distance is minimized.
        
        NOTE: How to calculate the distance between two gas stations after adding gas stations in between?
              Let the distance between two gas stations be diff = arr[i + 1] - arr[i]
              Let the number of gas stations added between arr[i] and arr[i + 1] be sections[i]
              After adding sections[i] gas stations, the number of sections will be sections[i] + 1
              Hence, the distance between two gas stations after adding gas stations will be diff / (sections[i] + 1)
     */
    public static double findMinDistBrute(int[] arr, int k) {
        // sections[i] = number of gas stations added between arr[i] and arr[i + 1]
        // this will help in calculating the maximum distance between two gas stations after adding k gas stations
        int[] sections = new int[arr.length - 1];

        // start adding gas stations one by one
        for(int station = 1 ; station <= k ; station ++) {
            double maxSectionLength = -1; // represents the max distance between two gas stations after adding current gas station
            int maxSectionIdx = -1; // represents the index of the section where we will add the current gas station

            // traverse the array and find the section with maximum distance between two gas stations so that we can add the 
            // current gas station there to minimize the maximum distance between two gas stations
            for(int i = 0 ; i < arr.length - 1; i ++) {
                double diff = arr[i + 1] - arr[i]; // distance between two gas stations
                /*
                    Here sectons[i] represents the number of gas stations already added between arr[i] and arr[i + 1].
                    We are doing sections[i] + 1 bcoz after adding current gas station, the number of sections will be 
                    sections[i] + 1
                 */ 
                double sectionLength = diff / (double)(sections[i] + 1); // distance between two gas stations after adding current gas station

                // if the current section length is greater than the maximum section length found so far,
                // update the maximum section length and the index of the section so that we can add the current gas station there
                if(maxSectionLength < sectionLength){
                    maxSectionLength = sectionLength;
                    maxSectionIdx = i; // this is the index of the section where we will add the current gas station
                }
            }

            // after finding the section with maximum distance between two gas stations, add the current gas station there and 
            // update the sections array to reflect that we have added one more gas station in that section
            sections[maxSectionIdx] ++;
        }

        // after adding all k gas stations, find the maximum distance between two gas stations
        double maxAns = -1;

        /* 
           Traverse the sections array and find the maximum distance between two gas stations after adding all k gas stations.
           We are traversing the sections array bcoz it contains the number of gas stations that were added between each pair 
           of gas stations in the original array. Hence we can calculate the max distance between two gas stations after adding 
           all k gas stations.
        */
        for(int i = 0 ; i < arr.length - 1 ; i ++) {
            // distance between two gas stations
            double diff = arr[i + 1] - arr[i];
            double sectionLength = diff / (double)(sections[i] + 1);

            // update the maximum distance between two gas stations
            maxAns = Math.max(maxAns, sectionLength);
        }

        // this will be the minimized maximum distance between two gas stations bcoz we added the gas stations in such a way 
        // that the maximum distance is minimized
        return maxAns;
    }

    // TC: O(n * logn + k * logn) -> O(n * logn) for adding all sections to the priority queue + O(k * logn) for adding k gas stations where push and pop operations take logn time
    // SC: O(n - 1) -> bcoz at a point we are keeping only (n-1) sections in the priority queue
    // Approach: In this approach, we will use a priority queue (max-heap) to always get the section with the maximum length.
    /*
        The code logic is as follows:
        1. We will define a priority queue to store the sections based on their initial maximum section length in descending order.
        2. We will maintain an array sections[] where sections[i] represents the number of gas stations added between arr[i] and 
           arr[i + 1]. This will help in calculating the maximum distance between two gas stations after adding k gas stations.
        3. We will add all sections to the priority queue based on their initial maximum section length.
        4. We will start adding gas stations one by one. For each gas station, we will pop the pair having max sectionlength 
           from pq so that we can add the current gas station there to minimize the maximum distance between two gas stations.
        5. After popping the top element from pq, we will place the new gas station in that section and update the sections array 
           to reflect that we have added one more gas station in that section.
        6. We will then calculate the new maximum section length for that section after adding the gas station and add it back to 
           the priority queue.
        7. After adding all k gas stations, the top element of the pq will have the minimized maximum distance between two gas stations.
     */
    public static double findMinDistBetter(int[] arr, int k) {
        // define a priority queue to store the sections based on their initial maximum section length in descending order
        /*
            NOTE: PriorityQueue is a min-heap by default but we want a max-heap here to always get the section with the maximum length.
                  Hence, we are using a custom comparator to achieve that.
         */
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.maxSectionLength, a.maxSectionLength)
        );
        
        // sections[i] = number of gas stations added between arr[i] and arr[i + 1]
        // this will help in calculating the maximum distance between two gas stations after adding k gas stations
        int[] sections = new int[arr.length - 1];

        // add all sections to the priority queue based on their initial maximum section length
        for(int i = 0 ; i < arr.length - 1 ; i ++) {
            pq.add(new Pair(arr[i + 1] - arr[i], i));
        }

        // start adding gas stations one by one
        for(int station = 1 ; station <= k ; station ++) {
            // pop the pair having max sectionlength from pq so that we can add the current gas station there to minimize the 
            // maximum distance between two gas stations
            Pair top = pq.peek();

            // remove this top element from pq so that we can add the updated max section length for the current popped section 
            // back after adding the gas station
            pq.remove(); 

            // at this section idx we have the maxSectionLength 
            int sectionIdx = top.maxSectionIdx;
            sections[sectionIdx]++; // place the new gas station in this section

            double diff = arr[sectionIdx + 1] - arr[sectionIdx]; // distance between two gas stations for the current section

            /*
                Here sectons[i] represents the number of gas stations already added between arr[i] and arr[i + 1].
                We are doing sections[i] + 1 bcoz after adding current gas station, the number of sections will be 
                sections[i] + 1
            */ 
            double sectionLength = diff / (double)(sections[sectionIdx] + 1); // distance between two gas stations after adding current gas station

            // add the updated max section length for the current popped section back to the pq
            pq.add(new Pair(sectionLength, sectionIdx));

        }

        // after adding all k gas stations, the top element of the pq will have the minimized maximum distance between two gas stations
        return pq.peek().maxSectionLength;
    }
    

    // TC: O(N * log (base 2) N) + O(N) -> O(N * log N) for binary search where each iteration checks N sections + O(N) for finding max section length
    // SC: O(1)
    // Approach: In this approach, we will use binary search on the answer (the maximum distance between two gas stations).
    /*
        The code logic is as follows:
        1. We will find the maximum distance between two consecutive gas stations in the original array. This will be our upper bound (hi).
        2. We will set the lower bound (lo) to 0 as the minimum possible maximum distance.
        3. We will perform binary search on the answer. For each mid value (potential answer), we will check if we can place k gas stations
           such that the maximum distance between any two consecutive gas stations becomes <= mid.
        4. If we can place k gas stations with max distance <= mid, then we can try to minimize further, so we move hi = mid.
        5. If we need more than k gas stations to achieve max distance <= mid, then mid is too small, so we move lo = mid.
        6. We continue until hi - lo becomes very small (< 1e-6), at which point hi will be our answer.
        
        NOTE: This is a greedy + binary search approach where for each potential answer we greedily calculate minimum stations needed.
     */
    public static double findMinDistOptimal(int[] arr, int k) {
        // Find the maximum distance between two consecutive gas stations in the original array
        // This will be our upper bound for binary search as the answer cannot exceed this
        double maxSectionLength = Integer.MIN_VALUE;

        for(int i = 0 ; i < arr.length - 1 ; i ++) {
            maxSectionLength = Math.max(maxSectionLength, arr[i + 1] - arr[i]);
        }

        // lo = 0 (lower bound: minimum possible maximum distance)
        // hi = maxSectionLength (upper bound: maximum distance in original array)
        double lo = 0;
        double hi = maxSectionLength;
        double ans = -1;

        // diff = precision level for binary search on floating point numbers
        // We will continue binary search until the difference between hi and lo becomes less than 1e-6
        double diff = 1e-6;

        // this is not standard binary search where we do (mid + 1) or (mid - 1) because we are dealing with floating point numbers
        while(hi - lo > diff) {
            // mid = potential answer (maximum distance between two gas stations after placing k stations)
            double mid = (lo + hi) / 2.0;

            // countStations = minimum number of gas stations required to achieve maximum distance <= mid
            // This is calculated greedily: for each section, we calculate how many stations are needed to keep distance <= mid
            int countStations = getStationsRequired(arr, mid);

            // If we need more than k stations to achieve max distance <= mid, then mid is too small
            // So we increase lo to mid to search for a larger value
            if(countStations > k) {
                lo = mid;
            } else { 
                // If we need <= k stations to achieve max distance <= mid, then mid is feasible. Store this feasible answer 
                // and try to find a smaller value by setting hi = mid since we need to minimize the maximum distance
                ans = hi;
                hi = mid;
            }
        }

        /*
            NOTE: We return ans (or hi) because at the end of the loop, hi will contain the minimum possible maximum distance
                  that can be achieved by placing at most k gas stations. Since we never do (mid + 1) or (mid - 1), hi stores
                  the final feasible answer from our binary search.
         */
        return ans; // OR return hi;
    }

    // Helper function to calculate the minimum number of gas stations required to make all sections have distance <= dist
    // TC: O(N) where N is the number of sections in the array
    // SC: O(1)
    public static int getStationsRequired(int[] arr, double dist) {
        int count = 0;

        // Traverse through all sections (pairs of consecutive gas stations)
        for(int i = 1 ; i < arr.length ; i ++) {
            // sectionLength = distance between two consecutive gas stations arr[i-1] and arr[i]
            int sectionLength = arr[i] - arr[i - 1];
            
            // numStations = number of gas stations needed in this section to keep distance <= dist
            // We use (sectionLength / dist) to find how many sections of length dist we can fit
            int numStations = (int)(sectionLength / dist);

            // If sectionLength is exactly divisible by dist, we need one less station
            // This is because if the section length is a perfect multiple of dist, placing stations at each dist interval
            // will create an extra section at the end which does not need an additional station.
            /* Example: 
                arr[1,2,3,4], k=4
                    - if dist = 0.4, sectionLength = 1 (between 1 and 2)
                        - We would calculate numStations = 1 / 0.4 = 2.5 which truncates to 2. But placing 2 stations at 1.4 and 1.8 creates sections [1,1.4], [1.4,1.8], [1.8,2]
                        - All sections are <= 0.4, so we actually only need 2 stations.
                    - if dist = 0.5, sectionLength = 1 (between 1 and 2)
                        - We would calculate numStations = 1 / 0.5 = 2. But this is wrong since according this we can place 2 
                          stations. But Ideally only 1 station is needed at 1.5 to make sections [1,1.5] and [1.5,2]
                        - Both sections are exactly 0.5, so we only need 1 station at 1.5.
                        -  Hence, we reduce numStations by 1 in this case.
            */
            if((sectionLength / dist) == (numStations * dist)) {
                numStations --;
            }

            // Add the number of stations needed in this section to the total count
            count += numStations;
        }

        // Return the total number of gas stations required to make all sections have distance <= dist
        return count;
    }
}
