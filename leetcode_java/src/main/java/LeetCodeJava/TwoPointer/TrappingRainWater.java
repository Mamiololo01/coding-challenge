package LeetCodeJava.TwoPointer;

// https://leetcode.com/problems/trapping-rain-water/description/

public class TrappingRainWater {

    // V0
    // TODO : implement
//    public int trap(int[] height) {
//
//    }

    // V1
    // https://github.com/neetcode-gh/leetcode/blob/main/java/0042-trapping-rain-water.java
    public int trap_1(int[] heights) {
        int left[] = new int[heights.length], right[] = new int[heights.length], max =
                heights[0], c = 0;

        for (int i = 0; i < heights.length; i++) {
            left[i] = Math.max(heights[i], max);
            max = left[i];
        }

        max = heights[heights.length - 1];
        for (int i = heights.length - 1; i >= 0; i--) {
            right[i] = Math.max(heights[i], max);
            max = right[i];
        }

        for (int i = 0; i < heights.length; i++) {
            c = c + Math.min(left[i], right[i]) - heights[i];
        }
        return c;
    }

    // V1'
    // https://github.com/neetcode-gh/leetcode/blob/main/java/0042-trapping-rain-water.java
    public int trap_1_1(int[] heights) {
        if (heights.length == 0) return 0;

        int l = 0, r = heights.length - 1;
        int leftMax = heights[l], rightMax = heights[r];
        int res = 0;

        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, heights[l]);
                res += leftMax - heights[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, heights[r]);
                res += rightMax - heights[r];
            }
        }

        return res;
    }


}
