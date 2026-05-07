
import java.util.*;

class Node {
    Node next;
    int data;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class ImplementStackUsingLL {
    // TC: O(1)
    // SC: O(N)
    public static class StackUsingInBuiltLL{
        LinkedList<Integer> list;
        int size;

        public StackUsingInBuiltLL() {
            list = new LinkedList<>();
            size = 0;

        }

        public void push(int ele) {
            list.add(ele);
            size++;
        }

        public int pop() {
            //check if list is empty
            if(isEmpty()) {
                System.out.println("Stack Empty");
                return -1;
            }

            int temp = list.getLast();
            list.removeLast();
            size--;
            return temp;
        }

        public int top() {
            //check if list is empty
            if(isEmpty()) {
                System.out.println("Stack Empty");
                return -1;
            }

            return list.getLast();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

    }

    // TC: O(1)
    // SC: O(N)
    public static class StackUsingLL {
        Node head;
        int size;

        public StackUsingLL() {
            head = null;
            size = 0;
        }

        public void push(int ele) {
            Node n = new Node(ele);

           // insert at beginning of LL
           n.next = head;
           head = n;
           size++;
        }

        public int pop() {
            // if stack is empty
            if(head == null) {
                System.out.println("stack empty");
                return -1;
            }

            // remove from beginning
            int val = head.data; // Get the top value
            Node temp = head; // Store the top temporarily

            head = head.next; // Update top to next node
            temp = null; // Delete old top node 

            size--;

            return val;
        }

        public int top() {
            // if stack is empty
            if(head == null) {
                System.out.println("stack empty");
                return -1;
            }

            return head.data;

        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // Stack using inbuilt LL
        StackUsingInBuiltLL st = new StackUsingInBuiltLL();

        System.out.println(st.isEmpty());

        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);

        System.out.println(st.isEmpty());
        System.out.println(st.size());

        st.pop();
        System.out.println(st.top());

        // Stacking using LL
        StackUsingLL stLL = new StackUsingLL();

        System.out.println(stLL.isEmpty());

        stLL.push(1);
        stLL.push(2);
        stLL.push(3);
        stLL.push(4);

        System.out.println(stLL.isEmpty());
        System.out.println(stLL.size());

        stLL.pop();
        System.out.println(stLL.top());

    }
    
}
