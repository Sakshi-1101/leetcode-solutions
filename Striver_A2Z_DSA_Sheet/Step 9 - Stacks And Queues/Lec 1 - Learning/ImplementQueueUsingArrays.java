public class ImplementQueueUsingArrays {

    // TC: O(1) for all the operations 
    // SC: O(N) => for the length of array 
    public static class Queue {
        int[] data;
        int front;
        int rear;
        int size; // current number of elements in the queue

        public Queue() {
            data = new int[10];
            front = -1;
            rear = -1;
            size = 0;
        }

        public void push(int ele) {
            // check if the queue is full
            if (size == data.length) {
                System.out.println("Queue Full");
                return;
            }

            // if the queue is empty, initialize front and rear to 0
            if (size == 0) {
                front = 0;
                rear = 0;
            } else { // move rear to the next position in a circular manner
                rear = (rear + 1) % data.length;
            }

            data[rear] = ele;
            size++;
        }

        public int pop() {
            // check if the queue is empty
            if (size == 0) {
                System.out.println("Queue Empty");
                return -1;
            }

            int temp = data[front];
            size--;

            // if the queue becomes empty after popping, reset front and rear to -1
            if (size == 0) {
                front = -1;
                rear = -1;
            } else { // move front to the next position in a circular manner
                front = (front + 1) % data.length;
            }

            return temp;
        }

        public int peek() {
            // check if the queue is empty
            if (size == 0) {
                System.out.println("Queue Empty");
                return -1;
            }

            return data[front];
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
