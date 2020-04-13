import java.util.ArrayList;
import java.util.List;

public class LogSystem {
    private static final char[] INDEX2THISTYPE = new char[]{
            'R',
            'Y',
            'M',
            'D',
            'H',
            'I',
            'S'
    };
    private static final int[] ENDINGDATEINDEX= new int[]{
            11,30,23,59,59
    };
    public class Node{
        Node[] next;
        ArrayList<Integer> idList;
        int[] curdate;
        boolean dateIni;
        private void build(char c){
            if(c=='R'){
                next = new Node[18];
                curdate = new int[0];
            }
            else if(c=='Y'){
                next = new Node[12];
                curdate = new int[1];
            }
            else if(c=='M'){
                next = new Node[31];
                curdate = new int[2];
            }
            else if(c=='D'){
                next = new Node[24];
                curdate = new int[3];
            }
            else if(c=='H'){
                next = new Node[60];
                curdate = new int[4];
            }
            else if(c=='I'){
                next = new Node[60];
                curdate = new int[5];
            }else if(c=='S'){
                curdate = new int[60];
            }
            this.idList = new ArrayList<>();
            this.dateIni = false;
        }
        public Node(char c){
            build(c);
        }
        public void add(int id){
            this.idList.add(id);
        }
    }
    Node root;
    public LogSystem() {
        root = new Node('R');
    }

    public void put(int id, String timestamp) {
        root = put(id,
                dateString2array(timestamp),
                0,
                root,
                null,
                5
        );
    }
    private String[] dateString2array(String timestamp){
        return new String[]{timestamp.substring(0,4),
                timestamp.substring(5,7),
                timestamp.substring(8,10),
                timestamp.substring(11,13),
                timestamp.substring(14,16),
                timestamp.substring(17),
        };
    }

    private int[] dateString2intarray(String timestamp){
        return new int[]{
                date2index(timestamp.substring(0,4),0),
                date2index(timestamp.substring(5,7),1),
                date2index(timestamp.substring(8,10),2),
                date2index(timestamp.substring(11,13),3),
                date2index(timestamp.substring(14,16),4),
                date2index(timestamp.substring(17),5)
        };
    }
    private Node put(int id, String[] info, int index, Node cur, Node parent, int thisNodeIndex){
        if(cur==null) cur = new Node(INDEX2THISTYPE[index]);
        cur.add(id);
        if(parent!=null&&!cur.dateIni){
            int i = 0;
            for(;i<parent.curdate.length;){
                cur.curdate[i] = parent.curdate[i++];
            }
            cur.curdate[i] = thisNodeIndex;
            cur.dateIni = true;
        }
        if(index<=5){
            int nextindex = date2index(info[index],index);
            cur.next[nextindex] = put(id,info,index+1,cur.next[nextindex],cur,nextindex);
        }
        return cur;
    }
    private int date2index(String info, int index ){
        int arrayi = Integer.parseInt(info);
        if(index==0) arrayi -= 2000;
        else if(index <= 2) arrayi -=1;
        return arrayi;

    }
    private boolean dateSmallerOrEqual(int[] sarray, int[]earray,int si, int ei,boolean equalCount) {
        if(sarray[si]<earray[si]) return true;
        if(sarray[si]==earray[si]) {
            if(si==ei) return equalCount;
            return dateSmallerOrEqual(sarray, earray, si + 1, ei,equalCount);
        }
        return false;
    }
    private boolean dateEqual(int[] sarray, int[]earray,int si, int ei) {
        if(sarray[si]==earray[si]) {
            if(si == ei) return true;
            return dateEqual(sarray,earray,si+1,ei);
        }
        return false;
    }
    public List<Integer> retrieve(String s, String e, String gra) {
        int[] sarray = dateString2intarray(s);
        int[] earray = dateString2intarray(e);
        if(!dateSmallerOrEqual(sarray,earray,0,5,true)) return new ArrayList<Integer>();
        return retrieve(sarray,earray,gra2index(gra),root,0);
    }
    private List<Integer> retrieve(int[] s, int[] e, int index, Node cur, int curdepth){

        List<Integer> res = new ArrayList<>();
        //?? boundry check && cur null check && curdepth > index
        if(cur==null) return res;
        int[] boundry;
        if(curdepth==index+1){
            if(contains(s,e,cur,curdepth-1,index)){
                return cur.idList;
            }else{
                return res;
            }
        }else if((boundry = intersect(s,e,cur,curdepth-1,index))!=null){
            for(int i=boundry[0];i<=boundry[1];i++){
                res.addAll(retrieve(
                        s,e,index,cur.next[i],curdepth+1
                ));
            }
        }
        return res;
    }

    //todo : optimization
    //not using curdate in the node
    private boolean contains(int[] s, int[] e, Node cur,int curdepthm1,int endingindex){
        if(curdepthm1==-1){//root
            return false;
        }
        int[] nodedate = cur.curdate;
        //if(curdepthm1==endingindex&&s[curdepthm1]<=nodedate&&e[curdepthm1]>=nodedate) return true;
        if(dateSmallerOrEqual(s,nodedate,0,curdepthm1,true)&&dateSmallerOrEqual(nodedate,e,0,curdepthm1,true)) return true;
        return false;
    }
    private int[] intersect(int[] s, int[] e, Node cur,int curdepthm1,int endingindex){
        if(curdepthm1==-1){
            return new int[]{Math.max(0,s[0]),Math.min(17,e[0])};
        }
        int[] nodedate = cur.curdate;//get(curdepthm1);
        //if(e[curdepthm1]>nodedate.get(curdepthm1)&&s[curdepthm1]==nodedate.get(curdepthm1)) return new int[]{s[curdepthm1+1],ENDINGDATEINDEX[curdepthm1]};
        if(dateSmallerOrEqual(nodedate,e,0,curdepthm1,false)){
            if(dateEqual(nodedate,s,0,curdepthm1)) return new int[]{s[curdepthm1+1],ENDINGDATEINDEX[curdepthm1]};
            //if(e[curdepthm1]>nodedate.get(curdepthm1)&&s[curdepthm1]<nodedate.get(curdepthm1)) return new int[]{0,ENDINGDATEINDEX[curdepthm1]};
            if(dateSmallerOrEqual(s,nodedate,0,curdepthm1,false)) return new int[]{0,ENDINGDATEINDEX[curdepthm1]};
        }
        //if(e[curdepthm1]==nodedate.get(curdepthm1)) return new int[]{s[curdepthm1+1],e[curdepthm1+1]};
        if(dateEqual(nodedate,e,0,curdepthm1)){
            if(dateSmallerOrEqual(s,nodedate,0,curdepthm1,false))return new int[]{0,e[curdepthm1+1]};
            else return new int[]{s[curdepthm1+1],e[curdepthm1+1]};
        }
        return null;
    }
    private int gra2index(String gra){
        int index = 5;
        if(gra.equals("Year")) index =0;
        else if(gra.equals("Month")) index =1;
        else if(gra.equals("Day")) index =2;
        else if(gra.equals("Hour")) index =3;
        else if(gra.equals("Minute")) index =4;
        return index;
    }
    public static void main(String[] args){
        LogSystem l = new LogSystem();
        String[] commands = new String[]{
                "put","put","retrieve","put","put","put","put","retrieve","put","put","put","put","put","put","put","retrieve","put","put","retrieve","put","put","put","put","put","retrieve","retrieve","put","retrieve","put","put","put","put","put",
                "put","put","retrieve","put","put","put","put","retrieve","put","put","retrieve","retrieve","put","put","retrieve","put","retrieve","put","put","put","put","retrieve","put","put","put","retrieve","put","put","put","put","put","retrieve","put",
                "retrieve","retrieve","put","put","put","put","retrieve","put","put","retrieve","put","retrieve",
                "retrieve","put","put","retrieve","retrieve","put","put","put","put","put",
                "retrieve","put","retrieve","put","retrieve","retrieve","put","put","retrieve","retrieve","retrieve","retrieve","put","put","put","put","put","put","put","put",
                "put","put","put","put","put","put","put","put","put","retrieve","put","put","put","put","retrieve","put","retrieve","retrieve","retrieve","put",
                "put","put","retrieve","retrieve","put","put","put","put","put","put","put","retrieve","put","put","put","put","put","put","put","put","put","put","put","put","put","put","put","retrieve","retrieve",
                "retrieve","put","put","retrieve","put","retrieve","put","retrieve","retrieve","put","retrieve","put","put","put","retrieve","put","put","put","put","retrieve","put","put","retrieve","put","put","put","put","put","retrieve","retrieve",
                "put","put","put","put","put","retrieve","put","put","put","retrieve","put","put","put","put","put","put","put","put","put","put","put","put","retrieve","put","retrieve","put","put","retrieve","retrieve","put",
                "retrieve","put","retrieve","put","put","put","put","retrieve","put","put","put","put","put","retrieve","put","put","put","retrieve","put","retrieve","put","retrieve","put","retrieve","retrieve","retrieve","put","retrieve","put","put",
                "put","retrieve","put","retrieve","put","retrieve","put","put","put","put","put","retrieve","retrieve","put","put","retrieve","retrieve","put","put","put","retrieve","put","put","put","put","put","put","put","put","retrieve","retrieve","put","put","put","put",
                "put","put","put","put","put","put","put","retrieve","put","put","put","put","put","put","put","put","retrieve","retrieve","put"
        };
        String[][] data = new String[][]{
                {"1","2005:01:05:22:16:15"},{"2","2003:12:12:20:30:51"},{"2005:07:10:17:43:43","2007:02:18:10:22:52","Month"},{"3","2001:06:25:23:51:23"},{"4","2004:10:25:13:49:48"},{"5","2002:05:03:14:21:45"},{"6","2004:10:04:21:49:49"},{"2003:05:18:16:45:48","2007:12:05:10:36:51","Hour"},{"7","2006:05:14:18:30:30"},
                {"8","2003:04:02:22:12:41"},{"9","2002:02:25:13:12:24"},{"10","2005:01:17:23:36:39"},{"11","2000:07:25:12:45:16"},{"12","2001:08:12:16:35:55"},{"13","2000:10:18:18:46:38"},{"2004:09:23:19:31:46","2005:10:27:16:51:21","Year"},{"14","2000:08:07:16:15:34"},{"15","2002:10:08:18:39:59"},{"2004:01:15:21:26:15","2006:01:12:14:47:11","Second"},
                {"16","2001:12:13:15:17:44"},{"17","2001:02:28:23:45:56"},{"18","2007:10:08:23:17:56"},{"19","2003:08:01:16:32:13"},{"20","2000:03:08:13:35:49"},{"2005:04:05:13:14:45","2006:10:20:23:49:10","Year"},{"2001:04:23:10:47:46","2003:07:26:21:53:49","Second"},{"21","2006:04:23:20:47:30"},{"2003:03:28:18:45:21","2005:09:12:17:33:45","Month"},
                {"22","2007:07:19:12:30:40"},{"23","2004:10:22:16:24:52"},{"24","2004:08:08:14:17:24"},{"25","2001:02:28:12:37:43"}};
        for(int i=0;i<data.length;i++){
           if(commands[i].indexOf("put")!=-1){
                l.put(Integer.parseInt(data[i][0]),data[i][1]);
           }else{
               l.retrieve(data[i][0],data[i][1],data[i][2]);
           }
        }
    };

}

