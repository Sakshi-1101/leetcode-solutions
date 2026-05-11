import java.util.Stack;

public class InfixToPrefix {

    public static void main(String[] args) {
        String s = "x+y*z/w+u";

        String ans = infixToPrefix(s);
        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will first reverse the infix string and then convert it to postfix string using the same 
    //           approach as infix to postfix conversion. Finally, we will reverse the ans string to get the final prefix string.
    //           Note: Since we reversed the infix expression for infix -> prefix conversion, associativity behavior also gets 
    //           reversed.
    public static String infixToPrefix(String s) {
        StringBuilder sb = new StringBuilder(s); 

        // Step 1: Reverse the string
        sb.reverse(); 
        
        // reverse '(' and ')' brackets
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(')
                sb.setCharAt(i, ')');
            else if (sb.charAt(i) == ')')
                sb.setCharAt(i, '(');
        }

        // Step 2: convert the infix string to postfix
        StringBuilder postFixStr = infixToPostFix(sb.toString());

        // Step 3: Reverse the ans string
        String ans = postFixStr.reverse().toString();
        return ans;
    }

    public static StringBuilder infixToPostFix(String s) {
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
            } else { // current character is an operator

                // IMPORTANT:
                // Since we reversed the infix expression for infix -> prefix conversion, associativity behavior also gets reversed.
                if(ch == '^') {
                    // '^' is originally RIGHT associative. After reversing the expression, it behaves like LEFT associative.
                    // So if stack top has: higher precedence OR equal precedence we pop it first.
                    // Example:
                    // original: a^b^c
                    // reversed: c^b^a
                    // Therefore we use <=

                    while(!st.isEmpty() && prec(ch) <= prec(st.peek())) {
                        ans.append(st.pop());
                    }

                } else { // for +, -, *, /
                    // +, -, *, / are originally LEFT associative.
                    // After reversing the expression, they behave like RIGHT associative. So we pop only operators having 
                    // STRICTLY higher precedence. Equal precedence operators are NOT popped here.
                    // Therefore we use <

                    while(!st.isEmpty() && prec(ch) < prec(st.peek())) {
                        ans.append(st.pop());
                    }
                }

                // push current operator into stack
                st.push(ch);
            }
        }

        // Pop all the remaining elements from the stack and add it to the string
        while(!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans;
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
