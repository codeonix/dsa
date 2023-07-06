import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class LeetCode {
    public static List<List<Integer>> findTriplets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // Skip duplicates
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static int minSubArrayLen(int target, int[] nums) {
    return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1,-1,-1,0};
        List<List<Integer>> triplets = findTriplets(arr);

        for(List<Integer> triplet : triplets) {
            for(int i : triplet) {
                System.out.print(i+ " ");
            }
            System.out.println();
        }
    }
}
