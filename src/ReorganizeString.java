import java.util.PriorityQueue;

public class ReorganizeString {
    private class pair implements Comparable<pair>{
        char c;
        int cnt;
        public pair(char c, int cnt){
            this.c = c;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(pair p2){
            int comp = Integer.compare(p2.cnt,this.cnt);
            if(comp!=0) return comp;
            return this.c-p2.c;
        }
    }
    public String reorganizeString(String S) {
        int len = S.length();
        if(len==0||len==1) return S;
        PriorityQueue<pair> pq = new PriorityQueue<>();
        int[] cnt = new int[26];
        for(char cur : S.toCharArray()){
            cnt[cur-'a']++;
        }
        for(int i=0;i<26;i++){
            if(cnt[i]>0)
                pq.offer(new pair((char)(i+'a'),cnt[i]));
        }
        int left = 0, right = 1;
        char[] res = new char[len];
        //todo : edge case, one side run out
        pair max = null, maxii = null;
        while(left<len&&right<len){
            if(max==null||max.cnt==0){
                if(pq.isEmpty())  break;
                max = pq.poll();
            }
            if(maxii==null||maxii.cnt==0){
                if(pq.isEmpty())  break;
                maxii = pq.poll();
            }
            res[left]  = max.c;
            res[right] = maxii.c;
            max.cnt--;
            maxii.cnt--;
            left+=2;
            right+=2;
        }
        if((len&1)==0){//even
            if(left==len) return new String(res);
            else return "";
        }else{//odd
            if(left==len-1){
                if(max.cnt==1) {
                    res[len - 1] = max.c;
                    return new String(res);
                }
                else if(max.cnt==0&&pq.size()==1){
                    max = pq.poll();
                    res[len-1] = max.c;
                    return new String(res);
                }
                else return "";
            }else{
                return "";
            }
        }
    }
    public static void main(String[] args){
        ReorganizeString r = new ReorganizeString();
        String res = r.reorganizeString("ababc");
        res = r.reorganizeString("abab");
        res = r.reorganizeString("ababb");
        res = r.reorganizeString("abaa");
        res = r.reorganizeString(
                "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao"
        );
    }
}
