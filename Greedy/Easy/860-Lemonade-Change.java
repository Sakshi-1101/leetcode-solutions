class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0; // Counter for $5 bills
        int ten = 0;  // Counter for $10 bills

        // Process each customer's bill
        for (int bill : bills) {
            if (bill == 5) {
                five++; // Accept $5 (no change needed)
            } 
            else if (bill == 10) {
                if (five > 0) {
                    five--; // Give one $5 as change
                    ten++;  // Accept $10
                } else {
                    return false; // Cannot give change
                }
            } 
            else { // bill == 20
                if (five > 0 && ten > 0) {
                    five--; // Use one $5
                    ten--;  // Use one $10
                } 
                else if (five >= 3) {
                    five -= 3; // Use three $5 bills
                } 
                else {
                    return false; // Cannot give change
                }
            }
        }
        
        return true; // Successfully gave change to all customers
        
    }
}