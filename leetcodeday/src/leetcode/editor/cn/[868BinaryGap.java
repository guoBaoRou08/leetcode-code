//给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。 
//
// 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"100
//1" 中的两个 1 的距离为 3 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：n = 22
//输出：2
//解释：22 的二进制是 "10110" 。
//在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
//第一对相邻的 1 中，两个 1 之间的距离为 2 。
//第二对相邻的 1 中，两个 1 之间的距离为 1 。
//答案取两个距离之中最大的，也就是 2 。
// 
//
// 示例 2： 
//
// 
//输入：n = 8
//输出：0
//解释：8 的二进制是 "1000" 。
//在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
// 
//
// 示例 3： 
//
// 
//输入：n = 5
//输出：2
//解释：5 的二进制是 "101" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 109 
// 
// Related Topics 位运算 数学 
// 👍 98 👎 0

package leetcode.editor.cn;
//Java：二进制间距
 class P868BinaryGap{
    public static void main(String[] args) {
        Solution solution = new P868BinaryGap().new Solution();
        // TO TEST
        int inPut = 220;
        int outPut = solution.binaryGap(inPut);
        System.out.println("输入："+inPut);
        System.out.println("输出："+outPut);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int binaryGap(int n) {
            //初始第一次出现位置，长度初始为0
            int lastLocal = -1, res = 0;
            //对目标数字二进制循环判断，如果等于0则输出res
            for (int i = 0; n != 0; ++i) {
                //与1位与判断是否最右面的数字是1还是0，
                // 如果为0 则 n向低位右移动寻找1的位置如果为1进入判断
                if ((n & 1) == 1) {
                    //第一次出出现位置 不是-1则是第n次1出现位置
                    if (lastLocal != -1) {
                        //i - lastLocal 表示最新出现1的位置 和上次出现1的位置之间距离
                        //上次距离计算和最新距离计算刷新结果取最远值
                        res = Math.max(res, i - lastLocal);
                    }
                    //是第一次发现并把的二次位置赋值到变量 或 直接更新最新发现1的位置
                    lastLocal = i;
                }
                n >>= 1;
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}