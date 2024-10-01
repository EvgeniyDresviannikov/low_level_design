package org.example;

import java.util.Arrays;

class RangeSumQueryImmutable {


    private int[] numsArr;
    private int[] segmentSumArr;

    public RangeSumQueryImmutable(int[] nums) {
        int treeSize = 2*nums.length;
        segmentSumArr = new int[treeSize];
        numsArr = Arrays.copyOf(nums, nums.length);

        constructTree(0, nums.length-1, 0);
    }


    public int sumRange(int left, int right) {
        return sumRange(left, right, 0, numsArr.length-1, 0);
    }

    private void constructTree(int left, int right, int pos) {
        if (left == right) {
            segmentSumArr[pos] = numsArr[left];
            return;
        }

        int mid = (left + right) / 2;
        constructTree(left, mid, 2*pos+1);
        constructTree(mid+1, right, 2*pos+2);
        segmentSumArr[pos] = segmentSumArr[2*pos+1] + segmentSumArr[2*pos+2];
    }

    private int sumRange(int left, int right, int low, int high, int pos) {
        if (left <= low && right >= high) {
            return segmentSumArr[pos];
        }

        if (left > high || right < low) {
            return 0;
        }

        int mid = (low + high) / 2;

        int sumLeft = sumRange(left, right, low, mid, 2*pos+1);
        int sumRight = sumRange(left, right, mid+1, high, 2*pos+2);

        return sumLeft + sumRight;

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */