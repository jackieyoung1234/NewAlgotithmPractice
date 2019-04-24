
import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagramsSlowest_and_FirstVersion(String[] strs) {
        ArrayList<String> backUp = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        for(String iteratingString : strs){
            char[] temp = iteratingString.toCharArray();
            Arrays.sort(temp);
            String sortS = String.valueOf(temp);
            int id = backUp.indexOf(sortS);
            if(id == -1){
                backUp.add(sortS);
                ArrayList<String> newAL = new ArrayList<>();
                newAL.add(iteratingString);
                res.add(newAL);
            }else{
                ArrayList<String> newAL = (ArrayList<String>) res.remove(id);
                newAL.add(iteratingString);
                res.add(id,newAL) ;
            }
        }
        return res;
    };
    public List<List<String>> groupAnagrams_sortedStringKey(String[] strs) {
        Map<String,List<String>> backUp = new HashMap<>();
        for(String temp : strs){
            char[] cArr = temp.toCharArray();
            Arrays.sort(cArr);
            String sortedS = String.valueOf(cArr);
            if(!backUp.containsKey(sortedS))
                backUp.put(sortedS,new ArrayList<String>());
            backUp.get(sortedS).add(temp);
        }
        return new ArrayList(backUp.values());
    };
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> backUp = new HashMap<>();
        for(String tt : strs){
            int[] cntKey = new int[26];
            for(char tc : tt.toCharArray()){
                cntKey[tc-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int k:cntKey)
                sb.append(k);
            String key = sb.toString();
            //todo: difference between strign from stringbuilder and valueOf, why hash is different
            //String key = String.valueOf(cntKey);
            if(!backUp.containsKey(key))
                backUp.put(key,new Stack<>());
            backUp.get(key).add(tt);
        }
        return new ArrayList(backUp.values());
    }
    public static void main(String[] args){
       List<List<String>> res = new GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
}
