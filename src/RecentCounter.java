public class RecentCounter {
    private static final int LEN = 1_000_000_000 + 1;
    int[] ftree;
    public RecentCounter() {
        ftree = new int[LEN];
    }

    public int ping(int t) {
        update(t+1);
        return sum(t+1)-sum(t-3000);
    }
    private void update(int i){
        while(i<LEN){
            ftree[i]++;
            i+=(i&(-i));
        }
    }
    private int sum(int i){
        int res = 0;
        while(i>0){
            res+= ftree[i];
            i -=(i&(-i));
        }
        return res;
    }
    public static void main(String[] args){
        RecentCounter o1 = new RecentCounter();
        System.out.println(o1.ping(1));
    }

}
