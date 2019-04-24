public class PermutationSequence {
    private static enum Color{h,j,k}
    private class MYLIST{
        boolean[] used;
        int length;
        public MYLIST(int size){
            length = size;
            used = new boolean[size];
        }
        public char get(int index){
            int i = 0;
            for(;i<length;i++){
                if(!used[i]){
                    if(index--==0) break;
                }
            }
            return (char)(++i+'0');
        }
        public char remove(int index){
            char res = get(index);
            used[index] = true;
            return res;
        }
    }
    public String getPermutation(int n, int k) {
        //int factor = getFactor(n-1);
        k--;
        char[] res = new char[n];
        int cur = 0;
        //ArrayList<Character> mylist = new ArrayList<>(n);
        MYLIST mylist = new MYLIST(n);
        //for(int i=1;i<=n;i++)
        //    mylist.add((char)(i+'0'));
        for(int i=0,factor=getFactor(n-1);i<n-1;i++){
            cur = k/factor;
            k %=factor;
            factor /= (n-i-1);
            res[i]=mylist.remove(cur);
        }
        res[n-1] = mylist.get(0);
        return String.valueOf(res);
    }
    public int getFactor(int n){
        if(n==0) return 0;
        else if(n==1) return 1;
        else return n*getFactor(n-1);
    }
    public static void main(String[] args){
       PermutationSequence p = new PermutationSequence();
       String res = p.getPermutation(4,9);
       int t = 3;
       int tt = t*(t+1) /2;
       t = 4;
       tt = t*(t+1) /2;
    }
}
