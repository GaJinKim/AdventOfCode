package src;

public class Day9 {
    public static void main(String[] args) {
        int[] numbers = {35,20,15,25,47,40,62,55,65,95,102,117,150,182,127,219,299,277,309,576};
        int[] preamble = new int[5];

        for (int i = 0; i < numbers.length - preamble.length; i++) {
            // update the preamble
            for (int j = 0; j < preamble.length; j++)
                preamble[j] = numbers[j + i];

            boolean foundSum = false;
            int validNum = numbers[i + preamble.length];

            // find a combination of 2 numbers that will sum up to validNum
            for (int k = 0; k < preamble.length - 1; k++) {
                int numOne = preamble[k];
                for (int l = k + 1; l < preamble.length; l++) {
                    int numTwo = preamble[l];
                    if (numOne + numTwo == validNum) {
                        System.out.println(validNum + " = " + numOne + " + " + numTwo);
                        foundSum = true;
                        break;
                    }
                }
            }

            if (!foundSum) {
                System.out.println("Could not find a summation for " + validNum);
                break;
            }
        }

    }
}