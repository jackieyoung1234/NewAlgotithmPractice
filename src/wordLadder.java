import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class wordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> list = new HashSet<>();
        list.addAll(wordList);
        if(!list.remove(endWord)) return 0;
        HashSet<String> st = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> tmp;
        st.add(beginWord);
        end.add(endWord);
        int step=1;
        char c;
        char[] ca;
        String nei;
        while(!st.isEmpty()&&!end.isEmpty()){
            if(st.size()> end.size()){
                HashSet<String> temp = st;
                st = end;
                end = temp;
            }
            tmp = new HashSet<>();
            for(String cur : st){
                ca = cur.toCharArray();
                for(int i=0;i<ca.length;i++){
                    c = ca[i];
                    for(char r = 'a';r<='z';r++){
                        if(r==c) continue;
                        ca[i] = r;
                        nei = String.valueOf(ca);
                        if(endWord.contains(nei))
                            return step+1;
                        else if(list.remove(nei)){
                            tmp.add(nei);
                        }
                    }
                    ca[i] = c;
                }

            }
            st = tmp;
            step++;
        }
        return 0;
    }
    public static void main(String[] args){
       List<String> dict = new ArrayList<>();
       dict.clear();
       dict.add("hot");
        dict.add("cog");
        dict.add("dog");
        dict.add("tot");
        dict.add("hog");
        dict.add("hop");
        dict.add("pot");
        dict.add("dot");
        int res = new wordLadder().ladderLength("hot","dog",dict);
    }
}
