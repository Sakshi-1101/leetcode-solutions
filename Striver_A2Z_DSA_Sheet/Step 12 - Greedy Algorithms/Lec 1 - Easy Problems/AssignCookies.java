import java.util.*;

public class AssignCookies {

    public static void main(String[] args) {
        int[] students = {1, 5, 3, 3, 4};
        int n = 5;

        int[] cookies = {4, 2, 1, 2, 1, 3};
        int m = 6;

        // GREEDY APPROACH
        /*
            NOTE: Greedy is the most efficient for this problem.
                  Tabulation and Memoization showcase the DP approach but are computationally heavier.
         */
        int ans = maxStudents(students, cookies, n, m);

        System.out.println(ans);
    }

    // TC: O(N*logN) + O(M*logM) + O(M)
    // SC: O(1)
    // Approach: In this approach, we will sort the students and cookies arrays. Then we will use two pointers to traverse both 
    //           arrays. At each step, we will check if the current cookie can satisfy the current student. If it can, we will 
    //           move to the next student and the next cookie. If it cannot, we will move to the next cookie for the same student. 
    //           We will continue this process until we reach the end of either array. Finally, we will return the number of 
    //           students who got cookies, which is represented by the last position of the ith student.
    public static int maxStudents(int[] students, int[] cookies, int n, int m) {
        //sort the arrays
        Arrays.sort(students);
        Arrays.sort(cookies);

        // take two pointers
        int i = 0; // represents the ith student
        int j = 0; // repesents the jth cookie size

        // traverse the cookies and students arrays until we reach the end of either array.
        while(j < m && i < n) {
            // if the jth cookie size is >= the ith student min cookie size, then we can give the cookie to the student and 
            // move to the next student and next cookie.
            if(cookies[j] >= students[i]) {
                i++;
                j++;
            } 
            // if the jth cookie size is < the ith student min cookie size, then we cannot give the cookie to the student and we 
            // move to the next cookie for the same student.
            else {
                j++;
            }
        }

        // return the number of students who got cookies, which is represented by the last position of the ith student.
        return i;
    }
    
}
