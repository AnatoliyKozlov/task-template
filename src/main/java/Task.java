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
    public int[] insertElementInCenter(int[] numbers, int newElement) {
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

    /**
     * There is example method with:
     * <br> - Time complexity O(n²)
     * <br> - Space complexity O(1)
     */
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
}
