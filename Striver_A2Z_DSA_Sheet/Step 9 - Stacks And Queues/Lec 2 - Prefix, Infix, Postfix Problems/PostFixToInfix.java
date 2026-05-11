import java.util.Stack;

public class PostFixToInfix {

    public static void main(String[] args) {
        String s = "ab*cd/+";

        String ans = postfixToInfix(s);
        System.out.println(ans);
        
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will traverse the postfix string from front and whenever we encounter an operator, we will 
    //           pop two elements from stack and create an infix string and push it back to stack. Finally, the element left in 
    //           stack will be our answer.
    public static String postfixToInfix(String s) {
        Stack<String> st = new Stack<>();

        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);

            // if an operand is encountered 
            if(Character.isLetterOrDigit(ch)) {
                st.push(String.valueOf(ch));
            } else { // if operator pop two elements from stack
                String op1 = st.pop();
                String op2 = st.pop();

                // since op2 will be popped first so we'll create the string accordingly
                String temp = "(" + op2 + ch + op1 + ")";
                st.push(temp);
            }
        }

        // The final element in the stack is the result
        return st.peek();
    }
    
}
