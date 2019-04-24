public class StringST {
    private static final int R = 256;

    private class Node {
        private Node[] next = new Node[R];
        private int size = 0;
        private Node N1;
        private boolean flag = false;
    }

    private class helper {
        private int size;
        private Node root;

        public helper(String[] sArr) {
            size = 0;
            root = new Node();
            for (String temp : sArr) {
                if (temp == "") {
                    root.size = 0;
                    break;
                }
                put(temp);
            }
        }

        private Node put(Node n, String k, int i) {
            if (i > k.length() - 1 || n.flag == true)
                return n;
            int temp = k.charAt(i);
            if (n.next[temp] == null) {
                n.size++;
                n.next[temp] = new Node();
                n.N1 = n.next[temp];
            }
            if (n.size == 2) {
                n.N1 = null;
                n.flag = true;
                return n;
            }
            n.next[temp] = put(n.next[temp], k, i + 1);
            return n;
        }

        public void put(String k) {
            root = put(root, k, 0);
        }
    }
    public String longestCommonPrefix2(String[] strs){
        if(strs.length == 0)
            return "";
        helper myH = new helper(strs);
        Node node = myH.root;
        int length = 0;
        while(node.size == 1){
           length++;
           node = node.N1;
        }
        return strs[0].substring(0,length);
    }
    //vertical scan
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        //StringBuilder prefix = new StringBuilder();
        String prefix = "";
        for(int i = 0; i < strs[0].length(); i ++){
            //String temp = prefix.append(strs[0].charAt(i)).toString();
            String temp = prefix + strs[0].charAt(i);
            for(int j = 1; j < strs.length; j ++){
               if(strs[j].indexOf(temp) != 0)
                   return prefix;
            }
            prefix = temp;
        }
        return prefix;
    }
    public static void main(String[] args){
        StringST st = new StringST();
       String result =  st.longestCommonPrefix(new String[]{});
       result = st.longestCommonPrefix(new String[]{"a"});
       result = st.longestCommonPrefix(new String[]{"a","a"});
        result = st.longestCommonPrefix(new String[]{"a","b"});
       result = st.longestCommonPrefix(new String[]{"","b"});
       result = st.longestCommonPrefix(new String[]{"","cbb",""});
       result = null;
    }

}
