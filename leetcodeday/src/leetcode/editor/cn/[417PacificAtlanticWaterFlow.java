//有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。 
//
// 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上
//单元格 高于海平面的高度 。 
//
// 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。 
//
// 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋
// 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
//输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
// 
//
// 示例 2： 
//
// 
//输入: heights = [[2,1],[1,2]]
//输出: [[0,0],[0,1],[1,0],[1,1]]
// 
//
// 
//
// 提示： 
//
// 
// m == heights.length 
// n == heights[r].length 
// 1 <= m, n <= 200 
// 0 <= heights[r][c] <= 105 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 
// 👍 419 👎 0

package leetcode.editor.cn;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：太平洋大西洋水流问题
class P417PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        Solution solution = new P417PacificAtlanticWaterFlow().new Solution();
        int[][] arrs = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<List<Integer>> res = solution.pacificAtlantic(arrs);
        System.out.println("输入：" );
        for(int i = 0; i < arrs.length; i++) {
            for(int j = 0; j < arrs[i].length; j++) {
                System.out.print(arrs[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("输出：" );
        for (List<Integer> arr:
             res) {
            System.out.print(arr+"\n");
        }
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //建立返回二维数组列
        List<List<Integer>> res = new ArrayList<>();
        int[][] island; //变量岛屿
        //建立二维数组逻辑变量
        // 1.共同流入的海域 2.流入的a海域 3.流入的p海域
        boolean[][] anyOcean, aOcean, pOcean;
        //建立dfs搜索方法
        public void dfs(int i, int j) {
            //如果共同流入的海域标记为true，则退出
            if (anyOcean[i][j])
                return;
            //没有标记则，标记可流入
            anyOcean[i][j] = true;
            //a和p海域均有标记则追加二维数组，只有一次，切没有重复解
            if (aOcean[i][j] && pOcean[i][j])
                res.add(Arrays.asList(i, j));
            //前一个条件为判断true说明还有岛屿可进入后一个为可以海水可以流入
            if (i + 1 < island.length && island[i][j] <= island[i + 1][j])//这个判断分支是从（i,0）开始遍历搜索
                dfs(i + 1, j); // 总判断为true即为可流入递归进入调用在次搜索其他未经过顶点
            if (i - 1 >= 0 && island[i][j] <= island[i - 1][j])//这个判断分支是从（最大列数,0）开始遍历搜索
                dfs(i - 1, j); //同上

            if (j + 1 < island[0].length && island[i][j] <= island[i][j + 1])//这个判断分支是从（0,i）开始遍历搜索
                dfs(i, j + 1); //同上
            if (j - 1 >= 0 && island[i][j] <= island[i][j - 1])//这个判断分支是从（0,最大行数）开始遍历搜索
                dfs(i, j - 1); //同上
        }

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            //分别初始化 a，p海域和岛屿
            aOcean = new boolean[heights.length][heights[0].length];
            pOcean = new boolean[heights.length][heights[0].length];
            island = heights;
            //先赋值可流入任何海域指向a海域，作用是在二维逻辑数组中标记可以流入a海域的岛屿
            anyOcean = aOcean;
            //遍历岛屿行数
            for (int i = 0; i < heights.length; ++i)
                dfs(i, 0); // 第0列固定调用dfs从（i,0）开始
            //遍历岛屿列数
            for (int i = 0; i < heights[0].length; ++i)
                dfs(0, i); // 第0行固定调用dfs从（0,i）开始
            //anyOcean重新指向p海域 二维数组刷新 重新初始化
            anyOcean = pOcean;
            //遍历岛屿行数
            for (int i = 0; i < heights.length; ++i)
                dfs(i, heights[0].length - 1); // 最后列固定开始调用dfs从（i,最大行数）开始
            //遍历岛屿列数
            for (int i = 0; i < heights[0].length; ++i)
                dfs(heights.length - 1, i); // 最后行固定开始调用dfs从（最大列数,i）开始
            //返回结果
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}