import java.util.HashMap;

public class IsomorphicStrings {

    public static void main(String[] args) {
        String s = "paper";
        String t = "title";

        boolean ansBrute = checkIfIsomorphicBrute(s, t);
        boolean ansOptimal = checkIfIsomorphicOptimal(s, t);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(n)
    // SC: O(k) -> k = no. of distinct chars mapped (worst-case k = n). If the char set is bounded (e.g., ASCII), then it is O(1). 
    //             Note you use two maps → 2*k entries (still O(k)).
    // Approach: In this approach, we use two HashMaps to store the character mappings from string s to string t and from string 
    //           t to string s. We iterate through the characters of both strings simultaneously and check for the following 
    //           conditions:
    //            1. If the current character from string s (ch1) is already mapped to a character in string t (ch2) and the 
    //               mapped character is not ch2, then the strings are not isomorphic and we return false.
    //            2. If the current character from string t (ch2) is already mapped to a character in string s (ch1) and the 
    //               mapped character is not ch1, then the strings are not isomorphic and we return false.
    //            3. If neither of the above conditions are true, we create the mapping between ch1 and ch2 in both maps. If we 
    //               successfully iterate through all characters without finding any inconsistencies in the mappings, then the 
    //               strings are isomorphic and we return true.
    public static boolean checkIfIsomorphicBrute(String s, String t) {
        // If the lengths of the strings are different, they cannot be isomorphic
        if(s.length() != t.length()) {
            return false;
        }

        // HashMaps to store the character mappings from s to t and from t to s
        HashMap<Character, Character> mapST = new HashMap<>();
        HashMap<Character, Character> mapTS = new HashMap<>();

        // Iterate through the characters of both strings simultaneously
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            // if map contains ch1 and the mapped character is not ch2, then s and t are not isomorphic
            if(mapST.containsKey(ch1) && mapST.get(ch1) != ch2) {
                return false;
            }

            // if map contains ch2 and the mapped character is not ch1, then s and t are not isomorphic
            if(mapTS.containsKey(ch2) && mapTS.get(ch2) != ch1) {
                return false;
            }

            // if map does not contain ch1 and ch2, then we create the mapping between ch1 and ch2 in both maps
            mapST.put(ch1, ch2);
            mapTS.put(ch2, ch1);
        }

        // if all chars have unique and consistent mappings, then s and t are isomorphic
        return true;
    }

    // TC: O(n)
    // SC: O(1) -> since the space used by the arrays is constant (256 fixed size) regardless of input size
    // Approach: In this approach, we use two integer arrays to track the last seen positions of characters in strings s and t. 
    //           We iterate through the characters of both strings simultaneously and check for the following conditions:
    //            1. If the last seen positions of the current characters from string s (ch1) and string t (ch2) do not match, 
    //               then the strings are not isomorphic and we return false.
    //            2. If the last seen positions match, we update the last seen positions of ch1 and ch2 to the current index + 1 
    //               (to avoid confusion with the default value of 0 in the arrays).
    //           If we successfully iterate through all characters without finding any inconsistencies in the last seen positions, 
    //           then the strings are isomorphic and we return true.  
    public static boolean checkIfIsomorphicOptimal(String s, String t) {
        // If the lengths of the strings are different, they cannot be isomorphic
        if(s.length() != t.length()) {
            return false;
        }

        // Arrays to track last seen positions of characters in s and t. We use 256 to cover all ASCII characters.
        int[] arrS = new int[256];
        int[] arrT = new int[256];

        // Loop through all characters in the strings simultaneously
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            // If the last seen positions of ch1 and ch2 do not match, then s and t are not isomorphic
            if(arrS[ch1] != arrT[ch2]) {
                return false;
            }

            // Update the last seen positions of ch1 and ch2 to the current index + 1 (to avoid confusion with default value 0)
            arrS[ch1] = i + 1;
            arrT[ch2] = i + 1;
        }

        // if all chars have unique and consistent mappings, then s and t are isomorphic
        return true;
    }
    
}
