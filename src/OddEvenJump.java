import java.util.*;

public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        //i: 0 1 2 3  4 5
        //   6 3 8 10 3 5
        //o: 2 4 3 x  5 x
        //e: 5 4 5 5  x x
        //si:1  4    5  0    2     3
        //   4  5    x  2    3     x
        //   145  45   x  023  23    3
        //rsi:     3    2   0   5   1   4
        //         5    5   5   x   4   x
        //         35   25  05  5   14  4
        int len = A.length,res=1;
        int[] ostep = new int[len];
        int[] estep = new int[len];
        boolean[] ob = new boolean[len];
        boolean[] eb = new boolean[len];
        int[] sortedIndex = new int[len];
        int[] l = new int[len];
        int min=Integer.MAX_VALUE,max= Integer.MIN_VALUE;
        for(int a:A){
            min = Math.min(a,min);
            max = Math.max(a,max);
        }
        int[] cntmap = new int[max-min+1];
        int total = 0, temp = 0;
        for(int a : A){
            cntmap[a-min]++;
        }
        for(int i = 0; i< cntmap.length;i++){
            temp += cntmap[i];
            cntmap[i] = total;
            total = temp;
        }
        sortHelper1(sortedIndex, cntmap, min, max, A,len,true);
        helper(l, ostep, sortedIndex, len);
        sortHelper1(sortedIndex, cntmap, min, max, A,len,false);
        helper(l, estep, sortedIndex, len);
        ob[len-1]= true;
        eb[len-1]= true;
        for(int i=len-2;i>=0;i--){
            if(ostep[i]!=-1){
                ob[i] = eb[ostep[i]];
            }
            if(estep[i]!=-1){
                eb[i] = ob[estep[i]];
            }
            if(ob[i]) res++;
        }
        return res;
    }
    private void sortHelper1(int[] sortedIndex, int[] orimap, int min, int max, int[] A,int alen,boolean naturalOrder){
        int[] cntmap = Arrays.copyOf(orimap,max-min+1);
        if(naturalOrder)
            for(int i=0;i<alen;i++){
                sortedIndex[cntmap[A[i]-min]++] = i;
            }
        else {// reverse order
            alen--;
            for (int i = alen; i >= 0; i--) {
                sortedIndex[alen - (cntmap[A[i] - min]++)] = i;
            }
        }
    }
    static int rerange(int[] A) {
        int min,max;
        min=max = A[0];
        for(int v : A) {
            if(v < min) min = v;
            if(v > max) max = v;
        }
        int[] map = new int[max-min+1];
        for(int v : A) {
            map[v-min] = 1;
        }
        int ix = 0;
        for(int i=0; i<map.length;i++) {
            if(map[i] != 0) map[i] = ++ix;
        }
        for(int i=0; i<A.length;i++) {
            A[i] = map[A[i]-min];
        }
        return ix;
    }
    static int ceilingIndex(int[] map, int v) {
        for(;v<map.length;v++) {
            if(map[v] != 0)
                return map[v];
        }
        return 0;
    }
    static int floorIndex(int[] map, int v) {
        for(;v > 0; v--) {
            if(map[v] != 0)
                return map[v];
        }
        return 0;
    }
    public static int oddEvenJump2(int[] A) {
        int max = rerange(A);
        int[] map = new int[max+1];
        int res = 1;
        final int N = A.length;
        map[A[N-1]] = N-1;
        boolean[] odds = new boolean[N];
        boolean[] evens = new boolean[N];
        odds[N-1] = evens[N-1] = true;
        for(int i=N-2; i>=0; i--) {
            int key = A[i];
            int minGE = ceilingIndex(map, key);
            int maxLE = floorIndex(map, key);
            if(minGE != 0 && evens[minGE]) {
                res ++;
                odds[i] = true;
            }
            if(maxLE != 0 && odds[maxLE]) {
                evens[i] = true;
            }
            map[key] = i;
        }
        return res;
    }

    public int oddEvenJumps3(int[] A){
        int len = A.length, res =1;
        int[] ostep = new int[len];
        int[] estep = new int[len];
        TreeMap<Integer,Integer> v2i = new TreeMap<>();
        boolean[] ojump = new boolean[len];
        boolean[] ejump = new boolean[len];
        //put(5,5) lgn
        //ceiling   floor lgn
        //put(3,4)
        //ceiling   floor lgn
        v2i.put(A[len-1],len-1);
        Map.Entry<Integer,Integer> ce,fl;
        for(int i = len-2;i>=0;i--){
            ce = v2i.ceilingEntry(A[i]);
            fl = v2i.floorEntry(A[i]);
            if(ce!=null) ostep[i]=ce.getValue();
            else    ostep[i]=-1;
            if(fl!=null) estep[i]=fl.getValue();
            else    estep[i]=-1;
            v2i.put(A[i],i);
        }
        ojump[len-1]=true;
        ejump[len-1]=true;
        for(int i = len-2;i>=0;i--){
            if(ostep[i]!=-1){
               if(ojump[i]= ejump[ostep[i]])
                   res++;
            }
            if(estep[i]!=-1){
               ejump[i]= ojump[estep[i]];
            }
        }
        return res;
    }
    public int oddEvenJumps4(int[] A) {
        //i: 0 1 2 3  4 5
        //   6 3 8 10 3 5
        //o: 2 4 3 x  5 x
        //e: 5 4 5 5  x x
        //si:1  4    5  0    2     3
        //   4  5    x  2    3     x
        //   145  45   x  023  23    3
        //rsi:     3    2   0   5   1   4
        //         5    5   5   x   4   x
        //         35   25  05  5   14  4
        int len = A.length,res=1;
        int[] ostep = new int[len];
        int[] estep = new int[len];
        boolean[] ob = new boolean[len];
        boolean[] eb = new boolean[len];
        Integer[] sortedIndex = new Integer[len];
        //LinkedList<Integer> l = new LinkedList<>();
        int[] l = new int[len];
        for(int i=0;i<len;i++){
            sortedIndex[i]  = i;
        }
        Arrays.sort(sortedIndex,(i1,i2)->Integer.compare(A[i1],A[i2]));
        helper2(l, ostep, sortedIndex, len);
        //l.clear();
        Arrays.sort(sortedIndex,(i1,i2)->Integer.compare(A[i2],A[i1]));
        helper2(l, estep, sortedIndex, len);
        ob[len-1]= true;
        eb[len-1]= true;
        for(int i=len-2;i>=0;i--){
            if(ostep[i]!=-1){
                ob[i] = eb[ostep[i]];
            }
            if(estep[i]!=-1){
                eb[i] = ob[estep[i]];
            }
            if(ob[i]) res++;
        }
        return res;
    }
    private void helper2(int[] l, int[] step, Integer[] sortedIndex, int len){
        int ptr = 0;
        l[ptr++] = sortedIndex[len-1];
        //l.offerLast(sortedIndex[len-1]);
        step[sortedIndex[len-1]]=-1;
        for(int i=len-2;i>=0;i--){
            //while(!l.isEmpty()&&sortedIndex[i]>l.getLast()){
            while(ptr>0&&sortedIndex[i]>l[ptr-1]){
                //l.removeLast();
                ptr--;
            }
            //if(!l.isEmpty())
            if(ptr>0)
                //step[sortedIndex[i]] = l.getLast();
                step[sortedIndex[i]] = l[ptr-1];
            else
                step[sortedIndex[i]] = -1;
            //l.addLast(sortedIndex[i]);
            l[ptr++] = sortedIndex[i];
        }
    }
    private void helper(int[] l, int[] step, int[] sortedIndex, int len){
        int ptr = 0;
        l[ptr++] = sortedIndex[len-1];
        //l.offerLast(sortedIndex[len-1]);
        step[sortedIndex[len-1]]=-1;
        for(int i=len-2;i>=0;i--){
            //while(!l.isEmpty()&&sortedIndex[i]>l.getLast()){
            while(ptr>0&&sortedIndex[i]>l[ptr-1]){
                //l.removeLast();
                ptr--;
            }
            //if(!l.isEmpty())
            if(ptr>0)
                //step[sortedIndex[i]] = l.getLast();
                step[sortedIndex[i]] = l[ptr-1];
            else
                step[sortedIndex[i]] = -1;
            //l.addLast(sortedIndex[i]);
            l[ptr++] = sortedIndex[i];
        }
    }
    public static void main(String[] args){
        Stack<Integer> sk = new Stack<>();
        sk.push(null);
        System.out.println(sk.isEmpty());
        System.out.println(sk.pop());
        int i = 0;
        int r = 6-(i++);
        OddEvenJump oej = new OddEvenJump();
        int res = oej.oddEvenJumps(new int[]{9,3,6,8,3,12,10});
        res = oej.oddEvenJumps(new int[]{1,2,3,2,1,4,4,5});
        res = oej.oddEvenJumps(new int[]{2,3,1,1,4});
        res = oej.oddEvenJumps(new int[]{10,13,12,14,15});
        res = oej.oddEvenJumps(new int[]{5,1,3,4,2});
    }
}
