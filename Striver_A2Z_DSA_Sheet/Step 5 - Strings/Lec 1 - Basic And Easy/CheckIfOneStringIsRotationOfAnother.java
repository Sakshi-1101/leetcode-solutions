public class CheckIfOneStringIsRotationOfAnother {

    public static void main(String[] args) {
        String s = "rotation";
        String t = "tionrota";

        boolean ansBrute = rotateBrute(s, t);
        boolean ansOptimal = rotateOptimal(s, t);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2) -> generating all the N rotations and each comparison takes O(N) time.
    // SC: O(N) -> for the space needed to store each rotated string.
    // Approach: In this approach, we generate all possible rotations of the string t and check if any of those rotations is 
    //           equal to string s. We can generate a rotation by taking a substring of t from index i to the end and 
    //           concatenating it with the substring of t from the beginning to index i. We repeat this process for all indices 
    //           from 0 to the length of t - 1. If we find a rotation that matches s, we return true. If we exhaust all rotations 
    //           without finding a match, we return false.
    public static boolean rotateBrute(String s, String t) {
        // Strings must be same length to be rotations of each other
        if (s.length() != t.length()) {
            return false;
        }

        // traverse the string t
        for(int i = 0 ; i < t.length() ; i ++) {
            // generate the rotated string
            /*
                - The index i correctly partitions the string into two parts: [0, i) and [i, length)
                - This is used to check all possible rotations of the string.
             */
            String temp = t.substring(i) + t.substring(0, i);

            // check if the current generated rotated string is equal to s
            if(temp.equals(s)) {
                return true;
            }
        }

        return false;
    }

    // TC: O(N) -> checking for a substring in s + s is linear in time.
    // SC: O(N) ->  to store the concatenated string s + s.
    // Approach: In this approach, we take advantage of the fact that if string t is a rotation of string s, then t will be a 
    //           substring of s concatenated with itself (s + s). This is because when we concatenate s with itself, we effectively 
    //           create a string that contains all possible rotations of s as substrings. Therefore, we can simply check if t is a 
    //           substring of s + s. If it is, then t is a rotation of s and we return true. If it is not, then t is not a rotation 
    //           of s and we return false.
    public static boolean rotateOptimal(String s, String t) {
        // Strings must be same length to be rotations of each other
        if (s.length() != t.length()) {
            return false;
        }

        // Concatenate the string with itself
        String temp = s + s;

        // Check if the string t is a substring of the concatenated string
        return temp.contains(t);
    }
}
