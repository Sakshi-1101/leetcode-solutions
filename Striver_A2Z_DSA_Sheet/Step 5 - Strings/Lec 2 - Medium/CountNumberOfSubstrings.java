
import java.util.*;

public class CountNumberOfSubstrings {

    public static void main(String[] args) {
        // String s = "pqpqs";
        // int k = 2;

        String  s = "abcaa";
        int k = 3  ;

        int ans = countSubstringsOptimal(s, k);

        System.out.println(ans);
    }

    // will work for most of the test cases but can fail for larger test cases since TC: O(N^2)
    public static int countSubstringsBruteMyApproach(String s, int k) {
        Set<Character> set = new HashSet<>();
        int val = 0 ;

        for(int i = 0 ; i < s.length() ; i ++) {
            set.clear();

            for(int j = i ; j < s.length() ; j ++) {
                // String str = s.substring(i, j);

                if(!set.contains(s.charAt(j))){
                    set.add(s.charAt(j));
                }

                if(set.size() == k) {
                    val++;
                }

            }
        }

        return val;
    }

    // TC: O(N)
    // SC: O(1) -> map size bounded by 26 characters for alphabets.
    // Approach: In this approach, we use a sliding window technique to count the no. of substrings with at most k distinct 
    //           chars. We maintain a frequency map to keep track of the chars in the current window. As we expand the right 
    //           pointer of the window, we add chars to the map and check if the no. of distinct chars exceeds k. If it does, 
    //           we shrink the window from the left until we have at most k distinct chars. For each valid window, we calculate 
    //           the no. of substrings that can be formed within that window and add it to our total count. Finally, we return the 
    //           total count of substrings with exactly k distinct chars by subtracting the count of substrings with at most 
    //           (k - 1) distinct chars from the count of substrings with at most k distinct chars. 
    public static int countSubstringsOptimal(String s, int k) {
        // exactly k distinct characters = atmost k distinct characters - atmost (k - 1) distinct characters
        return atmost(s, k) - atmost(s, k - 1);
    }

    // sliding window approach to count the number of substrings with at most k distinct characters
    private static int atmost(String s, int k) {
        int left = 0; // left pointer of the sliding window
        HashMap<Character, Integer> map = new HashMap<>(); // to store the frequency of characters in the current window
        int total = 0; // to store the count of valid substrings with at most k distinct characters

        // Expand the right pointer of the sliding window by iterating through the string
        for(int right = 0 ; right < s.length() ; right ++) {
            char ch = s.charAt(right);
            
            // Add the current character to the frequency map and update its count
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // If the number of distinct chars in the current window exceeds k, we need to shrink the window from the left
            while(map.size() > k) {
                char c = s.charAt(left);
                map.put(c, map.get(c) - 1); // decrease the freq of the leftmost char as we are going to move the left pointer ahead

                // If the freq of the leftmost char becomes 0 after decrementing, we remove it from the map to maintain the 
                // correct count of distinct characters
                if(map.get(c) == 0) {
                    map.remove(c);
                }

                left++; // move the left pointer ahead to shrink the window until we have at most k distinct chars in the window
            }

            // At this point, the current window (from left to right) has at most k distinct chars. The no. of substrings that 
            // can be formed within the current window is (right - left + 1). 
            // We add this count to the total count of valid substrings.
            total += (right - left + 1);
       }

       return total;
    }
    
}
