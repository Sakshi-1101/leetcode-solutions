import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AsteroidCollision {

    public static void main(String[] args) {
        int[] arr = {3,5,-6,2,-1,4};
        int n = 6;

        List<Integer> ans = asteroids(arr, n);

        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach we will use a stack to keep track of the asteroids that are currently moving in the right 
    //           direction. We will traverse the array of asteroids and for each asteroid, we will check if it is moving in the 
    //           right direction (positive value) or in the left direction (negative value). If it is moving in the right 
    //           direction, we will push it into the stack. If it is moving in the left direction, we will check if there are 
    //           any asteroids in the stack that are moving in the right direction and have a smaller value than the current 
    //           asteroid. If there are such asteroids, we will pop them from the stack because they will collide with the 
    //           current asteroid and get destroyed. If there is an asteroid in the stack that has the same value as the current 
    //           asteroid, we will pop it from the stack because both will collide and get destroyed. If there are no asteroids 
    //           in the stack that can collide with the current asteroid, we will push it into the stack because it will continue 
    //           moving in its direction. Finally, after traversing all asteroids, we will have the remaining asteroids in the 
    //           stack which will not collide with any other asteroid.
    public static List<Integer> asteroids(int[] arr, int n) {
        Stack<Integer> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();

        // traverse each ele in the array
        for(int i = 0 ; i < n ; i ++) {
            int val = arr[i];

            // if +ve element (right direction)
            if(val > 0) {
                st.push(val);
            } 
            // if -ve element (left direction)
            else {
                // if the stack is not empty and the top of the stack is a +ve element and its value is less than the absolute 
                // value of the current -ve element, then we pop the top of the stack because it will collide with the current 
                // -ve element and get destroyed.
                while(!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(val)) {
                    st.pop();
                }

                // if the stack is not empty and the top of the stack is a +ve element and its value is equal to the absolute 
                // value of the current -ve element, then we pop the top of the stack because both will collide and get destroyed.
                if(!st.isEmpty() && st.peek() == Math.abs(val)) {
                    st.pop();
                }
                // if the stack is empty or the top of the stack is a -ve element, then we push the current -ve element into the 
                // stack because it will not collide with any +ve element and will continue moving in the left direction.
                else if(st.isEmpty() || st.peek() < 0) {
                    st.push(val); // push -ve value
                }
            }
        }

        // after traversing the array, we will have the remaining asteroids in the stack which will not collide with any 
        // other asteroid.
        while(!st.isEmpty()) {
            ans.add(st.pop());
        }

        return ans;
    }
}
