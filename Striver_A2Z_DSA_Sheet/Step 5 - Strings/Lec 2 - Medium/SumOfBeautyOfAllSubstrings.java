public class SumOfBeautyOfAllSubstrings {
    
    public static void main(String[] args) {
        String s = "aabcbaa";

        // beauty of a substring = diff between the max freq of any char and the min freq of any char in that substring.
        int ans = findSumOfBeauty(s);

        System.out.println(ans);
    }

    // TC: O(n * n * 26) => O(n^2) 
    // SC: O(26) => O(1) for the frequency array
    // Approach: In this approach, we will generate all the substrings of the given string and for each substring, we will 
    //           calculate the beauty of that substring and add it to the answer. To calculate the beauty of a substring, we will 
    //           use a frequency array to store the frequency of characters in that substring. We will then find the maximum and 
    //           minimum frequency of characters in that substring and calculate the beauty as the diff between the maximum and 
    //           minimum frequency. Finally, we will return the sum of beauty of all substrings.
    /*
        NOTE: You can also use HashMap instead of freq array.
     */
    public static int findSumOfBeauty(String s) {
        int ans = 0; // to store the sum of beauty of all substrings

        // traverse the string
        for(int i = 0 ; i < s.length() ; i ++) {
            // to store the frequency of characters in the current substring
            int[] freq = new int[26];

            // generate all substrings starting from index i
            for(int j = i ; j < s.length() ; j ++) {
                char ch = s.charAt(j);
                freq[ch - 'a']++; // increment the frequency of the current character

                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                // find the maximum and minimum frequency of characters in the current substring
                for(int k = 0 ; k < freq.length ; k ++) {
                    if(freq[k] > 0) {
                        // update the maximum and minimum frequency
                        max = Math.max(max, freq[k]);
                        min = Math.min(min, freq[k]);
                    }
                }

                // add the beauty of the current substring to the answer
                ans += (max - min);
            }
        }

        return ans;
    }
}
