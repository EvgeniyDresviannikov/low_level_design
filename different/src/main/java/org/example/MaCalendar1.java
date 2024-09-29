package org.example;

class MyCalendar1 {
    
    TreeNode root;

    public MyCalendar1() {
    }

    public boolean book(int start, int end) {
        if (root == null) {
            root = new TreeNode(start, end);
            return true;
        }
        return insert(root, start, end);
    }

    private boolean insert(TreeNode curr, int start, int end) {

        while (true) {
            if (start >= curr.end) {
                if (curr.right == null) {
                    curr.right = new TreeNode(start, end);
                    return true;
                }
                curr = curr.right;
            } else if (end <= curr.start) {
                if (curr.left == null) {
                    curr.left = new TreeNode(start, end);
                    return true;
                }
                curr = curr.left;
            } else {
                return false;
            }
        }
    }
}

class TreeNode {
    int start;
    int end;
    TreeNode left;
    TreeNode right;

    public TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
