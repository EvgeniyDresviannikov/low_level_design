package org.example;

// TODO rewrite using segment tree
class RangeSumQuery {
    private int[] numsArr;
    private int[] sums;

    public RangeSumQuery(int[] nums) {
        sums = new int[nums.length];
        numsArr = new int[numsArr.length];
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            sums[i] = currentSum;
            numsArr[i] = nums[i];
        }
    }

    public void update(int index, int val) {
        int old = numsArr[index];
        int diff = val - old;
        numsArr[index] = val;

        for (int i = index; i < sums.length; i++) {
            sums[i] = sums[i] + diff;
        }
    }

    public int sumRange(int left, int right) {
        if (left == right) return numsArr[left];
        if (left == 0) return sums[right];

        return sums[right] - sums[left-1];
    }
}
