//给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。 
//
// 注意： 
//数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。 
//
// 示例: 
//
// 
//int[] nums = new int[] {1,2,3,3,3};
//Solution solution = new Solution(nums);
//
//// pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
//solution.pick(3);
//
//// pick(1) 应该返回 0。因为只有nums[0]等于1。
//solution.pick(1);
// 
// Related Topics 水塘抽样 哈希表 数学 随机化 
// 👍 151 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：随机数索引
 class P398RandomPickIndex{
    public static void main(String[] args) {
        int[] nums = {3,5,5};
        Solution solution = new P398RandomPickIndex().new Solution(nums);
        // TO TEST
        int pikNum = solution.pick(5);
        System.out.println("输入：" + Arrays.toString(nums) );
        System.out.println("输出：" + pikNum );
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] nums;
        Random random;
        //构造函数初始化数据
        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }
        //调用获取目标随机下标索引
        public int pick(int target) {
            int res = 0;
            //初始循环起始i，meetCnt,循环遍历数组长度
            for (int i = 0, meetCnt = 0; i < nums.length; ++i) {
                //每层遍历当与目标数相等时候判断
                if (nums[i] == target) {
                    ++meetCnt; // 第 meetCnt 次遇到 target meetCnt 加1
                    //获取随机[0,meetCnt)是否得0 则可等到 1/meetCnt 概率
                    if (random.nextInt(meetCnt) == 0) {
                        //小标索引赋值
                        res = i;
                    }
                }
            }
            //返回索引下标
            return res;
        }
    }

    /*class Solution {
        //初始化数据
        Map<Integer, List<Integer>> pos;
        Random random;
        public Solution(int[] nums) {
            //建立map
            pos = new HashMap<Integer, List<Integer>>();
            random = new Random();
            for (int i = 0; i < nums.length; ++i) {
                //给所有数字添加key和数组value出现下标索引
                pos.putIfAbsent(nums[i], new ArrayList<Integer>()); //当出现返回旧的数组，否则插入新数组
                pos.get(nums[i]).add(i);//给数组添加下标
            }
        }
        public int pick(int target) {
            //获得目标key的value数组
            List<Integer> indices = pos.get(target);
            //获得随机索，取索引数组长度为概率分母，概率均一样
            return indices.get(random.nextInt(indices.size()));
        }
    }*/

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
//leetcode submit region end(Prohibit modification and deletion)

}