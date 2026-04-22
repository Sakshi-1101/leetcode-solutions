import java.util.Stack;

public class ReverseStackUsingRecursion {

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(1);
        st.push(2);
        st.push(3);

        // sort in desc order
        reverseStack(st);

        System.out.println(st);
    }

    // TC: O(N^2)
    // SC: O(N)
    // Approach: In this approach, the logic is "Move everything out → insert → move back". We'll pop the top element and then
    //           recursively reverse the stack and then insert the popped element at the bottom of the stack recursively.
    public static void reverseStack(Stack<Integer> st) {
        // base case
        if(st.empty()) {
            return;
        }

        // pop the top element of stack
        int val = st.pop();

        // recursively reverse the rest of the elements of the stack
        reverseStack(st);

        // insert the popped element at the bottom of the stack
        insertValAtBottom(st, val);
    }

    // helper function to insert the element at bottom of stack
    public static void insertValAtBottom(Stack<Integer> st, int val) {
        // base case
        if(st.empty()) {
            st.push(val); // push the element in stack
            return;
        }

        // pop the top of the stack
        int top = st.pop();

        // recursively insert the element at the bottom of the stack
        insertValAtBottom(st, val);

        // push the popped element back to stack
        st.push(top);
    }
    
}
