import java.util.ArrayList;
import java.util.Collections;

class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class SortLL {

    public static void main(String[] args) {

        Node head = new Node(6, null);
        Node n1 = new Node(3, null);
        Node n2 = new Node(2, null);
        Node n3 = new Node(1, null);
        Node n4 = new Node(5, null);
        Node n5 = new Node(4, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Node headBrute = sortBrute(head);
        Node headOptimal = sortOptimal(head);

        printList(headBrute);
        printList(headOptimal);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(n) + O(n*logn) + O(n) = O(2 * n) + O(n * log n)
    // SC: O(n)
    // Approach: In this approach we will first traverse the LL and store the values in an arraylist, then we will sort the 
    //           arraylist and then we will traverse the LL again and update the values from arraylist. Finally we will return 
    //           the head of sorted LL.
    public static Node sortBrute(Node head) {  
        ArrayList<Integer> al = new ArrayList<>();

        Node temp = head;

        // traverse the LL and store the values in arraylist
        while(temp != null) {
            al.add(temp.data);
            temp = temp.next;
        }

        // sort the arraylist
        Collections.sort(al);

        // reset temp to head
        temp = head;
        int i = 0;

        // traverse the LL again and update the values from arraylist
        while(temp != null) {
            temp.data = al.get(i);
            temp = temp.next;
            i++;
        }

        // return the head of sorted LL
        return head;
    }

    // TC: O((N + N/2) * Log N)
    // SC: O(N) -> for recursive stack space
    // Approach: In this approach, we will use merge sort algorithm to sort the LL. In merge sort algorithm, we will first 
    //           find the mid of the LL and then we will break the LL into two halves, left half will start from head and right 
    //           half will start from mid.next. Then we will recursively sort the left and right half of the LL and finally we 
    //           will merge the sorted left and right half of the LL and return the head of merged sorted LL.
    public static Node sortOptimal(Node head) {
        // base case: if head is null or head has only one node, then it's already sorted, so we will return head.
        if(head == null || head.next == null) {
            return head;
        }

        // find the mid of the LL
        Node mid = findMid(head);

        // break the LL into two halves, left half will start from head and right half will start from mid.next
        Node leftHalfHead = head;
        Node rightHalfHead = mid.next;
        mid.next = null; // break the connection between left and right half of the LL

        // recursively sort the left and right half of the LL and return the head of sorted left and right half of the LL
        leftHalfHead = sortOptimal(leftHalfHead);
        rightHalfHead = sortOptimal(rightHalfHead);

        // merge the sorted left and right half of the LL and return the head of merged sorted LL
        Node newHead = mergeLeftAndRrightHalfList(leftHalfHead, rightHalfHead);
        return newHead;
    }

    // helper function to find the mid of the LL using slow and fast pointer approach
    private static Node findMid(Node head) {
        Node slow = head;

        // In even nodes LL, we have two mids, now for merge sort implementation we need first mid. 
        // That's we have kept fast pointer one place ahead so that slow pointer stops one place before.
        Node fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // at the end of loop, slow pointer will be at the mid of the LL, so we will return slow pointer.
        return slow;
    }

    // helper function to merge the sorted left and right half of the LL and return the head of merged sorted LL
    private static Node mergeLeftAndRrightHalfList(Node left, Node right) {
        Node temp = null;
        Node newHead = null; // this will be the head of merged sorted LL, we will return this at the end of function.

        // we will traverse both left and right half of the LL and compare the values, the smaller value will be added to the 
        // merged sorted LL and the pointer of that half will be moved to next node, this process will continue until we reach 
        // the end of either left or right half of the LL.
        while(left != null && right != null) {
            if(left.data < right.data) {
                // first node
                if(temp == null) {
                    temp = left;
                    // we will assign newHead only for the first node, after that we will not update bcoz temp is continuously 
                    // moving and connecting the nodes, so we need a separate pointer to store the head of merged sorted LL.
                    newHead = temp;
                } else {
                    temp.next = left;
                    temp = left;
                }

                left = left.next;
            } else {
                // first node
                if(temp == null) {
                    temp = right;
                    newHead = temp;
                } else {
                    temp.next = right;
                    temp = right;
                }

                right = right.next;
            }
        }

        // once above loop ends, if any element is left in either left or right half of the LL, then we will add all those 
        // remaining elements to the merged sorted LL.
        if(left != null) {
            temp.next = left;
        }

        if(right != null) {
            temp.next = right;
        }

        // at the end we will return the head of merged sorted LL.
        return newHead;
    }

}
