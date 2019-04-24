import java.util.Arrays;

public class FriendsOfAppropriateAges {
    public int numFriendRequests(int[] ages) {
        int len = ages.length;
        if(len<=1) return 0;
        Arrays.sort(ages);
        //fist > 0.5[i]+7
        int ans = 0;
        int f1=0,f2=0;
        int lobound = 0;
        for(int i=0;i<len;i++){
            lobound = ages[i]/2+7;
            f1 = findFirstL(ages,lobound,f1,len);
            f2=i+1;
            while(f2<len&&ages[f2]==ages[i])
                f2++;
            ans+=Math.max(f2-f1-1,0);
        }
        return ans;
    }
    private int findFirstL(int[] a, int target, int lo, int len){
        while(lo<len){
            int mid = lo+(len-lo)/2;
            int comp = Integer.compare(a[mid],target);
            if(comp>0) len = mid;
            else lo = mid+1;
        }
        return len;
    }
    public static void main(String[] args){
        int res = new FriendsOfAppropriateAges().numFriendRequests(new int[]{16,17,18});
    }
}
