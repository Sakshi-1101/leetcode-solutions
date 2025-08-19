public class CheckIfPalindrome {

    public static void main(String[] args) {
        String str = "race a car";
        boolean ans = checkPalin(str, 0);    
        System.out.println(ans);
    }

    // TC: O(N) {Precisely, O(N/2) as we compare the elements N/2 times and swap them}
    // SC: O(N)
    public static boolean checkPalin(String str, int i){
        // Base case: if the index reaches the middle of the string, it means all characters matched
        if( i >= str.length() / 2){
            return true;
        }

        // check if the characters at the current index and its corresponding index from the end are equal
        if(str.charAt(i) != str.charAt(str.length() - i - 1)){ 
            return false;
        }

        return checkPalin(str, i + 1);

    }

}
