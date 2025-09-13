public class StockBuyAndSell {

    /*
     * Constraints:
     * - you must buy before you sell.
     * - In this case, no transactions are done and the max profit = 0
     * - You can only complete at most one transaction (i.e., buy one and sell one share of the stock).
     */
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        // int[] arr = {7,6,4,3,1};

        int ans1 = findMaxProfitBrute(arr);
        int ans2 = findMaxProfitOptimal(arr);
        int ans3 = findMaxProfitBruteMyApproach(arr);

        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: For each element, consider it the minimum buying price and find the maximum selling price in the rest of the array starting from the element next to it.
    public static int findMaxProfitBruteMyApproach(int[] arr) {
        int maxProfit = 0;

        for(int i = 0 ; i < arr.length ; i ++){
            int min = arr[i];
            int max = 0;

            for(int j = i + 1 ; j < arr.length ; j ++) {
                max = Math.max(max, arr[j]);
            }

            int profit = max - min;
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;

    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: For each pair of days, calculate the profit and keep track of the maximum profit found.
    public static int findMaxProfitBrute(int[] arr){
        int maxProfit = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {
                int profit = arr[j] - arr[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: for a particular day, we'll calculate the profit if we sell on the ith day where we'll track the minimum buying price so far in the range 0 to ith index
    public static int findMaxProfitOptimal(int[] arr) {
        int min = arr[0]; // let the first element be the minimum buying price
        int maxProfit = 0;

        // for each element, calculate the profit if sold on that day where min will contain the minimum buying price so far in the range 0 to ith index
        for(int i = 1 ; i < arr.length ; i ++) {
            int profit = arr[i] - min; // calculate profit if sold on the ith day where arr[i] is the selling price
            maxProfit = Math.max(maxProfit, profit); // update maxProfit if the current profit is greater
            min = Math.min(min, arr[i]); // update min if the current price is lower than the previous min
        }

        return maxProfit;

    }
    
}
