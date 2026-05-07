import java.util.*;

public class ImplementStackUsingQueue {

    // TC: O(N) -> push() and O(1) -> rest func
    // SC: O(K) -> K = storing k elements in the queue
    public static class StackUsingSingleQueue{
        Queue<Integer> q;

        public StackUsingSingleQueue() {
            q = new LinkedList<>();
        }

        public void push(int ele) {
            // push the element
            q.add(ele);

            // move all the elements at the end of queue after the current element so that curr element is at the start of
            // queue (or top of stack)
            int originalSize = q.size() - 1;  // Size before adding ele
            for (int i = 0; i < originalSize; i++) {
                q.add(q.poll());
            }
        }

        public int pop() {
            int data = q.peek();
            q.poll();

            return data;
        }

        public int top() {
            return q.peek();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }

        public int size() {
            return q.size();
        }

    }

    // TC: O(N) -> push() and O(1) -> rest func
    // SC: O(K) -> K = storing k elements in the queue
    public static class StackUsingTwoQueues{
        Queue<Integer> q1;
        Queue<Integer> q2;

        public StackUsingTwoQueues() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        public void push(int ele) {
            // if queue is empty
            if(q1.isEmpty()) {
                q1.add(ele);
                return;
            } 

            // move all the elements from q1 to q2
            while(!q1.isEmpty()) {
                q2.add(q1.poll());
            }

            // add new ele to q1
            q1.add(ele);

            // move back all the ele from q2 to q1
            while(!q2.isEmpty()) {
                q1.add(q2.poll());
            }
           
        }

        public int pop() {
            if(q1.isEmpty()) {
                return -1;
            }

            int data = q1.peek();

            q1.poll();
            return data;
        }

        public int top() {
            if(q1.isEmpty()) {
                return -1;
            }

            return q1.peek();
        }

        public boolean isEmpty() {
            return q1.isEmpty();
        }

        public int size() {
            return q1.size();
        }
    }

    public static void main(String[] args) {
        StackUsingSingleQueue st = new StackUsingSingleQueue();

        System.out.println(st.isEmpty());

        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);

        System.out.println(st.isEmpty());
        System.out.println(st.size());

        st.pop();
        System.out.println(st.top());

        StackUsingTwoQueues st2 = new StackUsingTwoQueues();

        System.out.println(st2.isEmpty());

        st2.push(1);
        st2.push(2);
        st2.push(3);
        st2.push(4);

        System.out.println(st2.isEmpty());
        System.out.println(st2.size());

        st2.pop();
        System.out.println(st2.top());

    }
    
}
