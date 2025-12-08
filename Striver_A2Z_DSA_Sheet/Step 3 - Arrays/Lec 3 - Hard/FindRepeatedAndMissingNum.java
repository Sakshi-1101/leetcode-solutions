public class FindRepeatedAndMissingNum {

    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 1, 1};

        int[] ansBrute = findNumBrute(arr);

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            System.out.print(ansBrute[i] + " ");
        }

        System.out.println();

        int[] ansBetter = findNumBetter(arr);

        for(int i = 0 ; i < ansBetter.length ; i ++) {
            System.out.print(ansBetter[i] + " ");
        }

        System.out.println();

        int[] ansOptimal1 = findNumOptimal1(arr);

        for(int i = 0 ; i < ansOptimal1.length ; i ++) {
            System.out.print(ansOptimal1[i] + " ");
        }

        System.out.println();

        int[] ansOptimal2 = findNumOptimal2(arr);

        for(int i = 0 ; i < ansOptimal2.length ; i ++) {
            System.out.print(ansOptimal2[i] + " ");
        }
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this we use two nested loops. The outer loop runs from 1 to n and the inner loop runs through the array 
    //           to count the occurrences of each number. If a number occurs twice, we store it as the repeated number. If 
    //           a number does not occur, we store it as the missing number.
    public static int[] findNumBrute(int[] arr) {
      int n = arr.length; 
      int rep = -1; // repeated number
      int miss = -1; // missing number

      // traverse for n natural numbers from 1 to n
      for(int i = 1 ; i <= n ; i ++) {
        int count = 0; // count occurrences of ith num in arr

        // traverse the array to count occurrences
        for(int j = 0 ; j < n ; j ++) {
            if(arr[j] == i) {
                count++;
            }
        }

        // check count to find repeated and missing numbers
        if(count == 2) {
            rep = i;
        } else if(count == 0) {
            miss = i;
        }

        // if both numbers are found early, break the loop
        if(rep != -1 && miss != -1) {
            break;
        }

      }
      
      // store repeated and missing numbers in ans array and return
      return new int[]{rep, miss};
    }

    // TC: O(2*N) -> N for arr traversal and N for freq arr traversal
    // SC: O(N)
    // Approach: In this we use a frequency array to count occurrences of each number in the input array. We then traverse the 
    //           frequency array to find the repeated and missing numbers.
    public static int[] findNumBetter(int[] arr) {

      int n = arr.length;
      int rep = -1; // repeated number
      int miss = -1; // missing number

      // create frequency array
      int[] freq = new int[n + 1];

      // fill the frequency array
      for(int i = 0 ; i < n ; i ++) {
        freq[arr[i]]++;
      }

      // find repeated and missing numbers by traversing freq array
      /* 
        NOTE: run this loop from idx=1 bcoz the freq at idx=0 is also 0 due to which it will put wrong ans i.e. 0 in missing 
              element.
     */ 
      for(int i = 1 ; i < freq.length ; i ++) {
        if(freq[i] == 2) {
            rep = i;
        } else if(freq[i] == 0) {
            miss = i;
        }

        // if both numbers are found early, break the loop
        if(rep != -1 && miss != -1) {
            break;
        }
      }
    
      // store repeated and missing numbers in ans array and return
      return new int[]{rep, miss};
    }

    // TC: O(N)
    // SC: (1)
    // Approach: In this we'll use mathematical equations to find the repeated and missing numbers.
    public static int[] findNumOptimal1(int[] arr) {
      /* since we are summing up all the numbers and squares of numbers, so the value might exceed the integer boundary 
         hence use long instead of int.
      */
      long n = (long) arr.length;
    
      // calculate sum of n natural no.
      long Sn = (n * (n + 1)) / 2;

      // calculate sum of square of n natural number
      long Sn2 = (n * (n + 1) * (2 * n + 1)) / 6;

      long S = 0; // sum of all elements of array
      long S2 = 0; // sum of square of all elements of array

      for(int i = 0 ; i < n ; i ++) {
        S += (long)arr[i];
        S2 += (long)arr[i] * (long)arr[i];
      }

      /*
        NOTE: x = repeating no.
              y = missing no.
      */
      // S - Sn = x - y
      // S2 - Sn2 = x^2 - y^2 => (x + y)(x - y) = S2 - Sn2 => x + y = (S2 - Sn2) / (x - y)
      long xmy = S - Sn;
      long xpy = (S2 - Sn2) / xmy; // where xmy = (x - y)

      /*
        1.  x - y = xmy
            x + y = xpy
          + ____________
             2x = (xmy + xpy)
              x = (xmy + xpy) / 2

        2.  x - y = xmy
            => y = x - xmy
      */
      long x = (xmy + xpy) / 2;
      long y = x - xmy;

      return new int[]{(int)x, (int)y};

    }

    // TC: O(4 * N) ~ O(N)
    // SC: O(1)
    // Approach: In this we use XOR operation to find the repeated and missing numbers.
    public static int[] findNumOptimal2(int[] arr) {
      long n = arr.length;

      // Step 1: Calcluate the XOR = (xor of all elements of array) ^ (1 to n natural numbers)
      int xor = 0;

      for(int i = 0 ; i < n ; i ++) {
        xor = xor ^ arr[i]; // xor of all elements of array
        xor = xor ^ (i + 1); // 1 to n natural number
      }

      // Step 2: Find the differentiating bit position for the XOR value calculated in Step 1
      /*
          NOTE: For the value of xor we need check the position for the set bit. 
                For this we'll put 1 beneath each index one by one and check for which combination we'll get the set bit.
                To calculate that we'll do left shift (<<) i.e. 1 << bitPos.
                1 << 0 => 001, 1 << 1 => 010, 1 << 2 => 100
                Now, we'll do bitwise AND (&) between xor value and the left shift operation output. Now if this gives
                a number apart from 0 then that will be the bit index which will be a set bit(1).
                We are using bitwise AND (&) bcoz it only works in case (1 & 1 = 1) otherwise it willa always give 0.

              // SHORTCUT TO CALCULATE THE RIGHTMOST SET BIT POSITION
                 int bitPos = xor & ~(xor - 1);
       */
      int bitPos = 0;

      while(true) {
        if((xor & (1 << bitPos)) != 0) {
          break;
        }

        bitPos++;
      }

      // Step 3: Segregate the elements of array and 1 to n natural numbers into 0 and 1 list acc to the value at the bitPos. 
      //         Then calculate the xor of all the values in the respective lists.
      /*
        Eg: bitPos = 2;
        Therefore, 1 = 001 , 2 = 010, 5 = 101, 6 = 110
        So the since the 2nd bit for 1,2 is 0 it will go to 0 value list.
        And 5,6 has the 2nd bit as 1, so it will go to 1 value list.
       */

      // we'll have the repeating and missing number in these variables at the end
      int zeroList = 0;
      int oneList = 0;

      // traverse the array
      for(int i = 0 ; i < n ; i ++) {
        // check the bit at the bitPos
        if((arr[i] & (1 << bitPos)) != 0) { // part of 1 value list bcoz at the bitPos we'll get set bit(1) hence a number
          oneList ^= arr[i];
        } else { // part of the 0 value list 
          zeroList ^= arr[i];
        }
      }

      // traverse on 1 to n natural numbers
      for(int i = 1 ; i <= n ; i ++) {
        // check the bit at the bitPos
        if((i & (1 << bitPos)) != 0) { // part of 1 value list
          oneList ^= i;
        } else { // part of the 0 value list 
          zeroList ^= i;
        }
      }

      // Step 4: Now that we have got the repeated and missing number in zeroList and oneList, we'll verify which one is 
      //         repeating and missing out of the two numbers.

      int count = 0;

      for(int i = 0 ; i < n ; i ++) {
        if(arr[i] == zeroList) {
          count++;
        }
      }

      // if count is 2 then zeroList is the repeated number else oneList is the repeated number
      if(count == 2) {
        return new int[]{zeroList, oneList};
      } else {
        return new int[]{oneList, zeroList};
      }
    }
}
