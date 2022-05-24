import java.sql.SQLDataException;

// 计数排序
class Solution {
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        int curr = 0;
        for (int i = 0; i < count[0] && curr < nums.length; i++) {
            nums[curr] = 0;
            curr++;
        }
        for (int i = 0; i < count[1] && curr < nums.length; i++) {
            nums[curr] = 1;
            curr++;
        }
        for (int i = 0; i < count[2] && curr < nums.length; i++) {
            nums[curr] = 2;
            curr++;
        }
    }
}

// 单指针
class Solution1 {
    public void sortColors(int[] nums) {
        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                swap(nums, i, curr++);
        }
        for (int i = curr; i < nums.length; i++) {
            if (nums[i] == 1)
                swap(nums, i, curr++);
        }
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}

// 双指针
class Solution2 {
    public void sortColors(int[] nums) {
        int zero = 0, two = nums.length - 1;
        for (int i = 0; i <= two; i++) {
            if (nums[i] == 0)
                swap(nums, i, zero++);
            if (nums[i] == 2)
                swap(nums, i--, two--);
        }
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}

// 刷漆
class Solution3 {
    public void sortColors(int[] nums) {
        int one = 0,zero = 0;
        for(int i = 0; i<nums.length; i++){
            int tmp = nums[i];
            nums[i] = 2;
            if(tmp <= 1){
                nums[one] = 1;
                one++;
            }
            if(tmp == 0){
                nums[zero] = 0;
                zero++;
            }
        }
    }
}