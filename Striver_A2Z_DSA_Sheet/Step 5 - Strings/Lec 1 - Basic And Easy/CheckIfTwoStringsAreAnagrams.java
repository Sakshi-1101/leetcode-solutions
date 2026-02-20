import java.util.Arrays;

public class CheckIfTwoStringsAreAnagrams {

    public static void main(String[] args) {
        /* CONSTRAINT: s and t consist of lowercase English letters. */

        // String s = "aacc";
        // String t = "ccac";

        String s = "abc";
        String t = "bac";

        boolean ansBrute = checkAnagramBrute(s, t);
        boolean ansOptimal = checkAnagramOptimal(s, t);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(n) + O(n * log (base 2) n) -> for comparing and sorting
    // SC: O(1)
    // Approach: In this approach, we first check if the lengths of the two strings are different. If they are, then they cannot 
    //           be anagrams of each other and we return false. If they have the same length, we convert both strings to character 
    //           arrays and sort them. After sorting, if the two character arrays are equal, then the original strings are anagrams of each 
    //           other and we return true. Otherwise, we return false. 
    public static boolean checkAnagramBrute(String s, String t) {
        // If the lengths of the strings are different, they cannot be anagrams
        if(s.length() != t.length()) {
            return false;
        }

        // Convert the strings to character arrays bcoz strings are immutable in Java and we cannot sort them directly.
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();

        // Sort the arrays
        Arrays.sort(arrS);
        Arrays.sort(arrT);

        // Compare the sorted arrays. If they are equal, then s and t are anagrams of each other. Otherwise, they are not.

        /* OPTION 1 */
        // for(int i = 0 ; i < arrS.length ; i ++) {
        //     if(arrS[i] != arrT[i]) {
        //         return false;
        //     }
        // }
        
        // return true;

        /* OPTION 2 */
        // Unlike the == operator, which checks if two array references point to the same memory location, Arrays.equals() compares the actual values stored within the arrays.
        return Arrays.equals(arrS, arrT);

    }
    
    // TC: O(N) -> Each string is traversed once, and the frequency array is checked in constant time (26 iterations).
    // SC: O(1) -> as a fixed-size array of 26 elements is used regardless of the input size.
    // Approach: In this approach, we first check if the lengths of the two strings are different. If they are, then they cannot 
    //           be anagrams of each other. If they have the same length, we initialize a freq array of size 26 to store the freq 
    //           of each char in the strings. We traverse the first string and increment the freq of each char in the frequency 
    //           array. Then, we traverse the second string and decrement the freq of each char in the frequency array. Finally, 
    //           we traverse the frequency array and check if any frequency is not 0. we are doing this bcoz if s and t are 
    //           anagrams of each other, then the freq of each char in s should be the same as the freq of that char in t. Hence 
    //           they should cancel each other out and the freq of each char should be 0. If any freq is not 0, then s and t are 
    //           not anagrams of each other 
    public static boolean checkAnagramOptimal(String s, String t) {
        // If the lengths of the strings are different, they cannot be anagrams
        if(s.length() != t.length()) {
            return false;
        }

        // initialise a freq array to store the frequency of each character in the strings. 
        int[] freq = new int[26];

        // traverse the string s and increment the freq of each char in the freq array. 
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++; // we use ch - 'a' to convert the char to an idx in the range of 0 to 25
        }

        // traverse the string t and decrement the freq of each char in the freq array.
        for(int i = 0 ; i < t.length() ; i ++) {
            char ch = t.charAt(i);
            freq[ch - 'a']--; // we use ch - 'a' to convert the char to an idx in the range of 0 to 25
        }

        // traverse the freq array and check if any freq is not 0. If any freq is not 0, then s and t are not anagrams of each other and we return false.
        for(int i = 0 ; i < freq.length ; i ++) {
            if(freq[i] != 0) {
                return false;
            }
        }
        
        // if all freqs are 0, then s and t are anagrams of each other and we return true.
        return true;
    }   
}
