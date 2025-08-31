public class CountMaxConsecutive1sInArray {

    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 1, 1, 0, 1};

        int count = countOnes(arr);
        System.out.println(count);
    }

    // TC: 0(N)
    // SC: O(1)
    // Approch: We maintain a variable count that keeps a track of the number of consecutive 1’s while traversing the array. The other variable max_count maintains the maximum number of 1’s.
    public static int countOnes(int[] arr) {
        int count = 0;
        int maxCount = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] == 1){
                count++;
            } else {
                count = 0;
            }

            maxCount = Math.max(count, maxCount);
        }

        return maxCount;
    }
    
}
