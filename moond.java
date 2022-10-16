/**
 * Find the sums of the running odd numbers and even numbers from a given lowerbound 
 * to an upperbound. Also compute their absolute difference.
 */
public class OddEvenSum {  // Save as "OddEvenSum.java"
   public static void main(String[] args) {
      // Declare variables
      final int LOWERBOUND = 1;
      final int UPPERBOUND = 1000;  // Define the bounds
      int sumOdd  = 0;    // For accumulating odd numbers, init to 0
      int sumEven = 0;    // For accumulating even numbers, init to 0
      int absDiff;        // Absolute difference between the two sums

      // Use a while loop to accumulate the sums from LOWERBOUND to UPPERBOUND
      int number = LOWERBOUND;   // loop init
      while (number <= UPPERBOUND) {  // loop test
            // number = LOWERBOUND, LOWERBOUND+1, LOWERBOUND+1, ..., UPPERBOUND
         // A if-then-else decision
         if (number % 2 == 0) {  // Even number
            sumEven += number;   // Same as sumEven = sumEven + number
         } else {                // Odd number
            sumOdd += number;    // Same as sumOdd = sumOdd + number
         }
         ++number;  // loop update for next number
      }
      // Another if-then-else Decision
      if (sumOdd > sumEven) {
         absDiff = sumOdd - sumEven;
      } else {
         absDiff = sumEven - sumOdd;
      }
      // OR using one liner conditional expression
      //absDiff = (sumOdd > sumEven) ? sumOdd - sumEven : sumEven - sumOdd;
 
      // Print the results
      System.out.println("The sum of odd numbers from " + LOWERBOUND + " to " + UPPERBOUND + " is: " + sumOdd);
      System.out.println("The sum of even numbers from " + LOWERBOUND + " to " + UPPERBOUND + " is: " + sumEven);
      System.out.println("The absolute difference between the two sums is: " + absDiff);
   }
}
