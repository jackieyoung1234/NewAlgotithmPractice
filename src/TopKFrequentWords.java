import java.util.*;

public class TopKFrequentWords {
    public class SK implements Comparable<SK>{
        String s;
        int cnt;
        public SK(String s, int cnt){
            this.s = s;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(SK sk2){
            int comp = Integer.compare(this.cnt,sk2.cnt);
            if(comp!=0) return comp;
            return sk2.s.compareTo(this.s);
        }
    }
    public List<String> topKFrequent2(String[] words, int k) {
        HashMap<String, Integer> m = new HashMap<>();
        PriorityQueue<SK> p = new PriorityQueue<>();
        List<String> res = new ArrayList<>(k);
        SK newsk;
        for(String w : words){
            m.put(w,m.getOrDefault(w,0)+1);
        }
        for(Map.Entry<String,Integer> e : m.entrySet()){
            newsk =  new SK(e.getKey(),e.getValue());
            if(p.size()<k){
                p.offer(newsk);
            }else if(newsk.compareTo(p.peek())>0){
                p.poll();
                p.offer(newsk);
            }
        }
        while(k-->0){
            res.add(0,p.poll().s);
        }
        return res;
    }

    int index = 0;
    TNode[] list;
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        TNode root = new TNode();

        list = new TNode[words.length];


        for(String word: words) {
            addWord(root, word);
        }

        mergeSort(list,0, index-1);
       /*/ Comparator<TNode> com=new Comparator<TNode>(){
            @Override
            public int compare(TNode o1,TNode o2){
                if(o1==null)
                    if(o2==null)
                        return 0;
                    else
                        return 1;
                else
                    if(o2==null)
                        return -1;
                    else
                        return o1.frequency==o2.frequency?o1.word.compareTo(o2.word):o2.frequency-o1.frequency;
            }
        };
        Arrays.sort(list,com);/*/
        for(int i = 0; i < k; ++i) {
            result.add(list[i].word);
        }
        return result;
    }

    private void mergeSort(TNode[] list, int m, int n) {
        if(m < n) {
            int mid = m + (n-m)/2;
            mergeSort(list, m, mid);
            mergeSort(list, mid+1, n);
            merging(list,m,mid,n);
        }
    }

    private void merging(TNode[] list, int m, int mid, int n) {
        TNode[] temp = new TNode[n-m+1];

        int i = m;
        int j = mid+1;
        int k = 0;

        while(i <= mid && j <= n) {
            if(list[i].frequency > list[j].frequency) {
                temp[k++] = list[i++];
            }
            else if(list[i].frequency < list[j].frequency) {
                temp[k++] = list[j++];
            }
            else if(compareWords(list[i].word, list[j].word)) {
                temp[k++] = list[i++];
            }
            else {
                temp[k++] = list[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = list[i++];
        }

        while(j <= n) {
            temp[k++] = list[j++];
        }

        k = 0;

        for(i = m; i <= n;) {
            list[i++] = temp[k++];
        }
    }

    protected boolean compareWords(String word1, String word2) {
        for(int i = 0; i < word1.length() && i < word2.length(); ++i) {
            if(word1.charAt(i) < word2.charAt(i))
                return true;
            if(word1.charAt(i) > word2.charAt(i))
                return false;
        }
        return word1.length() < word2.length()?true:false;
    }

    private void addWord(TNode root, String word) {
        for(int i = 0; i < word.length(); ++i) {
            if(null == root.next[word.charAt(i)-'a']) {
                root.next[word.charAt(i)-'a'] = new TNode();
            }

            root = root.next[word.charAt(i)-'a'];
        }

        if(0 == root.frequency) {
            list[index++] = root;
            root.word = word;
        }
        root.frequency++;
    }



    class TNode {
        TNode[] next;
        int frequency;
        String word;

        public TNode() {
            next = new TNode[26];
            frequency = 0;
        }

    }
}
