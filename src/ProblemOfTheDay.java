public class ProblemOfTheDay {
    public static int longestSubArray1493(int[] nums) {
        int left = 0;  // Left pointer of the sliding window
        int right = 0; // Right pointer of the sliding window
        int maxLen = 0; // Maximum length of the sub-array

        int countZeros = 0; // Count of zeros encountered in the sliding window

        while (right < nums.length) {
            if (nums[right] == 0) {
                countZeros++;
            }

            // Shrink the window if there are more than one zeros
            while (countZeros > 1) {
                if (nums[left] == 0) {
                    countZeros--;
                }
                left++;
            }

            // Update the maximum length if applicable
            maxLen = Math.max(maxLen, right - left);

            right++; // Expand the window
        }

        return maxLen;
    }

    public static void main(String[] args) {
    int[] arr = {0,1,1,1,0,1,1,0,1};
        System.out.println(longestSubArray1493(arr));
    }
}
