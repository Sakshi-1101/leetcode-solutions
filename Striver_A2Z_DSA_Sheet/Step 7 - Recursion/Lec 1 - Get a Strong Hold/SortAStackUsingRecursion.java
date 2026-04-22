import java.util.Stack;

public class SortAStackUsingRecursion {

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(1);
        st.push(2);
        st.push(3);

        // sort in desc order
        sortStack(st);

        System.out.println(st);
    }

    // TC: O(N^2) -> sortStack() → runs n times and Each insert may traverse entire stack
    // SC: O(N)
    // Approach: In this approach, we'll pop the top element, sort the stack recursively and then insert the popped element in the
    //           stack in the correct position by recursively iteration on the stack.
    public static void sortStack(Stack<Integer> st) {
        // base case
        if(st.empty()) {
            return;
        }

        // pop the top element
        int val = st.pop();

        // sort the stack recursively
        sortStack(st);

        // insert the popped element in the correct position
        insertEle(st, val);
    }

    // function to insert the ele in the correct position in the stack
    public static void insertEle(Stack<Integer> st, int val) {
        // base case
        if(st.empty() || val >= st.peek()) {
            st.push(val); // push element in stack
            return;
        }

        // pop the top of the stack
        int top = st.pop();

        // recursively reinsert the remaining elements in the stack with val in the correct position
        insertEle(st, val);

        // insert back the popped top element
        st.push(top);
    }
    
}
