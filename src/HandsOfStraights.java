public class HandsOfStraights {
    /**
     *Alice has a hand of cards, given as an array of integers.

    Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

            Return true if and only if she can.



    Example 1:

    Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
    Output: true
    Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].

    Example 2:

    Input: hand = [1,2,3,4,5], W = 4
    Output: false
    Explanation: Alice's hand can't be rearranged into groups of 4.



    Note:

            1 <= hand.length <= 10000
            0 <= hand[i] <= 10^9
            1 <= W <= hand.length

     */
    public boolean isNStraightHand(int[] hand, int W){
        if(W==1) return true;
        //int[] bucket = new int[1_000_000_001];
        int[] bucket = new int[Integer.MAX_VALUE-1000];
        for(int n:hand){
            bucket[n]++;
        }
        int st=0,cur = 0;
        int cnt = 1,nextst = 0;
        boolean find = false;
        while(st<=1_000_000_000&&bucket[st++]==0);
        st--;//st is the first val, whose bucket[st]!=0
        while(st<=1_000_000_000){
            cnt = 1;
            cur = st+1;
            find = false;
            while(cnt<W&&cur<=1_000_000_000){
                bucket[cur] -=bucket[st];
                if(bucket[cur]<0) return false;
                if(!find&&bucket[cur]>0) {
                    find = true;
                    nextst = cur;
                }
                cnt++;
                cur=st+cnt;
                //if(bucket[cur]<bucket[st]){
                //   return false;
                //}else{
                //   bucket[cur] -= bucket[st];
                //}
                //cnt++;
                //cur=st+cnt;
            }
            if(cnt<W) return false;
            if(find) st = nextst;
            else{
                st += W;
                while(st<=1_000_000_00&&bucket[st]==0) st++;
            }
        }
        return true;
    }
    public static void main(String[] args){
        HandsOfStraights h = new HandsOfStraights();
        boolean res = h.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8},3);
    }

}
