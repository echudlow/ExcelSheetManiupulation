import java.util.Scanner;

public class Evenish {

    public static void main(String[] args) {


        // Instantiated a keyboard so that I could do some manual testing
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter a number to be tested: ");
        String answer = keyboard.nextLine();
        int num = Integer.parseInt(answer);
        if (num == 0){
            System.out.println("Zero is neither even nor odd");
        } else {
            System.out.printf("The number that you have entered is " + evenOdd(num));
        }


    }

    public static String evenOdd(int num) {
        // Broke the number into an array of chars and makes sure the number is positive
        char[] digits = String.valueOf(Math.abs(num)).toCharArray();
        int total = 0;
        for (int i = 0; i < digits.length; i++) {
            // Added every index of the array together
            total += Character.getNumericValue(digits[i]);
        }
         if(total % 2 == 0) {
            return "Evenish";
        } else {
            return "Oddish";
        }
    }
}