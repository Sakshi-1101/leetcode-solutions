import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public static void main(String[] args) {
        String s = "MCMXCIV";

        int ans = findIntegerValue(s);

        System.out.println(ans);
    }

    // TC: O(n)
    // SC: O(1) -> since we use a fixed-size map for Roman numerals.
    // Approach: In this approach, we create a map to store the integer values of the Roman numerals. We then traverse the string 
    //           from end to start. For each char, we compare its value with the value of the next char (which is the previous 
    //           char in the string). If the current char's value is less than the next char's value, it means we need to 
    //           subtract the current char's value from the total. Otherwise, we add it to the total. 
    //           This works because in Roman numerals, when a smaller numeral appears before a larger numeral, it indicates 
    //           subtraction (e.g., IV = 4), while when a smaller numeral appears after a larger numeral, it indicates addition 
    //           (e.g., VI = 6). Finally, we return the computed integer value.

    // BACKWARD LOOP
    public static int findIntegerValue(String s) {
        // map to store roman numerals to integer 
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // to store the final integer value of roman numeral
        int val = 0;

        // traverse the string from end to start
        for(int i = s.length() - 1 ; i >= 0 ; i --) {
            char ch = s.charAt(i);

            // if it is the last char, simply add it's value
            if(i == s.length() - 1) {
                val = val + map.get(ch);
            } 
            //if current roman no. value is less than next roman no. value
            else if(map.get(ch) < map.get(s.charAt(i + 1))) {
                val = val - map.get(ch);
            } 
            else { //map.get(ch) >= map.get(s.charAt(i + 1))
                val = val + map.get(ch);
            }
        }

        return val;
    }


    // FORWARD LOOP
    public int romanToInt(String s) {
        int res = 0;
        
        // Create a map of Roman numerals to integers
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // Loop through the string, except the last character
        for (int i = 0; i < s.length() - 1; i++) {

            // Subtract if current value is less than next value
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                res -= map.get(s.charAt(i));
            } else {
                res += map.get(s.charAt(i)); // Otherwise, add the value
            }
        }

        // Add the value of the last character
        return res + map.get(s.charAt(s.length() - 1));
    }
    
}
