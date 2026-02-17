
import java.util.Arrays;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] words = {"flower", "flow", "flight"};

        String prefix = findPrefix(words);

        System.out.println(prefix);
    }

    // TC: O(n * log (base 2) n) + O(m)
    /* 
        n = number of strings in the input array
        m = min length of string
        O(n * log (base 2) n) -> is for sorting the array of strings
        O(m) -> is for comparing the first and last strings in the sorted array to find the longest common prefix. 

    */
    // SC: O(m) -> where m is the length of the longest common prefix that we are storing in the StringBuilder ans.
    // Approach: In this approach, we first sort the array of strings in lexicographical order. After sorting, we only need to 
    //           compare the first and last strings in the sorted array to find the longest common prefix. This is because the 
    //           common prefix of the first and last string in the sorted array will be the longest common prefix for the entire 
    //           array. We then iterate through the characters of the first and last strings until we find a mismatch. The 
    //           characters that match up to that point will be our longest common prefix, which we return as a string.
    public static String findPrefix(String[] words) {
        // To store the longest common prefix
        StringBuilder ans = new StringBuilder();

        // sort the strings in lexicographical order bcoz the common prefix of the first and last string in the sorted array 
        // will be the longest common prefix for the entire array.
        Arrays.sort(words);

        //find the smallest(first) and largest(last) string
        String first = words[0];
        String last = words[words.length - 1];

        // we'll traverse till the min length of the first and last string because the common prefix can't be longer than the 
        // smallest string
        int min = Math.min(first.length() , last.length());

        // Compare characters of the first and last strings until a mismatch is found
        for(int i = 0 ; i < min ; i ++) {
            // Stop if characters are different
            if(first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }

            // Add matching character to result
            ans.append(first.charAt(i));
        }

        // Return the final common prefix
        return ans.toString();
    }
    
}
