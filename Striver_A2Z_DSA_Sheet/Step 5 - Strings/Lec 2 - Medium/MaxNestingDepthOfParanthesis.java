public class MaxNestingDepthOfParanthesis {

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";

        int depth = findMaxNestingDepth(s);

        System.out.println(depth);
    }

    // TC: O(n)
    // SC: O(1)
    // Approach: In this approach, we maintain two variables: maxDepth to keep track of the maximum depth encountered so far, 
    //           and currDepth to keep track of the current depth as we traverse the string. Whenever we encounter an opening 
    //           parenthesis '(', we increment currDepth, and whenever we encounter a closing parenthesis ')', we decrement 
    //           currDepth. The logic behind doing this is that an opening parenthesis indicates that we are going one level 
    //           deeper into the nesting, while a closing parenthesis indicates that we are coming back up one level. Since this 
    //           will always be a valid parentheses string, we can safely assume that currDepth will never become negative. 
    //           After processing each character, we update maxDepth to be the maximum of its current value and currDepth. 
    //           Finally, we return maxDepth as the result.
    public static int findMaxNestingDepth(String s) {
        int maxDepth = 0; // variable to keep track of the maximum depth encountered so far
        int currDepth = 0; // variable to keep track of the current depth as we traverse the string

        // traverse the string
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);

            // if we encounter an '(', we increment currDepth, and whenever we encounter a ')', we decrement currDepth.
            if(ch == '(') {
                currDepth++;
            } else if(ch == ')') {
                currDepth--;
            }

            // update maxDepth so far 
            maxDepth = Math.max(maxDepth, currDepth);
        }

        return maxDepth;
    }
    
}
