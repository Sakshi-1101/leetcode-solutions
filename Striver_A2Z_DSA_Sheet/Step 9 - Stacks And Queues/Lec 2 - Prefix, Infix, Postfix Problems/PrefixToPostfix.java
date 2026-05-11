import java.util.Stack;

public class PrefixToPostfix {
    
    public static void main(String[] args) {
        String s = "*+ab-cd";

        String ans = prefixToPostfix(s);
        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will traverse the prefix string from back and whenever we encounter an operator, we will 
    //           pop two elements from stack and create a postfix string and push it back to stack. Finally, the element left 
    //           in stack will be our answer.
    public static String prefixToPostfix(String s) {
        Stack<String> st = new Stack<>();

        for(int i = s.length() - 1 ; i >= 0; i --) {
            char ch = s.charAt(i);

            // if an operand is encountered 
            if(Character.isLetterOrDigit(ch)) {
                st.push(String.valueOf(ch));
            } else { // if operator pop two elements from stack
                String op1 = st.pop();
                String op2 = st.pop();

                // create the postfix string
                String temp = op1 + op2 + ch;
                st.push(temp);
            }
        }

        // The final element in the stack is the result
        return st.peek();
    }
}
