import java.util.HashSet;

public class CoinChange {
    public int coinChange_Old(int[] coins, int amount) {
        HashSet<Integer> pre = new HashSet<>();
        HashSet<Integer> next = new HashSet<>();
        boolean alllarger = true;
        int stepcnt = 1;
        pre.add(0);
        while(true){
            for(int pree:pre){
               for(int coin:coins){
                   int newcoin = pree+coin;
                   if(newcoin==amount) return stepcnt;
                   if(newcoin<amount) alllarger = false;
                   next.add(newcoin);
               }
            }
            if(alllarger) break;
            alllarger = true;
            stepcnt++;
            pre = next;
            next=new HashSet<>();
        }
        return -1;
    }
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) dp[i] = 0x7fff_fffe;
        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        return dp[amount] == 0x7fff_fffe ? -1 : dp[amount];
    }
    public static void main(String[] args){
       int res = new CoinChange().coinChange(
          new int[]{5,2,1},6
       );
       int aa = Integer.parseInt("-012");
       char f = "012".charAt(0);
       aa = Integer.parseInt("-fDff",16);
       int k  = 3;
       double test = (double)k/5;
       test = k/(double)5;
    }
}
