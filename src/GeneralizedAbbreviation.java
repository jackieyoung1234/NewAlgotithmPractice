import java.util.LinkedList;
import java.util.List;

public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        int len = word.length();
        //List<String> res = new LinkedList<>();
        List<String>[] dp = new LinkedList[len];
        for(int i=0;i<len;i++)
            dp[i] = new LinkedList<>();
        helper(word,word.length(),0,new StringBuilder(),dp);
        return dp[0];
    }
    //private void helper(String word2n,int totallength, int start, StringBuilder sb2n, List<String> res){
    private void helper(String word2n,int totallength, int start, StringBuilder sb2n, List<String>[] res){
        int len = totallength-start;
        if(len==0){
            res[0].add(sb2n.toString());
            return;
        }
        else if(start>0&&!res[start].isEmpty()){
            for(String s:res[start])
                res[0].add(sb2n.toString()+s);
            return;
        }else{
            res[0].add(sb2n.toString()+len);
        }
        int size = sb2n.length();
        for(int i=0;i<len;i++){
            sb2n.append(i==0?"":i);
            sb2n.append(word2n.charAt(i+start));
            helper(word2n,totallength,i+start+1,sb2n,res);
            sb2n.setLength(size);
        }
        if(size<totallength&&res[size].isEmpty()){
                for(String s: res[0]){
                    if(s.length()>size)
                        res[size].add(s.substring(size));
                }
        }
    }
    public List<String> generateAbbreviationsII(String word) {
        List<String> res = new LinkedList<>();
        helperII(word,word.length(),0,new StringBuilder(),res);
        return res;
    }
    private void helperII(String word2n,int totallength, int start, StringBuilder sb2n, List<String> res){
        int len = totallength-start;
        if(len==0){
            res.add(sb2n.toString());
            return;
        }else{
            res.add(sb2n.toString()+len);
        }
        int size = sb2n.length();
        for(int i=0;i<len;i++){
            sb2n.append(i==0?"":i);
            sb2n.append(word2n.charAt(i+start));
            helperII(word2n,totallength,i+start+1,sb2n,res);
            sb2n.setLength(size);
        }
    }
    private void stringTest(String s){int a =1;return;}

    public static void main(String[] args){
       char[] cc = new char[]{'a','b'};
       cc[0] ^= cc[1];
       GeneralizedAbbreviation g = new GeneralizedAbbreviation();
       g.stringTest(null);
       List<String> result = g.generateAbbreviations("abc");
       result = g.generateAbbreviations("abcd");
    }
}
