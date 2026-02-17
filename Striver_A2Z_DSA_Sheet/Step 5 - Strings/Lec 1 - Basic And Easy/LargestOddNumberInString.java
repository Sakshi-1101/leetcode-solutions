public class LargestOddNumberInString {

    public static void main(String[] args) {
        String s = "0214638";

        String ans = findLargestNum(s);

        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we iterate through the input string from the end to find the last occurrence of an odd digit. 
    //           If we find an odd digit, we store its index and break the loop. If no odd digit is found, we return an empty 
    //           string. If an odd digit is found, we then skip any leading zeroes from the start of the string up to the index 
    //           of the last odd digit. Finally, we return the substring starting from the first non-zero character to the last 
    //           occurrence of the odd digit, which represents the largest odd number that can be formed from the digits in the 
    //           string.
    public static String findLargestNum(String s) {
        // find the last position of the odd no. in the string
        /*
            NOTE: We are looking for the last occurrence of an odd digit because the largest odd number that can be formed from 
                  the digits in the string will end with the last odd digit. By starting from the end of the string, we ensure 
                  that we capture the largest possible odd number, as any digits before the last odd digit can be included in the 
                  final result without affecting its odd nature.
         */
        int oddIdx = -1;

        for(int i = s.length() - 1 ; i >= 0 ; i --) {
            char ch = s.charAt(i);

            // as soon as an odd no. is encouter we'll break since we need last odd no. in string
            if((ch - '0') % 2 == 1) {
                oddIdx = i;
                break;
            }
        }

        // return an empty string, if odd no. not found
        if(oddIdx == -1) {
            return "";
        }

        // Skip leading zeroes up to the odd digit
        int i = 0;

        while(i <= oddIdx && s.charAt(i) == '0') {
            i++;
        }

        // return substring from first non-zero digit to last occurrence of the odd digit
        return s.substring(i, oddIdx + 1);
    }
    
}
