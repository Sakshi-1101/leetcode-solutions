public class CelebrityProblem {
    
    public static void main(String[] args) {
        int[][] arr = { {0, 1, 1, 0}, 
                        {0, 0, 0, 0}, 
                        {1, 1, 0, 0}, 
                        {0, 1, 1, 0} 
                     };

        int n = 4;

        int ansBrute = checkCelebrityBrute(arr, n);
        System.out.println(ansBrute);

        int ansBruteMyApp = checkCelebrityBruteMyApproach(arr, n);
        System.out.println(ansBruteMyApp);

        int ansOptimal = checkCelebrityOptimal(arr, n);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this approach, we traverse the matrix to find a potential celebrity by checking if a person knows anyone. If 
    //           we find a person who doesn't know anyone, we then check if everyone else knows that person. If both conditions 
    //           are satisfied, we return the index of the celebrity; otherwise, we return -1.
    public static int checkCelebrityBruteMyApproach(int[][] arr, int n) {
        int ans = -1; // assume no celeb

        // traverse the rows and find a possible celeb
        for(int i = 0 ; i < n ; i ++) {
            // this will return a candidate that doesn't know anybody (potential celeb)
            boolean isPossibleCeleb = getPossible(arr[i], i, n);

            if(!isPossibleCeleb) {
                continue;
            }

            // this will keep track that does everyone knows the potential celeb or not
            boolean ifCeleb = true;

            // traverse the column of only the potential celeb
            for(int j = 0 ; j < n ; j ++) {
                // if any 0 encounter in the column apart from i == j (that person itself) that means there is someone
                // who doesn't know potential celeb, hence it can be a celeb
                if(i != j && arr[j][i] != 1) {
                    ifCeleb = false; 
                    break;
                }
            }

            // if we find all 1 in the potential celeb column except i == j (person itself), we found our celeb
            if(ifCeleb) {
                ans = i;
            } 
        }

        return ans;

    }

    // helper function to check if current candidate is a potential celeb or not
    public static boolean getPossible(int[] arr, int candidate, int n) {

        // traverse the row, if all the cells have 0 except i==j (person itself) that means the candidate doesn't know anyone
        // hence can be a potential celeb
        for(int i = 0 ; i < n ; i ++) {
            if(i != candidate && arr[i] == 1) {
                return false;
            }
        }

        return true;
    }

    // TC: O(N^2) + O(N)
    // SC: O(2N)
    // Approach: In this approach, we use two arrays to keep track of how many people know a person and how many people a person 
    //           knows. We then check for the conditions of a celebrity that is known by everyone else and knows no one. If such 
    //           a person exists, we return their index; otherwise, we return -1.
    public static int checkCelebrityBrute(int[][] arr, int n) {
        int[] knowMe = new int[n]; // To store count of people who know person of index i
        int[] IKnow = new int[n];  // To store count of people known by the person of index i

        int celeb = -1; // assuming no celebrity initially

        // Traverse the matrix to calculate knowMe and Iknow
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < n ; j ++) {
                // If person i knows person j
                if(arr[i][j] == 1) {
                    knowMe[j]++;  // Person j is known by person i
                    IKnow[i]++;   // Person i knows person j
                }
            }
        }

        // Traverse all persons to find the celebrity
        for(int i = 0 ; i < n ; i ++) {
            // If person i knows no one and is known by everyone else
            if(knowMe[i] == n - 1 && IKnow[i] == 0) {
                celeb = i; // Person i is the celebrity
                break;
            }
        }

        // Return -1 if no celebrity is found
        return celeb;
    }


    // TC: O(N) + O(N) = O(2N)
    // SC: O(1)
    // Approach: In this approach, we use two pointers to narrow down the potential celebrity. We then verify if the candidate 
    //           is indeed a celebrity by checking the conditions. 
    public static int checkCelebrityOptimal(int[][] arr, int n) {
        // Top and bottom pointers for narrowing the possible celebrity
        int top = 0;
        int bottom = n - 1;

        // Traverse for all the people to find potential celebrity
        while(top < bottom) {
            if(arr[top][bottom] == 1) { // top knows bottom, so top can't be celeb
                top++;
            } else if(arr[bottom][top] == 1){ // bottom knows top, so bottom can't be a celeb
                bottom--;
            } else { // top person doesn't know bottom person & bottom person doesn't know top person, means both can't be celebs
                top++;
                bottom--;
            }
        }

        // If top exceeds bottom, no celebrity is found
        if (top > bottom) {
            return -1;
        }

        // if top == bottom, check if the potential candidate pointed by top is a celebrity or not
        for(int i = 0 ; i < n ; i ++) {
            if (i == top) { // Skip checking the person itself
                continue;
            }

            // If top knows someone or someone doesn't know top, it's not a celebrity
            if(arr[top][i] == 1 && arr[i][top] == 0) { // arr[top][i] -> row check, arr[i][top] -> column check
                return -1;
            }
        }

        // Return the index of the celebrity
        return top;

    }
}
