/******************************************************************
 *
 *   Jacob Oh / 001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     * <p>
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     * <p>
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values      - int[] array to be sorted.
     * @param //ascending - if true,method performs an ascending sort, else descending.
     *                    There are two method signatures allowing this parameter
     *                    to not be passed and defaulting to 'true (or ascending sort).
     */

    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {

        int n = values.length;
        int minACtual = values[0];
        int TempMaxual = values[0];
        int indexOfSwap = 0;
        boolean swap = false;
//
        for (int i = 0; i < n - 1; i++) {
            // YOU CODE GOES HERE -- COMPLETE THE INNER LOOP OF THIS
            // "SELECTION SORT" ALGORITHM.
            // DO NOT FORGET TO ADD YOUR NAME / SECTION ABOVE
            minACtual = values[i];
            TempMaxual = values[i];
            indexOfSwap = i;
            swap = false;

            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (values[j] < minACtual) {
                        minACtual = values[j];
                        indexOfSwap = j;
                        swap = true;
                    }
                } else {
                    if (values[j] > TempMaxual) {
                        TempMaxual = values[j];
                        indexOfSwap = j;
                        swap = true;
                    }
                }


            }
            if (swap) {
                int temp = values[i];
                values[i] = values[indexOfSwap];
                values[indexOfSwap] = temp;
            }


        }

    } // End class selectionSort


    /**
     * Method mergeSortDivisibleByKFirst
     * <p>
     * This method will perform a merge sort algorithm. However, all numbers
     * that are divisible by the argument 'k', are returned first in the sorted
     * list. Example:
     * values = { 10, 3, 25, 8, 6 }, k = 5
     * Sorted result should be --> { 10, 25, 3, 6, 8 }
     * <p>
     * values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     * Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     * <p>
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values - input array to sort per definition above
     * @param k      - value k, such that all numbers divisible by this value are first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0) return;
        if (values.length <= 1) return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */
    int count = 0;

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right) {
        // YOUR CODE GOES HERE, THIS METHOD IS NO MORE THAN THE STANDARD MERGE PORTION
        // OF A MERGESORT, EXCEPT THE NUMBERS DIVISIBLE BY K MUST GO FIRST WITHIN THE
        // SEQUENCE PER THE DISCUSSION IN THE PROLOGUE ABOVE.
        //
        // NOTE: YOU CAN PROGRAM THIS WITH A SPACE COMPLEXITY OF O(1) OR O(N LOG N).
        // AGAIN, THIS IS REFERRING TO SPACE COMPLEXITY. O(1) IS IN-PLACE, O(N LOG N)
        // ALLOCATES AUXILIARY DATA STRUCTURES (TEMPORARY ARRAYS). IT WILL BE EASIER
        // TO CODE WITH A SPACE COMPLEXITY OF O(N LOG N), WHICH IS FINE FOR PURPOSES
        // OF THIS PROGRAMMING EXERCISES.

        //get all numbers divisible by k if count = 0
        // list to hold numbers divisible by k
        ArrayList<Integer> divisibleList = new ArrayList<>();

        // list to hold numbers not divisible by k
        ArrayList<Integer> sortedNeedList = new ArrayList<>();

        // go through the range and separate values based on divisibility
        for (int i = left; i <= right; i++) {
            if (arr[i] % k == 0) {
                divisibleList.add(arr[i]); // keep divisible ones separate
            } else {
                sortedNeedList.add(arr[i]); // keep others for later sorting
            }
        }

        // sort the non-divisible numbers normally
        ArrayList<Integer> sortedResult = mergeSortHelperFun(sortedNeedList);

        // now overwrite the original array section
        // first place the divisible numbers
        int index = left;
        for (int num : divisibleList) {
            arr[index++] = num;
        }

        // then place the sorted non-divisible numbers
        for (int num : sortedResult) {
            arr[index++] = num;
        }
    }

    private ArrayList<Integer> mergeSortHelperFun(ArrayList<Integer> list) {
        // basic merge sort: if list is small, it's already sorted
        if (list.size() <= 1) {
            return list;
        }

        // split the list into two halves
        int mid = list.size() / 2;
        ArrayList<Integer> tempArrLeft = new ArrayList<>(list.subList(0, mid));
        ArrayList<Integer> tempArrRight = new ArrayList<>(list.subList(mid, list.size()));

        // sort each half
        ArrayList<Integer> sortedLeft = mergeSortHelperFun(tempArrLeft);
        ArrayList<Integer> sortedRight = mergeSortHelperFun(tempArrRight);

        // now merge the two sorted halves together
        ArrayList<Integer> mergedEnd = new ArrayList<>();
        int i = 0, j = 0;

        // compare elements from both sides and add the smaller one
        while (i < sortedLeft.size() && j < sortedRight.size()) {
            if (sortedLeft.get(i) <= sortedRight.get(j)) {
                mergedEnd.add(sortedLeft.get(i));
                i++;
            } else {
                mergedEnd.add(sortedRight.get(j));
                j++;
            }
        }

        // if anything's left on the left side, add it
        while (i < sortedLeft.size()) {
            mergedEnd.add(sortedLeft.get(i));
            i++;
        }

        // if anything's left on the right side, add it
        while (j < sortedRight.size()) {
            mergedEnd.add(sortedRight.get(j));
            j++;
        }

        return mergedEnd; // final merged and sorted result
    }


    /**
     * Method asteroidsDestroyed
     * <p>
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     * <p>
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     * <p>
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     * <p>
     * Example 1:
     * Input: mass = 10, asteroids = [3,9,19,5,21]
     * Output: true
     * <p>
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     * <p>
     * Example 2:
     * Input: mass = 5, asteroids = [4,9,23,4]
     * Output: false
     * <p>
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     * <p>
     * Constraints:
     * 1 <= mass <= 105
     * 1 <= asteroids.length <= 105
     * 1 <= asteroids[i] <= 105
     *
     * @param mass      - integer value representing the mass of the planet
     * @param asteroids - integer array of the mass of asteroids
     * @return - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {

        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT()
        Arrays.sort(asteroids);
        for (int i = 0; i < asteroids.length; i++) {
            if (mass < asteroids[i]) {
                return false;
            } else {
                mass += asteroids[i];
            }
        }

        return true;

    }


    /**
     * Method numRescueSleds
     * <p>
     * You are given an array people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     * <p>
     * Example 1:
     * Input: people = [1,2], limit = 3
     * Output: 1
     * Explanation: 1 sled (1, 2)
     * <p>
     * Example 2:
     * Input: people = [3,2,2,1], limit = 3
     * Output: 3
     * Explanation: 3 sleds (1, 2), (2) and (3)
     * <p>
     * Example 3:
     * Input: people = [3,5,3,4], limit = 5
     * Output: 4
     * Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people - an array of weights for people that need to go in a sled
     * @param limit  - the weight limit per sled
     * @return - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {

        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT
        Arrays.sort(people);
        // track who's already placed
        boolean[] usedOrNah = new boolean[people.length];
        int sledCount = 0;

        for (int i = 0; i < people.length; i++) {
            if (usedOrNah[i]) continue;
            // mark current person as placed
            usedOrNah[i] = true;

            if (people[i] == limit) {
                sledCount++; // goes alone without some one else
            } else {
                boolean paired = false;

                for (int j = people.length - 1; j > i; j--) {
                    if (!usedOrNah[j] && people[i] + people[j] <= limit) {
                        usedOrNah[j] = true;
                        paired = true;
                        break;
                    }
                }

                sledCount++; // either paired or alone
            }
        }
        return sledCount;

    }




}
