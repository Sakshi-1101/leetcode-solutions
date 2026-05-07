class Node {
    Node next;
    int data;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class ImplementQueueUsingLL {

    // TC: O(1)
    // SC: O(N)
    public static class Queue {
        Node front;
        Node rear;
        int size;
        
        public Queue() {
            front = null;
            rear = null;
            size = 0;
            
        }

        public void push(int ele) {
            Node dataNode = new Node(ele);

            // if queue is empty
            if(rear == null) {
                rear = dataNode;
                front = dataNode;
            } else {
                rear.next = dataNode;
                rear = dataNode;
            }

            size++;
        }

        public int pop() {
            //if queue is empty
            if(front == null) {
                System.out.println("Queue empty");
                return -1;
            }

            int tempData = front.data;
            Node tempNode = front;

            front = front.next;
            tempNode = null;

            size--;
            return tempData;
            
        }

        public int peek() {
            //if queue is empty
            if(front == null) {
                System.out.println("Queue empty");
                return -1;
            }

            return front.data;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

    }

    public static void main(String[] args) {
        Queue q = new Queue();

        System.out.println(q.isEmpty());

        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);

        System.out.println(q.isEmpty());
        System.out.println(q.size());

        q.pop();
        System.out.println(q.peek());
    }
    
}
