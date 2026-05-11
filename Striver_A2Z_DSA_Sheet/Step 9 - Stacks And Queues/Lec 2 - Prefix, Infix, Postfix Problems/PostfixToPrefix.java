import java.util.Stack;

public class PostfixToPrefix {

    public static void main(String[] args) {
        String s = "abc*+d-";

        String ans = postFixToPrefix(s);
        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will traverse the postfix string from front and whenever we encounter an operator, we 
    //           will pop two elements from stack and create a prefix string and push it back to stack. Finally, the element 
    //           left in stack will be our answer. 
    public static String postFixToPrefix(String s) {
        Stack<String> st = new Stack<>();

        for(int i = 0 ; i < s.length(); i ++) {
            char ch = s.charAt(i);

            // if an operand is encountered 
            if(Character.isLetterOrDigit(ch)) {
                st.push(String.valueOf(ch));
            } else { // if operator pop two elements from stack
                String op1 = st.pop();
                String op2 = st.pop();

                // create the prefix string
                String temp = ch + op2 + op1;
                st.push(temp);
            }
        }

        // The final element in the stack is the result
        return st.peek();
    }
    
}
