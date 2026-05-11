import java.util.Stack;

public class InfixToPostFix {

    public static void main(String[] args) {
        String s = "a+b*(c^d-e)^(f+g*h)-i";

        String ans = infixToPostFix(s);
        System.out.println(ans);
    }

    // TC: O(n)
    // SC: O(n)
    // Approach: Use a stack to store operators and pop them when a higher precedence operator is encountered or when a closing 
    //           parenthesis is found and add them to the ans string. Finally, pop all the remaining operators from the stack and 
    //           add it to the ans string.
    public static String infixToPostFix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);

            // if char is an operand, add it to ans string
            if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
                ans.append(ch);
            } else if(ch == '(') {
                st.push(ch);
            } else if(ch == ')') {
                // pop from stack until an ‘(‘ is encountered and add the operators into the ans string
                while(!st.isEmpty() && st.peek() != '(') {
                    ans.append(st.pop());
                }
                st.pop(); // remove the ( bracket from stack
            } else { // if ch is an operator
                // Pop from stack and add to ans string until the precedence of the current operator <= precedence of the operator 
                // at the top of the stack
                while(!st.isEmpty() && prec(ch) <= prec(st.peek())) {
                    ans.append(st.pop());
                }

                // Push the current operator to the stack
                st.push(ch);
            }
        }

        // Pop all the remaining elements from the stack and add it to the string
        while(!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.toString();
    }

    // Function to return precedence of operators
    public static int prec(char c) {
        if (c == '^')  // ^ operator has highest precedence
            return 3;
        else if (c == '/' || c == '*')  // * and / have higher precedence than addition
            return 2;
        else if (c == '+' || c == '-')  // + and - have lowest precedence
            return 1;
        else
            return -1;
    }

    
}
