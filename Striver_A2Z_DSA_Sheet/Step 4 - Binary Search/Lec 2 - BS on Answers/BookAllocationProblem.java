public class BookAllocationProblem {

    public static void main(String[] args) {
        /*
            CONSTRAINTS:
            1. Each student gets at least one book.
            2. Each book should be allocated to only one student.
            3. Book allocation should be in a contiguous manner.
         */
        int[] arr = {25, 46, 28, 49, 24};
        int students = 4;

        // to find the min value of the maximum number of pages assigned to a student.
        int ansBrute = findMinOfMaxPagesBrute(arr, students);
        int ansOptimal = findMinOfMaxPagesOptimal(arr, students);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
        
    }

    // TC: O(sum - max + 1) * O(N) => O(N) -> time complexity of findStudentCount()
    // SC: O(1)
    // Approach: In this approach, we will try to find the max no. of pages that can be allocated to each student by traversing 
    //           through all the possible answers from max no. of pages among all the books to summation of all the pages of the 
    //           books. For each possible answer, we will check how many students are required to allocate all the books such 
    //           that no student gets more than the possible answer pages. If the number of students required is equal to the 
    //           given number of students, we return that possible answer as the result.
    public static int findMinOfMaxPagesBrute(int[] arr, int students) {
        // edge case: when no. of books < no. of students 
        if(arr.length < students) {
            return -1; // not possible to allocate each book to every student 
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            max = Math.max(arr[i], max);
            sum += arr[i];
        }

        // define the range where ans will lie
        int start = max; // max no. of pages among all the book, so that each student get atleast one book
        int end = sum; // if there was only 1 student then all the books will be allocated to that student only. Hence, max no. of pages allocated = summation of array
    
        // traverse on the possible answers
        for(int i = start ; i <= end ; i ++) {
            // for i no. of pages, check how many students you are able to allocate all the books
            int countStudents = findStudentCount(arr, i);

            // if you are able to allocate the max no. of pages to given no. of students return the ans since we need the min 
            // value so no need to check further for bigger values
            if(countStudents == students) {
                return i;
            }
        }

        //return max(arr[]) as there cannot exist any answer smaller than that.
        return start;
    
    }

    // function to find how many students are required to allocate all the books such that no student gets more than 'pages' no. of pages. 
    /* The code logic is as follows:
        1. Start with the first student and allocate books to that student until the allocated pages exceed 'pages'.
        2. When the allocated pages exceed 'pages', move to the next student and allocate the current book to that student.
        3. Repeat this process until all books are allocated.
    */
    private static int findStudentCount(int[] arr, int pages) {
        // this implies currently student 1 is allocated 0 pages
        int stdCount = 1;
        int pagesAllocated = 0;

        // traverse the array to allocate books
        for(int i = 0 ; i < arr.length ; i ++) {
            // if the allocated pages + current no. of pages doesn't exceed the max no. of pages that can be assigned
            if(pagesAllocated + arr[i] <= pages) {
                pagesAllocated += arr[i]; // add the current pages to the existing student i.e. allocate the ith book to current student
            } else { // if the allocated pages + current no. of pages exceeds the max no. of pages that can be assigned
                stdCount++; // move to the next student
                pagesAllocated = arr[i]; // allocate the current ith book with arr[i] no. of pages to this student
            }
        }

        // return the total no. of student to whom the all the given books were allocated such that each student didn't exceed 
        // the max no. of pages that can be assigned
        return stdCount;
    }

    // TC: O(log (base 2) (sum - max + 1)) * O(N) => O(N) -> time complexity of findStudentCount()
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the min value of the maximum number of pages assigned to a 
    //           student. We will define the search space from max no. of pages among all the books to summation of all the pages 
    //           of the books. For each mid value in the search space, we will check how many students are required to allocate 
    //           all the books such that no student gets more than mid pages. If the number of students required is less than or 
    //           equal to the given number of students, we will search for a smaller value in the left half of the search space.
    //           Otherwise, we will search for a larger value in the right half of the search space. We will continue this 
    //           process until we find the minimum value of the maximum number of pages assigned to a student.
    public static int findMinOfMaxPagesOptimal(int[] arr, int students) {
        
        // edge case: when no. of books < no. of students 
        if(arr.length < students) {
            return -1; // not possible to allocate each book to every student 
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            max = Math.max(arr[i], max);
            sum += arr[i];
        }

        // define the range where ans will lie
        int lo = max; // max no. of pages among all the book, so that each student get atleast one book
        int hi = sum; // if there was only 1 student then all the books will be allocated to that student only. Hence, max no. of pages allocated = summation of array
        
        // initialize ans to lo bcoz lo is the minimum possible value of the max no. of pages allocated
        int ans = lo;

        // binary search
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // for mid no. of pages, check how many students you are able to allocate all the books
            int countStudents = findStudentCount(arr, mid);

            // if you are able to allocate the max no. of pages to given no. of students look for more smaller value since we 
            // need the min value of the max no. of pages allocated
            if(countStudents <= students)  {
                ans = mid;
                hi = mid - 1; // discard bigger values and search on left half
            } else { // since current value couldn't allocate all books, look for bigger value
                lo = mid + 1; // search in right half for bigger values
            }
        }
        
        return ans; // OR return lo;
    }
    
}
