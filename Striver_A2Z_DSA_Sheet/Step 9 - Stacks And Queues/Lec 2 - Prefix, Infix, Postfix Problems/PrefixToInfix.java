import java.util.Stack;

public class PrefixToInfix {

    public static void main(String[] args) {
        String s = "*+ab-cd";

        String ans = prefixToInfix(s);
        System.out.println(ans);
        
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will traverse the prefix string from back and whenever we encounter an operator, we will 
    //           pop two elements from stack and create an infix string and push it back to stack. Finally, the element left 
    //           in stack will be our answer.
    public static String prefixToInfix(String s) {
        Stack<String> st = new Stack<>();

        for(int i = s.length() - 1 ; i >= 0 ; i --) {
            char ch = s.charAt(i);

            // if an operand is encountered 
            if(Character.isLetterOrDigit(ch)) {
                st.push(String.valueOf(ch));
            } else { // if operator pop two elements from stack
                String op1 = st.pop();
                String op2 = st.pop();

                // since traversing from back so op1 will be appended first 
                String temp = "(" + op1 + ch + op2 + ")";
                st.push(temp);
            }
        }

        // The final element in the stack is the result
        return st.peek();
    }
    
}
