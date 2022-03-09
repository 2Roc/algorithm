package class02;

import java.util.HashMap;

/**
 * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
 */
public class RightmostViewofBinaryTree {
        /**
         * 答案数组的大小其实是对应二叉树的高度，或者说这二叉树有多少层。
         * 二叉树每层只能有一个顶点，这个顶点就是二叉树该层最右边的节点
         * 我构建二叉树是递归的构建左子树和右子树，这样左子树的最右子节点会
         * 被右子树的最右子节点给覆盖掉(前提是该节点要在这二叉树的同一层)
         * 当我们构建完二叉树后，答案也构建好了，我们这要将答案写入放回数组就行。
         * @return int整型一维数组
         */
        private HashMap<Integer, Integer> ans = new HashMap<>();
        private HashMap<Integer, Integer> map = new HashMap<>();
        private int j = 0;
        //对应二叉树的高度，或者有多少层
        public int[] solve (int[] xianxu, int[] zhongxu) {
            //将xianxu节点的值映射到相应的中序节点的下标。
            for(int i = 0; i < zhongxu.length; i++){
                map.put(zhongxu[i], i);
            }
            //开始构建二叉树。
            build(xianxu, zhongxu, 0, xianxu.length-1, 0);

            //创建放回答案的数组
            int[] temp = new int[ans.size()];


            //将值传入数组
            for(int i = 0; i < ans.size(); i++){
                temp[i] = ans.get(i);
            }

            return temp;
        }

        public void build(int[] xianxu, int[] zhongxu, int left, int right, int i) {
            if (left > right) {
                return;
            }
            int index = map.get(xianxu[j++]);
            //构建左子树
            build(xianxu, zhongxu, left, index - 1, i + 1);
            //构建右子树
            build(xianxu, zhongxu, index + 1, right, i + 1);
            //存储第i层的最右边的那个节点。
            ans.put(i, zhongxu[index]);
        }
}
