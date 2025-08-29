class Solution {
    public int removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

         int i = 0;
        
        // we move ?j? till we don't get a number arr[j] which is different from arr[i]. 
        //As we got a unique number we will increase the i pointer and update its value by arr[j]
        for(int j = 1 ; j < arr.length ; j ++){

            if(arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }

        return i + 1; // i + 1 to return total unique elements
        
    }
}