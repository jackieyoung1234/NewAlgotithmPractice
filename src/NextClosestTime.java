import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NextClosestTime {
    public String nextClosestTime(String time) {
        char[] cs = new char[]{
                time.charAt(0),
                time.charAt(1),
                time.charAt(3),
                time.charAt(4),
        };
        char[] sorted = Arrays.copyOfRange(cs,0,4);
        Arrays.sort(sorted);
        int i=3;
        for(;i>=0;i--){
            //H1H0:M1M0
            //xx:x!
            //!>M0  return H1H0:M1!
            if(i==3){
                int firstLarger = ffli(sorted,cs[3],0,3);
                if(firstLarger<4){
                    cs[3] = sorted[firstLarger];
                    break;
                }
                //xx:!?    !>M1 && !<6,replace M0 with [0]
                //return xx:![0]
            }else if(i==2) {
                int firstLarger = ffli(sorted,cs[2],0,3);
                if(firstLarger<4&&sorted[firstLarger]<6){
                    cs[2] = sorted[firstLarger];
                    cs[3] = sorted[0];
                    break;
                }
                //?!:&&
                //a.?==1 or 0,
                //if !>H0,   return H1!:[0][0]
                //b.?==2
                //if !>H0 && !<=4, return H1!:[0][0]
            }else if(i==1){
                int firstLarger = ffli(sorted,cs[1],0,3);
                if(cs[0]<'2'){
                    if(firstLarger<4){
                        cs[1] = sorted[firstLarger];
                        cs[2] = sorted[0];
                        cs[3] = sorted[0];
                        break;
                    }
                }else{//=='2'
                    if(firstLarger<4&&sorted[firstLarger]<='4'){
                        cs[1] = sorted[firstLarger];
                        cs[2] = sorted[0];
                        cs[3] = sorted[0];
                        break;
                    }
                }

                //!x:xx
                // if !>H1 && !<=2, return ![0]:[0][0]
            }else if(i==0){
                int firstLarger = ffli(sorted,cs[0],0,3);
                if(firstLarger<4&&sorted[firstLarger]<='2'){
                    cs[0] = sorted[firstLarger];
                    cs[1] = sorted[0];
                    cs[2] = sorted[0];
                    cs[3] = sorted[0];
                    break;

                }
            }
        }
        //sort H1H0 M1M0 to [4]
        //else return the ! smallest of [4] which is <=2
        if(i<0){
            cs[0] = sorted[0];
            cs[1] = sorted[0];
            cs[2] = sorted[0];
            cs[3] = sorted[0];

        }
        return String.valueOf(new char[]{cs[0],cs[1],':',cs[2],cs[3]});
    }
    /**
     * find the index fo the first element in a sorted array whose value is > key
     */
    private int ffli(char[] s, char k, int st, int end){
        int e = end+1;
        int mid = 0,comp=0;
        while(st<e){
            mid = st + (e-st)/2;
            comp = Character.compare(s[mid],k);
            if(comp<=0) st = mid+1;
            else e = mid;
        }
        return st;
    }
    private static final int MIN_PER_DAY = 24*60;
    public String nextClosestTime2(String time) {
        String[] timeinfo =  time.split("\\:",0);
        int hour =  Integer.parseInt(timeinfo[0]);
        int min  =  Integer.parseInt(timeinfo[1]);
        int target = 60*hour+min;
        HashSet<Integer> avai = new HashSet();
        avai.add(hour/10);
        avai.add(hour%10);
        avai.add(min/10);
        avai.add(min%10);
        StringBuilder nct = new StringBuilder(time);
        helper(new int[]{MIN_PER_DAY,target}, nct, new StringBuilder(), 0, 0, 0, avai);
        return nct.toString();
    }
    //info 0:closestdiff2n 1: target time
    private void helper(int[] info,StringBuilder nct, StringBuilder s2n, int t2n ,int index, int pre,HashSet<Integer> avai){
        switch(index){
            case 0:
                for(int n : avai){
                    if(n<3){
                        s2n.append(n);
                        helper(info,nct,s2n,t2n,1,n,avai);
                        s2n.deleteCharAt(0);
                    }
                }
                break;
            case 1:
                for(int n : avai){
                    //no 24:xx, should be 00:xx
                    if(pre<=1||n<4){
                        s2n.append(n);
                        s2n.append(':');
                        helper(info,nct,s2n,10*pre+n,3,0,avai);
                        s2n.delete(1,3);
                    }
                }
                break;
            case 3:
                for(int n : avai){
                    if(n<6){
                        s2n.append(n);
                        helper(info,nct,s2n,t2n,4,n,avai);
                        s2n.deleteCharAt(3);
                    }
                }
                break;
            case 4:
                for(int n : avai){
                    s2n.append(n);
                    helper(info,nct,s2n,t2n*60+pre*10+n,5,0,avai);
                    s2n.deleteCharAt(4);
                }
                break;
            case 5:
                if(less(t2n,info)){
                    nct.replace(0,5,s2n.toString());
                }
                break;
        }
    }
    private boolean less(int time, int[] info){// closet2n,  int target){
        int cadidiff = time==info[1]?MIN_PER_DAY:(time<info[1])?(MIN_PER_DAY-info[1]+time):(time-info[1]);
        if(cadidiff<info[0]){
            info[0] = cadidiff;
            return true;
        }
        return false;
    }
    private void test(StringBuilder l){
       l = new StringBuilder("1");
    }
    public static void main(String[] args){
       NextClosestTime nct = new NextClosestTime();
       StringBuilder l = new StringBuilder("0");
       nct.test(l);
       String res = nct.nextClosestTime("19:34");
       res = nct.nextClosestTime("00:00");
        res = nct.nextClosestTime("13:55");
       res = nct.nextClosestTime("23:59");





       String[] info = "01:24".split("\\:",0);
       int hour = Integer.parseInt(info[0]);

       int hh = 4;
       double test = hh/Math.pow(8,2);
    }
}
