public class TrappingRainwater {

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int n = arr.length;

        int ansBrute = getTrappedWaterBrute(arr, n);
        int ansOptimal = getTrappedWaterOptimal(arr, n);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);

    }

    // TC: O(3N) -> O(2N) for prefix anf suffix max and O(N) for traversing the array
    // SC: O(2N) -> for storing prefix and suffix max
    // Approach: In this approach, we will calculate the maximum height of buildings to the left and right of each building. Then 
    //           we will check if the current building is shorter than both the left and right max buildings. If it is, then it 
    //           can trap water. The total water trapped on top of the current building will be the minimum height of the left 
    //           and right max buildings minus the height of the current building.
    public static int getTrappedWaterBrute(int[] arr, int n) {
        int totalWater = 0; // to store total trapped water

        int[] prefixMax = getPrefixMax(arr, n); // for each ith building, store the tallest building on the left of ith building
        int[] suffixMax = getSuffixMax(arr, n); // for each ith building, store the tallest building on the right of ith building

        // traverse the array for each building and find how much water it will trap and calculate the total water trapped
        for(int i = 0 ; i < n ; i ++) {
            int leftMax = prefixMax[i]; // tallest builing on the left of ith building
            int rightMax = suffixMax[i]; // tallest builing on the right of ith building

            // if the current building is shorter than both left and right max then it can trap water
            if(arr[i] < leftMax && arr[i] < rightMax) {
                // total water trapped on top of the ith building will be the min height of the left and right max building 
                // minus the height of the current building
                totalWater += Math.min(leftMax, rightMax) - arr[i];
            }
        }

        return totalWater;
    }

    // Function to calculate the maximum height of buildings to the left of each building
    private static int[] getPrefixMax(int[] arr, int n)  {
        int[] prefixMax = new int[n];

        prefixMax[0] = arr[0]; // the first building is the tallest building on the left of itself

        // traverse the array to calculate the maximum height of buildings to the left of each building
        for(int i = 1 ; i < n ; i ++) {
            // the maximum height of buildings to the left of the ith building will be the max of the height till (i-1)th 
            // building and the height of the ith building
            prefixMax[i] = Math.max(prefixMax[i - 1], arr[i]);
        }

        return prefixMax;
    }

    // Function to calculate the maximum height of buildings to the right of each building
    private static int[] getSuffixMax(int[] arr, int n)  {
        int[] suffixMax = new int[n];

        suffixMax[n - 1] = arr[n - 1]; // the last building is the tallest building on the right of itself

        // traverse the array in reverse to calculate the maximum height of buildings to the right of each building
        for(int i = n - 2 ; i >= 0 ; i --) {
            // the maximum height of buildings to the right of the ith building will be the max of the height till (i+1)th 
            // building and the height of the ith building
            suffixMax[i] = Math.max(suffixMax[i + 1], arr[i]);
        }

        return suffixMax;
    }
    
    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will use two pointers to traverse the array from both ends. We will keep track of the 
    //           maximum height of buildings to the left and right of the current building. We will always pick the smaller one 
    //           between arr[left] and arr[right] because the water trapped on top of the current building will be limited by the 
    //           shorter building. If the current building is shorter than both leftMax and rightMax, then it can trap water. The 
    //           total water trapped on top of the current building will be leftMax - arr[left] or rightMax - arr[right] depending 
    //           on which pointer we are at.
    public static int getTrappedWaterOptimal(int[] arr, int n) {
        int totalWater = 0; // represents the total water trapped

        // two pointers to traverse the array from both ends
        int left = 0;
        int right = n - 1;

        // to store the maximum height of buildings to the left and right of the current building
        int leftMax = 0;
        int rightMax = 0;

        // traverse the array until the two pointers meet
        while(left < right) {
            // always pick the smaller one (arr[left] or arr[right]) because the water trapped on top of the current building 
            // will be limited by the shorter building

            // means there is a building on the right which is taller than current building at arr[left]
            if(arr[left] <= arr[right]) {
                // check if we have taller building on the left
                if(arr[left] < leftMax) {
                    // at this point we have a taller building on the left and right so we can trap water on top of the current 
                    // building and since arr[left] < arr[right] and leftMax so we'll subtract leftMax - arr[left] to find trapped 
                    // water bcoz since we are traversing the smaller value always we'll not have value greater than arr[right] 
                    // before leftMax
                    totalWater += leftMax - arr[left];
                }else {
                    leftMax = arr[left]; // since there is no taller building then arr[left] means arr[left] is the taller one
                }

                left++; // move forward to the next building
            } else { // arr[right] < arr[left] -> there is a building on the left which is taller than current building at arr[right]
                // check if we have taller building on the right
                if(arr[right] < rightMax) {
                    // at this point we have a taller building on the left and right so we can trap water on top of the current 
                    // building and since arr[right] < arr[left] and rightMax so we'll subtract rightMax - arr[right] to find 
                    // trapped water bcoz since we are traversing the smaller value always we'll not have value greater than arr[left]
                    totalWater += rightMax - arr[right];
                } else {
                    rightMax = arr[right]; // since there is no taller building then arr[right] means arr[right] is the taller one
                }

                right--; // move backward to the next building
            }
        }
        
        return totalWater;
    }
}
