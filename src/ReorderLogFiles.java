import java.util.Arrays;

class LetterLog implements Comparable<LetterLog>{
    private int stindex;// st index of letter after identifier
    private int i;//index in the original string
    private boolean isDigit;
    String l;
    private int getStIndex(String s){
        int i = 0;
        for(;i<s.length();i++){
            if(s.charAt(i)==' ')
                break;
        }
        return i+1;
    }
    public LetterLog(String s, int i){
        stindex = getStIndex(s);
        l = s;
        i = i;
        isDigit = Character.isDigit(s.charAt(stindex));
    }

    @Override
    public int compareTo(LetterLog l2){
        if(this.isDigit() && l2.isDigit()){
            return Integer.compare(i,l2.getIndex());
        }else if(this.isDigit()){
            return 1;
        }else if(l2.isDigit()){
            return -1;
        }
        int res = this.l.substring(stindex).compareTo(l2.toLogs().substring(l2.getStIndex()));
        return res==0?this.l.substring(0,stindex-1).compareTo(l2.toLogs().substring(0,l2.getStIndex()-1)):res;
    }
    public String toLogs(){
        return l;
    }
    public int getStIndex(){
        return stindex;
    }
    public int getIndex(){
        return i;
    }
    public boolean isDigit(){
        return this.isDigit;
    }

}
class Solution {


    public String[] reorderLogFiles(String[] logs) {
        int len = logs.length;
        if(len<=1) return logs;
        LetterLog[] la = new LetterLog[len];
        for(int i=0;i<len;i++){
            la[i] = new LetterLog(logs[i],i);
        }
        Arrays.sort(la);
        for(int i=0;i<len;i++){
            logs[i] = la[i].toLogs();
        }
        return logs;
    }
}
