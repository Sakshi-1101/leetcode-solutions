
import java.util.HashMap;

class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class LengthOfLoopInLL {

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

        int headBrute = lengthBrute(head);
        int headOptimal = lengthOptimal(head);

        System.out.println(headBrute);
        System.out.println(headOptimal);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will use a hashmap to store the nodes of the LL and their positions. We will traverse the LL 
    //           and for each node, we will check if it is already present in the hashmap. If it is present, then it means that it
    //           is already visited and hence there is a loop in the LL and we will return the length of the loop by calculating 
    //           the difference between the current position and the position of the node in the hashmap. If it is not present, 
    //           then we will add it to the hashmap and continue traversing the LL. If we reach the end of the LL 
    //           (i.e., temp becomes null), then it means that there is no loop in the LL and we will return 0.
    public static int lengthBrute(Node head) {
        HashMap<Node, Integer> map = new HashMap<>(); // map to store the nodes of the LL and their positions

        // position variable to keep track of the current position of the node in the LL
        int pos = 0;

        Node temp = head;

        // Traverse the LL and check if each node is already present in the hashmap
        while(temp != null) {
            // if a node is already present in the hashmap, that means loop is present in the LL
            if(map.containsKey(temp)) {
                // calculate the length of the loop by calculating the diff between the curr pos and the last visited pos of the node in the map
                int len = pos - map.get(temp) + 1;
                return len;
            }
            
            // if the node is not present in the hashmap, then add it to the hashmap and continue traversing the LL
            pos++;
            map.put(temp, pos);
            temp = temp.next;
        }

        // if no loop detected in the LL, then return 0
        return 0;
    }
    
    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will use the Tortoise and Hare algorithm to detect the loop in the LL and then we will 
    //           calculate the length of the loop by traversing the loop until we reach the meeting point slow and fast pointers 
    //           of the loop again.
    public static int lengthOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        // Move the fast pointer two steps at a time and the slow pointer one step at a time until the fast pointer reaches the end of the LL
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // if the slow and fast pointers meet, then there is a loop in the LL
            if(slow == fast) {
                return getLengthLoop(slow); // calculate the length of the loop 
            }
        }

        // if no loop detected in the LL, then return 0
        return 0;
    }

    // Helper function to calculate the length of the loop by traversing the loop until we reach the meeting point slow and fast pointers of the loop again
    private static int getLengthLoop(Node slow) {
        Node temp = slow; // Initialize a temporary pointer to the meeting point of the loop
        int len = 1; // count the current node in the loop

        // Traverse the loop until we reach the meeting point again and count the number of nodes in the loop
        while(temp.next != slow) {
            temp = temp.next;
            len++;
        }

        // return the length of the loop
        return len;
    }

}

