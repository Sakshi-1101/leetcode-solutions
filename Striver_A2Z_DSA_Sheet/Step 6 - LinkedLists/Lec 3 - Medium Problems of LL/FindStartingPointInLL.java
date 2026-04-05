import java.util.HashMap;

class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class FindStartingPointInLL {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(3, null);
        Node n3 = new Node(4, null);
        Node n4 = new Node(5, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        Node headBrute = findStartBrute(head);
        Node headOptimal = findStartOptimal(head);

        System.out.println(headBrute.data);
        System.out.println(headOptimal.data);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(N * LogN) -> we traverse the entire LL once and store and retrieve nodes from the hashmap. Map operations have a worst time space complexiy of O(LogN).
    // SC: o(N) -> hashmap space
    // Approach: In this approach, we will use a hashmap to store the nodes of the LL. We will traverse the LL and for each node, 
    //           we will check if it is already present in the hashmap. If it is present, then it means that it is already visited
    //           and hence there is a loop in the LL and we will return node. If it is not present, then we will add it to the 
    //           hashmap and continue traversing the LL. If we reach the end of the LL (i.e., temp becomes null), then it means 
    //           that there is no loop in the LL and we will return null.
    public static Node findStartBrute(Node head) {
        HashMap<Node, Boolean> map = new HashMap<>(); // map to store the nodes of the LL

        Node temp = head;

        // Traverse the LL and check if each node is already present in the hashmap
        while(temp != null) {
            // if a node is already present in the hashmap, then it means that it is already visited and hence there is a 
            // loop in the LL
            if(map.containsKey(temp)) {
                return temp;
            }

            // If not is not there in map, add it to the map and continue traversing the LL
            map.put(temp, true);
            temp = temp.next;
        }

        // If we reach the end of the LL (i.e., temp becomes null), then it means that there is no loop in the LL
        return null;

    }

    // TC: O(n) -> we traverse the entire LL once in the worst case (when there is no loop in the LL)
    // SC: O(1) -> we only use two pointers
    // Approach: In this approach, we'll use two pointers, one slow and one fast. The slow pointer will move one step at a time, 
    //           while the fast pointer will move two steps at a time. If there is a loop in the LL, then the fast pointer will 
    //           eventually meet the slow pointer. Once the loop is detected, we will move the slow pointer to the head of the LL 
    //           and keep the fast pointer at the meeting point. Then, we will move both pointers one step at a time until they 
    //           meet each other. The point at which they meet will be the starting point of the loop.
    /* TORTOISE AND HARE ALGORITHM (FLOYD'S CYCLE DETECTION) */
    public static Node findStartOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        // traverse the LL such that fast pointer moves 2 steps and slow one step
        while(fast != null && fast.next != null) { // OR while(fast.next != null && fast.next.next != null)
            slow = slow.next;
            fast = fast.next.next;

            // If there is a loop in the LL, means cycle is present 
            if(slow == fast) {
               // to find the starting point of the loop, we will move the slow pointer to the head of the LL
               slow = head;

               // Now, we will move both the slow and fast pointers one step at a time until they meet each other. The point at 
               // which they meet will be the starting point of the loop.
               /*
                    Explanation: 
                    - When the fast and slow pointers meet, it means that they are both at the same distance from the starting point of 
                    the loop.
                    - Let's say the distance from the head of the LL to the starting point of the loop is 'x' and the distance from 
                    the starting point of the loop to the point where the fast and slow pointers meet is 'y'.
                    - When we move the slow pointer to the head of the LL and keep the fast pointer at the meeting point, and then 
                    move both pointers one step at a time, the slow pointer will move 'x' steps to reach the starting point of the 
                    loop, while the fast pointer will move 'y' steps to reach the same point.
                    - Since both pointers are moving at the same speed, they will meet at the starting point of the loop after 'x' 
                    steps, which is the point where the loop starts.
                */
               while(slow != fast) {
                slow = slow.next;
                fast = fast.next;
               }

               // at the end of the above loop, both slow and fast pointers will be pointing to the starting point of the loop
               return slow;
            }
        } 

        // If there is no loop, then no starting point
        return null;
    }


    
}
