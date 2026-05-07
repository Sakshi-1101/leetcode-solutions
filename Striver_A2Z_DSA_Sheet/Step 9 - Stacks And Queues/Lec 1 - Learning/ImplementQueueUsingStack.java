import java.util.*;

public class ImplementQueueUsingStack {

    // TC: O(N) -> push() and O(1) for rest func
    // SC: O(N)
    public static class QueueUsingStack1 {
        Stack<Integer> st1;
        Stack<Integer> st2;

        public QueueUsingStack1() {
           st1 = new Stack<>();
           st2 = new Stack<>();
        }

        public void push(int ele) {
            if(st1.isEmpty()) {
                st1.push(ele);
                return;
            }

            while(!st1.isEmpty()) {
                st2.push(st1.pop());
            }

            st1.push(ele);

            while(!st2.isEmpty()) {
                st1.push(st2.pop());
            }
        }

        public int pop() {
            if(st1.isEmpty()) {
                return -1;
            }
            
            return st1.pop();
        }

        public int peek() {
            if(st1.isEmpty()) {
                return -1;
            }

            return st1.peek();
            
        }

        public boolean isEmpty() {
           return st1.isEmpty();
        }

        public int size() {
            return st1.size();
        }

    }

    // TC: O(1) -> push() and O(N) for pop(), top()
    // SC: O(N)
    public static class QueueUsingStack2 {
        Stack<Integer> st1;
        Stack<Integer> st2;

        public QueueUsingStack2() {
           st1 = new Stack<>();
           st2 = new Stack<>();
        }

        public void push(int ele) {
            st1.push(ele);
        }

        public int pop() {
            if(st1.isEmpty()) {
                return -1;
            }

            // push all ele from st1 except the first added ele to st2
            while(st1.size() > 1) {
                st2.push(st1.pop());
            }

            // pop the rem ele from st1
            int val = st1.pop();

            // push back all ele from st2 to st1
            while(!st2.isEmpty()) {
                st1.push(st2.pop());
            }

            return val;
        }

        // TC: Worst case O(N) when st1 to st2 transfer happens otherwise O(1)
        private int popOptimised() {
            // If st2 stack is empty,
            // transfer all elements from st1 to st2
            if (st2.isEmpty()) {
                while (!st1.isEmpty()) {
                    st2.push(st1.pop());
                }
            }

             // Queue empty means means nothing in st2 and nothing in st1 to transfer
            if (st2.isEmpty()) {
                return -1;
            }

            return st2.pop();
        }

        public int peek() {
            if(st1.isEmpty()) {
                return -1;
            }

            // push all ele from st1 except the first added ele to st2
            while(st1.size() > 1) {
                st2.push(st1.pop());
            }

            // get the rem ele from st1 but dont remove it
            int val = st1.peek();

            // push back all ele from st2 to st1
            while(!st2.isEmpty()) {
                st1.push(st2.pop());
            }

            return val;
            
        }

        // TC: Worst case O(N) when st1 to st2 transfer happens otherwise O(1)
        private int peekOptimised() {
            // Transfer only when needed
            if (st2.isEmpty()) {

                while (!st1.isEmpty()) {
                    st2.push(st1.pop());
                }
            }

            // Queue empty means means nothing in st2 and nothing in st1 to transfer
            if (st2.isEmpty()) {
                return -1;
            }

            return st2.peek();
        }

        public boolean isEmpty() {
           return st1.isEmpty();
        }

        public int size() {
            return st1.size();
        }

    }

    public static void main(String[] args) {
        QueueUsingStack1 q1 = new QueueUsingStack1();

        System.out.println(q1.isEmpty());

        q1.push(1);
        q1.push(2);
        q1.push(3);
        q1.push(4);

        System.out.println(q1.isEmpty());
        System.out.println(q1.size());

        q1.pop();
        System.out.println(q1.peek());

        QueueUsingStack2 q2 = new QueueUsingStack2();

        System.out.println(q2.isEmpty());

        q2.push(1);
        q2.push(2);
        q2.push(3);
        q2.push(4);

        System.out.println(q2.isEmpty());
        System.out.println(q2.size());

        q2.pop();
        System.out.println(q2.peek());
    }
    
}
