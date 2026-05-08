import java.util.Stack;

public class BalancedParanthesis {
    
    public static void main(String[] args) {
        String s = "()[{}()]";

        boolean ans = isValid(s);
        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will use a stack to keep track of the opening brackets. Whenever we encounter a closing 
    //           bracket, we will check if the top of the stack has the corresponding opening bracket. If it does, we will pop 
    //           the stack and continue processing the string. If it doesn't, then we can return false because it means that 
    //           there is a mismatch in the brackets. After processing the entire string, if the stack is empty then we can 
    //           return true because it means that all brackets are balanced, otherwise we will return false.
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        // traverse the string
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            if(ch == ')') {
                if(!st.isEmpty() && st.peek() == '(') {
                    st.pop();
                } else {
                    // st.push(ch);
                    return false; // since we didn't find any opening bracket corresponding to this
                }
            } else if(ch == ']') {
                if(!st.isEmpty() && st.peek() == '[') {
                    st.pop();
                } else {
                    // st.push(ch);
                    return false;
                }
            } else if(ch == '}') {
                if(!st.isEmpty() && st.peek() == '{') {
                    st.pop();
                } else {
                    // st.push(ch);
                    return false;
                }
            } else { // if any opening bracket
                st.push(ch);
            }
        }

        // if stack is not empty after processing entire string
        if(!st.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
