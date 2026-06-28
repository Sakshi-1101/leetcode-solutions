import java.util.Stack;

public class RemoveKDigits {

    public static void main(String[] args) {
        // String s = "541892";
        // int k = 2;
        String s = "1002991";
        int k = 3;
        // String s = "10200";
        // int k = 1;
        // String s = "10";
        // int k = 2;

        String ans = getSmallestNum(s, k);
        System.out.println(ans);
    }

    // TC: O(3*N) + O(K) ~ O(N + K)
    /*
        Explanation:
        1. O(N) for traversing the string and pushing elements to the stack.
        2. O(N) for popping all elements from the stack and appending to the string.
        3. O(N) for removing leading zeroes from the string or O(N) for reversing the string or O(N) for both operations. (some 0s removed and rest of the string reversed)
        4. O(K) for popping elements from the stack if k is still remaining after traversing the string. (worst possible scenario)
     */
    // SC: O(N) + O(N) = o(2 * N) -> O(N) for stack space + O(N) for string builder space
    // Approach: In this approach we will use a stack to keep track of the digits of the number. We will traverse the string 
    //           and for each char, we will check if the stack is not empty and k is still remaining and the top of the stack is 
    //           greater than the current char, then we can pop the top of the stack to remove that digit and decrease k by 1. We 
    //           will keep doing this until we have removed k digits or the top of the stack is less than or equal to the current 
    //           char. After that, we will push the current char to the stack. After traversing the string, if k is still remaining, 
    //           it means that the digits in the stack are in increasing order and we haven't removed k digits yet, so we can 
    //           remove the last k digits from the stack to get the smallest number possible. This is because the last k digits 
    //           in the stack will be the largest digits in the number formed by the stack, and removing them will give us a 
    //           smaller number. Finally, we will pop all elements in the stack and append into the string and remove the leading 
    //           zeroes and reverse the string to get the correct number since stack had the digits in rev order so string will 
    //           also have like that and return the final string with smallest number after removing k digits.
    public static String getSmallestNum(String s, int k) {
        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();

        // if k == length of string means remove all the digits
        if(k == s.length()) {
            return "0";
        }

        // traverse the string
        for(int i = 0 ; i < s.length() ; i ++) {
            
            // for each char check if the stack is not empty and k is still remaining and the top of the stack is greater than 
            // the current char, then we can pop the top of the stack to remove that digit and decrease k by 1
            while(!st.isEmpty() && k > 0 && st.peek() > s.charAt(i)) {
                st.pop();
                k--;
            }

            // push the current char to stack
            st.push(s.charAt(i));
        }

        // if K is still remaining
        /*
            if k is still remaining, it means that the digits in the stack are in increasing order and we haven't removed k digits 
            yet, so we can remove the last k digits from the stack to get the smallest number possible. This is because the last 
            k digits in the stack will be the largest digits in the number formed by the stack, and removing them will give us a 
            smaller number.
            Example: s = "123456", k = 2, stack will be [1, 2, 3, 4, 5, 6], we haven't removed any digit yet and we still have 
                                          to remove 2 digits, so we can remove the last 2 digits from the stack which are 5,6 to 
                                          get the smallest number possible which is "1234".
         */
        while(k > 0) {
            st.pop();
            k--;
        }

        // check if stack is empty, means we have removed all the digits, so we can return "0" as the smallest number possible
        if(st.isEmpty()) {
            return "0";
        }

        // pop all elements in the stack and append into the string
        while(!st.isEmpty()) {
            ans.append(st.pop());
        }

        // remove the leading zeroes
        while(ans.length() > 0 && ans.charAt(ans.length() - 1) == '0') {
            ans.deleteCharAt(ans.length() - 1);
        }

        // reverse the string to get the correct number since stack had the digits in rev order so string will also have like that 
        ans.reverse();

        // what if the stack had all 0s, and after removing leading 0s our string will become empty 
        if(ans.length() == 0) {
            return "0";
        }

        // return final string with smallest number after removing k digits
        return ans.toString();

    }
    
}
