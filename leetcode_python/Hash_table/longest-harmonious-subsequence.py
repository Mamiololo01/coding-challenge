# Time:  O(n)
# Space: O(n)

# We define a harmonious array is an array where the difference
# between its maximum value and its minimum value is exactly 1.
#
# Now, given an integer array, you need to find the length of its
# longest harmonious subsequence among all its possible subsequences.
#
# Example 1:
# Input: [1,3,2,2,5,2,3,7]
# Output: 5
# Explanation: The longest harmonious subsequence is [3,2,2,2,3].
# Note: The length of the input array will not exceed 20,000.


# V1  : dev 

# V2 
# https://www.jianshu.com/p/a155ea910fb5
class Solution(object):
    def findLHS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums_count = {}
        for num in nums:
            nums_count[num] = nums_count.get(num, 0) + 1

        result = 0
        for num in nums_count:
            count = nums_count[num]
            if nums_count.get(num+1):
                result = max(result, count + nums_count[num+1])
        return result

# V3 
import collections


class Solution(object):
    def findLHS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        lookup = collections.defaultdict(int)
        result = 0
        for num in nums:
            lookup[num] += 1
            for diff in [-1, 1]:
                if (num + diff) in lookup:
                    result = max(result, lookup[num] + lookup[num + diff])
        return result



