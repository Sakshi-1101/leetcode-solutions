class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class MiddleOfLL {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(3, null);
        Node n3 = new Node(4, null);
        Node n4 = new Node(5, null);
        Node n5 = new Node(6, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        int midEleBrute = findMidElementBrute(head);
        int midEleOptimal = findMidElementOptimal(head);

        System.out.println(midEleBrute);
        System.out.println(midEleOptimal);
    }

    // TC: O(n + n/2)
    // SC: O(1)
    // Approach: In this approach, we will first count the total no. of elements in the LL and then we will find the mid element 
    //           by traversing the LL again until we reach the mid element. If the no. of elements in the LL is even, then we will
    //           return the next element of the mid element as the mid element bcoz in case of even no. of elements, there are two 
    //           mid elements and we have to return the second one.
    public static int findMidElementBrute(Node head) {
        Node temp = head;
        int count = 0;

        while(temp != null) {
            temp = temp.next;
            count++;
        }

        temp = head;
        int mid = count / 2 + 1; // bcoz we have to return the second mid element in case of even no. of elements in the LL

        // Traverse the LL until we reach the mid element
        while(temp != null) {
            mid --; // Decrease the mid value by 1 until it becomes 0, which means we have reached the mid element

            // If mid becomes 0, then we have reached the mid element and we will break the loop 
             if(mid == 0) {
                break;
            }

            temp = temp.next; // Move to the next node in the LL if mid is not 0 yet
        }

        // return mid element
        return temp.data;

    }

    // TC: O(n/2) 
    // SC: O(1)
    // Approach: In this approach, we will use two pointers, one slow and one fast. The slow pointer will move one step at a time, 
    //           while the fast pointer will move two steps at a time. When the fast pointer reaches the end of the LL, the slow 
    //           pointer will be at the mid element. If the no. of elements in the LL is even, then we will return the next element 
    //           of the slow pointer as the mid element bcoz in case of even no. of elements, there are two mid elements and we have to return the second one.
    // TORTOISE AND HARE ALGORITHM ((Floyd’s Cycle Detection))
    public static int findMidElementOptimal(Node head) {
        Node temp = head;

        int count = 0;

        // Count the total no. of elements in the LL
        while(temp != null) {
            temp = temp.next;
            count++;
        }

        Node slow = head;
        Node fast = head;

        // Move the fast pointer two steps at a time and the slow pointer one step at a time until the fast pointer reaches the end of the LL
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // if the no. of elements in the LL is even, then we will return the next element of the slow pointer as the mid element 
        if(count % 2 == 0) {
            return slow.next.data;
        } else { // if the no. of elements in the LL is odd, then we will return the slow pointer as the mid element
            return slow.data;
        }
    }
    
}
