public class editDistance {
    char[] w1;
    char[] w2;
    public int minDistance(String word1, String word2) {
        int i = word1.length(),j = word2.length();
        char[] w1 = new char[i+1];
        char[] w2 = new char[j+1];
        System.arraycopy(word1.toCharArray(),0,w1,1,i);
        System.arraycopy(word2.toCharArray(),0,w2,1,j);
        int[][] backUp =  new int[i+1][j+1];
        for(int t=0; t<i+1;t++) backUp[t][0] = t;
        for(int t=0; t<j+1;t++) backUp[0][t] = t;
        for(int t=1;t<i+1;t++){
            for(int tt = 1;tt<j+1;tt++)
                if(w1[t] == w2[tt])
                    backUp[t][tt] = backUp[t-1][tt-1];
                else{
                    backUp[t][tt] =  1+Math.min(Math.min(backUp[t-1][tt],backUp[t][tt-1]),backUp[t-1][tt-1]);
                }
        }
        return backUp[i][j];
    }
    public int minDistanceOriginal(String word1, String word2) {
//corner case word1 or 2 null
        w1 = word1.toCharArray();
        w2 = word2.toCharArray();
        return helper(w1.length-1,w2.length-1,0);
    }
    private int helper(int i, int j, int cnt){
        if(i>=0 && j>=0) {
            if(w1[i]==w2[j]) return helper(i--,j--,cnt);
            else {
                return Math.min(Math.min(helper(i--,j--,cnt++),helper(i--,j,cnt++)),helper(i,j--,cnt++));
            }
        }else{
            if(j<0){
                return cnt+((i>=0)?(i+1):0);
            }else{//j>=0,i<0
                return cnt+1+j;
            }
        }
    }
    public static void main(String[] args){
       //int t = new editDistance().minDistance("horse","ros") ;
       int t = new editDistance().minDistanceOriginal("horse","ros") ;
    }

}
