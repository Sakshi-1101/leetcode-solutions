import java.util.*;

public class WordBreak {

    public static void main(String[] args) {
        String s = "applepineapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");

        Set<String> words = new HashSet<>();

        for(String str: wordDict) {
            words.add(str);
        }

        boolean ans = checkWords(s, words);
        System.out.println(ans);

    }

    // TC:
    // SC: O(n) -> recursion depth
    // Approach:
    private static boolean checkWords(String s, Set<String> words) {
        Set<String> dict = new HashSet<>();

        // store all the words in a set
        for(String str: words) {
            dict.add(str);
        }

        return helper(s, 0, dict); 
    }

    // helper function to find if valid segments are present or not
    private static boolean helper(String s, int idx, Set<String> dict) {
        // base case
        if(idx == s.length()) {
            return true;
        }

        // traverse all the substrings starting from idx
        for(int i = idx + 1 ; i <= s.length() ; i ++) {
            // if set contains the curr generated substring
            if(dict.contains(s.substring(idx, i))){
                // check rest of string
                if(helper(s, i, dict)) {
                    return true;
                }
            }
        }

        // if no valid substring found in the dict then return false
        return false;   
    }
    
}
