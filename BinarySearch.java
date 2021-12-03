/*
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Jonathan Pasco-Arnone
* @version 0.5
* @since   2021-12-02
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*/
final class BinarySearch {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private BinarySearch() {
        // Prevent instantiation
        // Optional: throw an exception e.g. AssertionError
        // if this ever *is* called
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * Function finds the index of a number, using Binary Search recursively.
    *
    * @param userArray the randomly generated array.
    * @param userNumber the number that the user wants to check the array for.
    * @param lowIndex the lowest index.
    * @param highIndex the highest index.
    * @return binarySearch
    */
    static int binarySearch(final int[] userArray, final int userNumber,
        int lowIndex, int highIndex) {
        // Variables.
        int newLowIndex = lowIndex;
        int newHighIndex = highIndex;
        // Constants.
        final int returnValue;

        if (userNumber == userArray[(int) Math.ceil((double) (newLowIndex
            + newHighIndex) / 2)]) {
            returnValue = userArray[(int) Math.ceil((double) (newLowIndex
                + newHighIndex) / 2)];
        } else if (newLowIndex == newHighIndex) {
            returnValue = -1;
        } else if (userNumber > userArray[(int) Math.ceil((double)
            (newLowIndex + newHighIndex) / 2)]) {
            newLowIndex = (int) Math.ceil((double) (newLowIndex
                + newHighIndex) / 2);
            returnValue = binarySearch(userArray, userNumber,
                newLowIndex, newHighIndex);
        } else {
            newHighIndex = (int) Math.floor((double) (newLowIndex
                + newHighIndex) / 2);
            returnValue = binarySearch(userArray, userNumber,
               newLowIndex, newHighIndex);
        }

        return returnValue;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        // Variables.
        /*
        * Constants. (the checkstyle does not like constants with all caps,
        * so that is why they are all lowercase)
        */

        /*
        * The min number for array.
        */
        final int min = 0;
        /*
        * The max number for array.
        */
        final int max = 999;
        /*
        * The number of elements in the array.
        */
        final int arraySize = 100;

        System.out.println("Binary Search Program");
        try {
            // Initializing the random class
            final Random randNumber = new Random();

            // Initializing array of numbers
            final int[] randomNumberArray = new int[arraySize];

            // Adding numbers to the array
            for (int counter = 0; counter < randomNumberArray.length;
                counter++) {
                randomNumberArray[counter] = randNumber.nextInt(max) + 1;
            }

            // Sorting the array
            final int[] numberArray = randomNumberArray;
            Arrays.sort(numberArray);

            System.out.print("\nSorted list of numbers:\n");
            for (int element: numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");

            // Getting user input as to what number they wish to search for
            final Scanner userInput = new Scanner(System.in);
            System.out.print("What number are you searching for in"
                + " the array");
            System.out.print(" (integer between 0 and 999): ");
            final int searchNumber = userInput.nextInt();
            userInput.close();
            System.out.println();

            // Ensuring the user inputs an appropriate integer
            if (searchNumber > max || searchNumber < min) {
                throw new Exception();
            } else {
                /*
                * Using binary search to find the user's chosen number
                * in the array
                */
                final int searchResult = binarySearch(numberArray,
                    searchNumber, 0, numberArray.length - 1);
                if (searchResult == -1) {
                    throw new Exception();
                } else {
                    // Outputing the results of the search
                    System.out.println();
                    System.out.println("Your number is in index: "
                        + searchResult);
                }
            }

            // Catches and tells the user that an error occured
        } catch (java.util.InputMismatchException ex) {
            System.out.println();
            System.out.println("ERROR: Invalid Input");
        }
    }
}
