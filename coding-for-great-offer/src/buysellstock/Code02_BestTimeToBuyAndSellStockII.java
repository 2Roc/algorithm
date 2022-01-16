package BuyAndSellStock;

/**
 leetcode 122
 买卖股票，允许买卖任意次数
 */
public class Code02_BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        for (int m = 0; m < prices.length - 1; m++) {
            if (prices[m + 1] > prices[m]) {
                ans += (prices[m + 1] - prices[m]);
            }
        }
        return ans;
    }

    //牛客
    public int maxProfit2 (int[] prices) {
        int ans = 0;
        for(int i = 1 ; i < prices.length ; i++)
        {
            if(prices[i]>prices[i-1])
            {
                ans+= prices[i]-prices[i-1];
            }

        }
        return ans;
    }
}
