/**
 * This is a class example of a task whose methods have varying complexity from O(1) to 0(n³)
 */
public class Task {

    /**
     * There is example method with:
     * <br> - Time complexity O(n)
     * <br> - Space complexity O(1)
     *
     * @param numbers
     * @return value of highest value in massive
     */
    public int findMaxElement(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        int max = numbers[0];
        for (var num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * There is example method with:
     * <br> - Time complexity O(n)
     * <br> - Space complexity O(n)
     *
     * @param numbers
     * @param newElement
     * @return value of highest value in massive
     */
    public int[] insetElementInCenter(int[] numbers, int newElement) {
        if (numbers.length == 0) {
            return numbers;
        }
        var newArray = new int[numbers.length + 1];
        for (int i = 0; i < numbers.length / 2; i++) {
            newArray[i] = numbers[i];
        }
        newArray[numbers.length / 2 + 1] = newElement;
        for (int i = numbers.length / 2 + 2; i < numbers.length; i++) {
            newArray[i] = numbers[i];
        }
        return newArray;
    }
}
