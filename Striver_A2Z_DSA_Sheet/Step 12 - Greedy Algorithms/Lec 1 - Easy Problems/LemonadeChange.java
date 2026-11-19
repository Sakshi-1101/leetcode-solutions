
public class LemonadeChange {

    public static void main(String[] args) {
        int[] arr = {5, 5, 5, 10, 20};
        int n = 5;

        // GREEDY APPROACH
        boolean ans = checkForChange(arr, n);
        System.out.println(ans);
        
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will keep track of the count of $5 and $10 bills we have. For each customer, we will check 
    //           their bill. If they pay with a $5 bill, we simply increase our count of $5 bills. If they pay with a $10 bill,
    //           we need to give them one $5 bill as change, so we decrease our count of $5 bills and increase our count of $10 
    //           bills. If they pay with a $20 bill, we first try to give them one $10 bill and one $5 bill as change (if 
    //           available), otherwise we try to give them three $5 bills as change. If we cannot provide the required change at 
    //           any point, we return false. If we can provide change for all customers, we return true.
    public static boolean checkForChange(int[] arr, int n) {
        int count5 = 0; // count of 5$ bills
        int count10 = 0; // count of 10$ bills
        int count20 = 0; // no need to count 20$ bill, no significance

        // traverse the array for each customer's bill
        for(int bill: arr) {
            if(bill == 5) {
                count5++;  // Accept $5 (no change needed)
            } else if(bill == 10) {
                if(count5 > 0) {
                    count5--; // Give one $5 as change
                    count10++; // Accept $10
                } else {
                    return false; // if no $5 bill available, cannot give change
                }
            } else { // bill == 20
                /*
                    NOTE: We'll check for this combination ($10 + $5) first before checking for three $5 bills because 5 is a
                          smaller denomination, you keep it intact for future customers who may pay with $10 bills. If you give 
                          away three $5 bills, you may not have enough change for a future customer who pays with a $10 bill.
                    
                    (This is what greedy approach is all about, making the best choice at each step to ensure the best overall 
                     outcome.)
                 */
                if(count5 > 0 && count10 > 0) {
                    count5--; // Use one $5
                    count10--; // Use one $10
                } else if(count5 >= 3) {
                    count5 = 0; // Use three $5 bills, if no $10 bill available
                } else {
                    return false; // if no three $5 bills also available, then cannot give change
                }
            }
        }

        return true; // Successfully gave change to all customers
    }
    
}
