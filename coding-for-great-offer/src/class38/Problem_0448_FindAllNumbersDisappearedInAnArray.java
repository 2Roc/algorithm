package class38;

import java.util.ArrayList;
import java.util.List;

public class Problem_0448_FindAllNumbersDisappearedInAnArray {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            // 从i位置出发，去玩下标循环怼
            walk(nums, i);
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public static void walk(int[] nums, int i) {
        while (nums[i] != i + 1) { // 不断从i发货
            int nexti = nums[i] - 1;
            if (nums[nexti] == nexti + 1) {
                break;
            }
            swap(nums, i, nexti);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 缺失的第一个正整数
     * <p>
     * 给定一个无重复元素的整数数组nums，请你找出其中没有出现的最小的正整数
     * 进阶： 空间复杂度O(1)时间复杂度O(n)
     * <p>
     * 看了leetcode的空间O(1)解法自己写了遍代码，记录一下思路吧 主要是把原来数组当做hash表用的思想，
     * 由于如果数组中有空隙，解肯定在[1,n]之间，那么在数组相应的[0,n-1]位置标记这些存在的元素，
     * 比如遍历到一个4，那么就把下标为4-1=3的位置的值置为负数，置为负数这个点很巧妙，
     * 这个负数不能随意设置一个负数，必须是原来正数的相反数，因为如若不然，会篡改后面的信息。
     * 那么这就又引出了问题，数组中原来的负数怎么处理呢，答案是在上述步骤前先置为n+1,超过数组界限，
     * 这样不会修改数组中的位置信息,统一初始状态，避免置为相反数时冲突的问题。
     *
     * @param nums
     * @return
     */
    public int minNumberDisappeared(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0)
                nums[i] = len + 1;
        }
        for (int i = 0; i < len; i++) {
            int x = nums[i] < 0 ? -nums[i] : nums[i];

            if (x > 0 && x < len + 1)
                nums[x - 1] = -nums[x - 1];
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0)
                return i + 1;
        }
        return len + 1;
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 10;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
//            int[] arr = GenerateRandomArray.generateRandomArray(N, maxValue);
            int[] arr = new int[]{4, 5, 6, 8, 7};

            List<Integer> list = findDisappearedNumbers(arr);
            list.forEach(System.out::println);
        }
    }
}
