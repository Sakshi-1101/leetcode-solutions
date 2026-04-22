import java.util.*;

public class GenerateBinaryStringsWithoutConsecutive1s {

    public static void main(String[] args) {
        int n = 3;

        List<String> strList = new ArrayList<>();

        generate("", n, strList);

        System.out.println(strList);
    }

    // TC: O(2^N) -> since each position has 2 choices.
    // SC: O(N) -> recursive stack space
    // Approach: In this approach, we will generate all the binary strings of length n using recursion. We will add 0 to the string 
    //           always, but we will add 1 only if the current string is empty or the last char of the current string is not 1. This 
    //           way, we can ensure that there are no consecutive 1s in the generated binary strings. 
    //           (This is an example of pruned recursion)
    public static void generate(String str, int n, List<String> strList) {
        // base case
        if(str.length() == n) {
            strList.add(str);
            return;
        }

        // add 0 to the string always
        generate(str + "0", n, strList);

        // add 1 only if curr string is empty or last char of curr string is not 1
        if(str.isEmpty() || str.charAt(str.length() - 1) != '1') {
            generate(str + "1", n, strList);
        }
    }
    
}
