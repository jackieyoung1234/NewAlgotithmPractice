import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NQueen {
    public List<List<String>> solveNQueens(int n) {
        Map<Integer,int[]> hash = new HashMap<>();
        char[] s = new char[n];
        for(int i=0;i<n;i++){
           s[i] = '.';
        }
        int i = 0;
        while(i<n)
            hash.put(i++,new int[n]);
        List<List<String>> result = new ArrayList<>();
        helper(0,hash,new ArrayList<Integer>(),n,result,s);
        return result;
    }
    private void helper(int rowN, Map<Integer,int[]> hash, ArrayList<Integer> toNow, int n, List<List<String>> result, char[] s){
        if(rowN == n) {
            if (toNow.size() == n) {
                ArrayList<String> toNowS = new ArrayList<>();
                for (int i : toNow) {
                    s[i] = 'Q';
                    toNowS.add(String.valueOf(s));
                    s[i] = '.';
                }
                result.add(toNowS);
            }
            return;
        }
        int[] temp = hash.get(rowN);
        for(int i=0; i<n;i++)
        if(temp[i]==0){
            temp[i]++;
            toNow.add(i);
            //set rows below
            int k = 1;
            boolean c1 = (i-k)>=0;
            boolean c2 = (i+k)<n;
            while(rowN+k<n){
                int[] tempK = hash.get(rowN+k);
                if(c1)
                    tempK[i-k]++;
                if(c2)
                    tempK[i+k]++;
                tempK[i]++;
                k++;
                c1 = (i-k)>=0;
                c2 = (i+k)<n;
            }
            helper(rowN+1,hash,toNow,n,result,s);
            temp[i]--;
            toNow.remove(toNow.size()-1);
            //reset rows below
            k = 1;
            c1 = (i-k)>=0;
            c2 = (i+k)<n;
            while(rowN+k<n){
                int[] tempK = hash.get(rowN+k);
                if(c1)
                    tempK[i-k]--;
                if(c2)
                    tempK[i+k]--;
                tempK[i]--;
                k++;
                c1 = (i-k)>=0;
                c2 = (i+k)<n;
            }
        }
    }
    public static void main(String[] args){
        List<List<String>> res = new NQueen().solveNQueens(4);
    }
}
