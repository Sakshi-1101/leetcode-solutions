class Solution {
    public String largestOddNumber(String num) {
        // find the last position of the odd no. in the string
        int oddIdx = -1;

        for(int i = num.length() - 1 ; i >= 0 ; i --) {
            char ch = num.charAt(i);

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

        while(i <= oddIdx && num.charAt(i) == '0') {
            i++;
        }

        // return substring from first non-zero digit to last occurrence of the odd digit
        return num.substring(i, oddIdx + 1);
    }
}