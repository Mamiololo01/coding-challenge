package LeetCodeJava.BinarySearch;

// https://leetcode.com/problems/longest-increasing-subsequence/description/

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // V0
    // TODO : implement it
//    public int lengthOfLIS(int[] nums) {
//        return 0;
//    }

    // V1
    // IDEA : DP
    // https://leetcode.com/problems/longest-increasing-subsequence/solutions/4509493/300/
    public int lengthOfLIS_1(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp,1);
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int maxi=1;
        for(int len : dp){
            maxi=Math.max(maxi,len);
        }
        return maxi;
    }

    // V2
    // IDEA : BINARY SEARCH
    // https://leetcode.com/problems/longest-increasing-subsequence/solutions/4509303/beats-100-binary-search-explained-with-video-c-java-python-js/
    public int lengthOfLIS_2(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }

    // V3
    // IDEA : DP
    // https://leetcode.com/problems/longest-increasing-subsequence/solutions/4510776/java-solution-explained-in-hindi/
    public int lengthOfLIS_3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int len : dp) {
            maxLength = Math.max(maxLength, len);
        }
        return maxLength;
    }

}
