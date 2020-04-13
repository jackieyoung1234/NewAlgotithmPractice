import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        return null;
        ////todo?? use two way bfs, when reverse direction, switch addLast and addFirst
        //Set<String> available = new HashSet<>(wordList);
        //if(!available.remove(endWord)) return new LinkedList<List<String>>();
        //Queue<String> q = new LinkedList<>();
        //q.offer(endWord);
        //Map<String, List<List<String>>> parent = new HashMap<>();
        //LinkedList<List<String>> endwordP = new LinkedList<>();
        //endwordP.add(Arrays.asList(new String[]{endWord}));
        //HashMap<String, Integer> levelmap = new HashMap<>();
        //levelmap.put(endWord,0);
        //parent.put(endWord,endwordP);
        //Set<String> visited = new HashSet<>();
        //String cur = null, next = null;
        //char[] curc;
        //char cc = '\u0000';
        //List<List<String>> newPathList;
        //LinkedList<String> newPath;// = null;
        //int size =0,depth=1;
        //while(!q.isEmpty()){
        //    size = q.size();
        //    while(size-->0){
        //        cur = q.poll();
        //        if(cur.equals(beginWord)) break;
        //        visited.add(cur);
        //        curc = cur.toCharArray();
        //        for(int i=0;i<curc.length;i++){
        //            cc = curc[i];
        //            for(char c='a';c<='z';c++){
        //                if(c==curc[i]) continue;
        //                curc[i]=c;
        //                next = new String(curc);
        //                if(!visited.contains(next)&&available.contains(next)&&levelmap.getOrDefault(next,depth)!=depth){
        //                    levelmap.put(next,depth);
        //                    q.offer(next);
        //                    //replace with computeIfabsent
        //                    //parent.computeIfAbsent(next, list->new LinkedList<LinkedList<String>>());
        //                    if(!parent.containsKey(next)) parent.put(next,new LinkedList<List<String>>());
        //                    newPathList = parent.get(next);
        //                    if(newPathList.)
        //                    for(List<String> path : parent.get(cur)){
        //                        newPath = new LinkedList<String>(path);
        //                        newPath.addFirst(next);
        //                        newPathList.add(newPath);
        //                    }
        //                }
        //                curc[i] = cc;
        //            }
        //        }
        //    }
        //    depth++;
        //}
        ////return  parent.computeIfAbsent(beginWord,new LinkedList<>());
        ////return parent.getOrDefault(beginWord, new LinkedList<>());
        //if(parent.containsKey(beginWord)) return parent.get(beginWord);
        //return new LinkedList<List<String>>();
    }
    public static void main(String[] args){
        WordLadderII w = new WordLadderII();
        List<List<String>> res = w.findLadders("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog"));
    }
}
