public class ImplementStackUsingArrays {

    // TC: O(1) for push, pop, top and isEmpty
    // SC: O(N) -> length of array
    public static class Stack{
        int[] data;
        int idx;

        public Stack() {
            data = new int[10];
            idx = -1;
        }

        public void push(int ele) {
            // check if the stack is full
            if(idx >= data.length - 1) {
                System.out.println("Stack Overflow");
                return;
            }

            idx++;
            data[idx] = ele;
        }

        public int pop() {
            // check if the stack is empty
            if(isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }

            int temp = data[idx];
            idx --;
            return temp;
        }

        public int top() {
            // check if the stack is empty
            if(isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }

            return data[idx];
        }

        public boolean isEmpty() {
            // if idx is -1 means there are no elements in the stack
            return idx == -1;
        }

        public int size() {
            // size of the stack will be idx + 1 because idx starts from -1 and for each push we are incrementing idx by 1
            return idx + 1;
        }

    }

    public static void main(String[] args) {
        Stack st = new Stack();

        System.out.println(st.isEmpty());

        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);

        System.out.println(st.isEmpty());
        System.out.println(st.size());

        st.pop();
        System.out.println(st.top());

    }
    
}
