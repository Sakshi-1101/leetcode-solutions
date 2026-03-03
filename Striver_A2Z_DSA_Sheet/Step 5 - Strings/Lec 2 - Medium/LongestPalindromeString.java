public class LongestPalindromeString {

    public static void main(String[] args) {
        String s = "babad";

        String ansBetter = findLongestPalindromeBetter(s);
        String ansOptimal = findLongestPalindromeOptimal(s);

        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this approach, we iterate through each character in the string and treat it as a center of a potential 
    //           palindrome. We then expand around this center to find the longest odd-length palindrome. We also consider the 
    //           gap between the current character and the next character as a center to find the longest even-length palindrome. 
    //           We keep track of the longest palindrome found so far and return it at the end. 
    public static String findLongestPalindromeBetter(String s) {
        // base case: if string is null or empty, return empty string
        if (s == null || s.length() < 1) {
            return "";
        }

        String ans = ""; // to store the longest palindrome found so far
        int maxLen = 0; // to store the length of the longest palindrome found so far

        // iterate through each character in the string and expand around it to find palindromes
        for(int i = 0 ; i < s.length() ; i ++) {
            // odd length palindrome (centered at i)
            String oddPalin = expand(s, i, i);
            // even length palindrome (centered between i and i + 1)
            String evenPalin = expand(s, i, i + 1);

            // update the longest palindrome found so far if the current odd is longer
            if(oddPalin.length() > maxLen) {
                maxLen = oddPalin.length();
                ans = oddPalin;
            }

            // update the longest palindrome found so far if the current even is longer
            if(evenPalin.length() > maxLen) {
                maxLen = evenPalin.length();
                ans = evenPalin;
            }
        }

        // return the longest palindrome found
        return ans;
    }

    // helper function to expand around the center and find the longest palindrome
    private static String expand(String s, int left, int right) {

        // expand as long as the characters at left and right are the same and within bounds
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left-- ;
            right++ ;
        }

        // after the loop, left and right are one step beyond the palindrome, so we return the substring from left + 1 to right
        return s.substring(left + 1, right);
    }


    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we can use Manacher's algorithm to find the longest palindromic substring in linear time. The 
    //           algorithm works by transforming the input string to handle both odd and even-length palindromes uniformly, and 
    //           then using an array to store the radius of the palindrome at each position. We also keep track of the rightmost 
    //           palindrome found so far to optimize the search for palindromes at subsequent positions. Finally, we find the 
    //           maximum radius and its corresponding center index to extract the longest palindromic substring from the original 
    //           string.
    public static String findLongestPalindromeOptimal(String s) {
        // base case: if string is null or empty, return empty string
        if (s == null || s.length() == 0)
            return "";

        // Transform the string
        // Example: "abba" → "^#a#b#b#a#$"
        StringBuilder sb = new StringBuilder();
        sb.append("^");  // starting boundary (to avoid bounds checking)

        // Insert '#' between characters to handle both odd and even-length palindromes uniformly
        for (char c : s.toCharArray()) {
            sb.append("#");
            sb.append(c);
        }

        sb.append("#$"); // ending boundary (to avoid bounds checking)

        String T = sb.toString(); // convert StringBuilder to String
        int n = T.length();

        //Array to store radius of palindrome at each index
        int[] P = new int[n];

        // rightmost palindrome means if we have a palindrome that extends to the rightmost position R starting from center C, 
        // then we can use the information of that palindrome to find the palindrome at the mirror position of i with respect to C.
        int C = 0; // center of rightmost palindrome
        int R = 0; // right boundary of that palindrome

        // traverse the\ string and calculate the radius of palindrome at each index
        for (int i = 1; i < n - 1; i++) {

            // Mirror position of i -> mirror position means if we have a palindrome centered at C that extends to R, then the
            //                         mirror position of i is the position that is equidistant from C on the opposite side.
            int mirror = 2 * C - i;

            // If i is inside the right boundary
            if (i < R) {
                // Copy minimum safe value
                // R - i => the maximum radius we can expand at i without going beyond R
                // P[mirror] => the radius of palindrome at the mirror position, which is the max radius we can copy from the mirror position without going beyond R
                P[i] = Math.min(R - i, P[mirror]);
            }

            // condition checks if the characters at the positions that are one step beyond the current radius on both sides of i are the same,
            // if they are the same, it means we can expand the palindrome centered at i by one more step, so we increment the 
            // radius P[i] by 1 and continue checking for further expansion until the characters at those positions are different
            // or we go out of bounds.
            while (T.charAt(i + (1 + P[i])) == T.charAt(i - (1 + P[i]))) { // Try to expand beyond current radius
                P[i]++;
            }

            // If palindrome expands beyond R update center and right boundary
            if (i + P[i] > R) {
                C = i; // the new centre will be i bcoz the palindrome centered at i extends beyond R
                R = i + P[i];
            }
        }

        // Find maximum length palindrome
        int maxLen = 0;
        int centerIndex = 0;

        // traverse the array P to find the maximum radius and its corresponding center index.
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                //The maximum radius will give us the length of the longest palindrome
                maxLen = P[i];
                // the center index will help us determine the starting position of that palindrome in the original string.
                centerIndex = i;
            }
        }

        // Convert back to original string index
        /*
            Formula explanation:
            - The center index in the transformed string T corresponds to a position in the original string s.
            - Each character in s is separated by a '#' in T, so the index in T is effectively doubled compared to s.
            - The length of the longest palindrome in T is given by maxLen, which represents the radius of the palindrome in T.
            - To convert this back to an index in s, we need to account for the fact that each char in s corresponds to two chars 
              in T (the character itself and the '#' before it).
            - Therefore, we divide the center index by 2 to get the corresponding index in s, and we also need to adjust for the 
              length of the palindrome by subtracting maxLen from the center index before dividing by 2.
         */ 

        int start = (centerIndex - maxLen) / 2; 

        // return the final longest palindromic substring 
        return s.substring(start, start + maxLen);        
    
    }
    
}
