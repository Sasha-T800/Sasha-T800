/* Author: Sasha Tkachenko
/* Class CreditCardValidator
/* imllement Luhn algorithm
*/

public class CreditCardValidator {
   /* the method The Luhn algorithm test is used as a credit card digit test 
    /*
    */
    public boolean isValid(int[] card) {
        int[] reversedCard = reverseNumbers(card);
        int sumOddNumbers = getSumOddNumbers(reversedCard);
        int sumEvenNumbers = getSumEvenNumbers(reversedCard);
        int total = sumEvenNumbers + sumOddNumbers;
        String totalAsString = String.valueOf(total);
        //If sumOddNumbers + sumEvenNumbers ends in zero then the original number is valid, otherwise it is invalid.
        if (totalAsString.length() == 2 && totalAsString.charAt(1) == '0')
            return true;

        return false;
    }
    /* the method Take the second, fourth ... and every other even digits in the reversed digits
    /* Multiply each digit by two and sum the digits, if the answer is greater than 9
        then add the 2 digits to form partial sums for the even digits         
        Sum the partial sums of the even digits  
    */
    private int getSumEvenNumbers(int[] reversedCard) {
        int[] evenNumbers = new int[reversedCard.length / 2];
        int j = 0;
        for (int i = 0; i < reversedCard.length; i++) {
            if (i % 2 != 0) {
                evenNumbers[j] = reversedCard[i];
                j++;
            }
        }

        int total = 0;
        for (int i = 0; i < evenNumbers.length; i++) {
            evenNumbers[i] = evenNumbers[i] * 2; // double all numbers
            if (evenNumbers[i] > 9) {
                int digitSum = 0; //Sum the digits > 9
                while (evenNumbers[i] > 0) {
                    digitSum = digitSum + evenNumbers[i] % 10;
                    evenNumbers[i] = evenNumbers[i] / 10;
                }
                evenNumbers[i] = digitSum;
            }
            total = total + evenNumbers[i];
        }
        return total;
    }
    /* the method Perform a partial sum of the odd digits
    /*
    */
    private int getSumOddNumbers(int[] reversedCard) {
        int sum = 0;
        for (int i = 0; i < reversedCard.length; i++) {
            if (i % 2 == 0) {
                sum = sum + reversedCard[i];
            }
        }
        return sum;
    }
    /* the method Reverse the order of the digits in the number.
    /*
    */
    private int[] reverseNumbers(int[] card) {
        int[] reverseCard = new int[card.length];
        for (int i = 0; i < card.length; i++) {
            reverseCard[card.length - i - 1] = card[i];
        }
        return reverseCard;
    }
}
