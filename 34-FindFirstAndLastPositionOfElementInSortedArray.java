/*
左闭右开
*/
class Solution1 {
    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[2];
        range[0] = left_bound(nums, target);
        range[1] = right_bound(nums, target);
        return range;
    }

    int left_bound(int[] nums, int target) {
        if (nums.length == 0) 
            return -1;
        int left = 0;
        int right = nums.length; //左闭右开
        while (left < right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num == target) 
                right = mid;
            else if (num < target)
                left = mid + 1;
            else if (num > target)
                right = mid;
        }
        if (left == nums.length) //注意：left = mid + 1 => array中所有元素小于target, left最终为nums.length
            return -1;
        return nums[left] == target ? left : -1; 
    }

    int right_bound(int[] nums, int target) {
        if (nums.length == 0) 
            return -1;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num == target) 
                left = mid + 1; //注意
            else if (num < target)
                left = mid + 1; //注意
            else if (num > target)
                right = mid;
        }
        if (left == 0) //注意：array中所有元素大于target，left等于0
            return -1;
        return nums[left-1] == target ? (left-1) : -1;  //注意
    }
}

/*
左右双闭
*/
class Solution2 {
    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[2];
        range[0] = left_bound(nums, target);
        range[1] = right_bound(nums, target);
        return range;
    }

    int left_bound(int[] nums, int target) {
        if (nums.length == 0) 
            return -1;
        int left = 0;
        int right = nums.length - 1; // 左右双闭
        while (left <= right) { // 终止条件为 left == right + 1
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num == target) 
                right = mid - 1;
            else if (num < target)
                left = mid + 1;
            else if (num > target)
                right = mid - 1;
        }
        if (left == nums.length) //注意：left = mid + 1 => array中所有元素小于target, left最终为nums.length
            return -1;
        return nums[left] == target ? left : -1; 
    }

    int right_bound(int[] nums, int target) {
        if (nums.length == 0) 
            return -1;
        int left = 0;
        int right = nums.length - 1; // 左右双闭
        while (left <= right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num == target) 
                left = mid + 1; //注意
            else if (num < target)
                left = mid + 1; //注意
            else if (num > target)
                right = mid - 1;
        }
        if (left == 0) //注意：array中所有元素大于target，left等于0
            return -1;
        return nums[left-1] == target ? (left-1) : -1;  //注意
    }
}

/*
左右双闭 + 统一bound算法
*/
class Solution3 {
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = bound(nums, target, true);
        int rightIdx = bound(nums, target, false) - 1; // 注意
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target)
            return new int[]{leftIdx, rightIdx};
        return new int[]{-1, -1};
    }

    int bound(int[] nums, int target, boolean left_bound) {
        if (nums.length == 0) 
            return -1;
        int left = 0;
        int right = nums.length - 1; // 左右双闭
        int answer = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num > target || (num == target && left_bound)) {
                right = mid - 1;
                answer = mid;
            }
            else
                left = mid + 1;
        }
        return answer;
    }
}