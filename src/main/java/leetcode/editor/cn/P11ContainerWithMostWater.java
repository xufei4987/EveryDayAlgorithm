package leetcode.editor.cn;
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。
//
//
//
//
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
//
//
// 示例：
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49
// Related Topics 数组 双指针

//Java：盛最多水的容器
public class P11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new P11ContainerWithMostWater().new Solution();
        // TO TEST
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = solution.maxArea1(arr);
        System.out.println(maxArea);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
//            if(height == null || height.length < 2) return 0;
//            int max = 0;
//            for (int i = 0; i < height.length - 1; i++){
//                for (int j = i + 1; j < height.length; j++){
//                    int cur = (j - i) * Math.min(height[i],height[j]);
//                    if(cur > max){
//                        max = cur;
//                    }
//                }
//            }
//            return max;
            if (height == null || height.length < 2) return 0;
            int max = 0, cur = 0, l = 0, r = height.length - 1;
            while (l < r) {
                cur = (r - l) * Math.min(height[l], height[r]);
                if (cur > max) max = cur;
                if (height[l] > height[r]) {
                    r--;
                } else {
                    l++;
                }
            }
            return max;
        }

        public int maxArea1(int[] height) {
            if (height == null || height.length < 2) return 0;
            int l = 0;
            int r = height.length - 1;
            int maxArea = 0;
            while (l < r) {
                int minH = Math.min(height[l],height[r]);
                maxArea = Math.max(maxArea,(r-l)*minH);
                if(height[l] < height[r]){
                    int minl = height[l];
                    l++;
                    while (l < r && height[l] <= minl){
                        l++;
                    }
                }else {
                    int minr = height[r];
                    r--;
                    while (l < r && height[r] <= minr){
                        r--;
                    }
                }
            }
            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
