public class Solution {

    public int FindSortedArrayRotation(int array[], int length) {
        if (array == null || length == 0)
            return -1;
        if (array[0] < array[1] && array[0] < array[length - 1])
            return 0;
        else if (array[length - 1] < array[length - 2] && array[length - 1] < array[0])
            return length - 1;
        else {
            int left = 1, right = length - 2;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1])
                    return mid;
                else if (array[mid] > array[left]) {
                    left = mid + 1;
                } else
                    right = mid;
            }
        }
        return -1;
    }
}