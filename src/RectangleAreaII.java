import java.util.ArrayList;

public class RectangleAreaII {
    public int rectangleArea(int[][] rectangles) {
        ArrayList<int[]> st = new ArrayList<>();
        ArrayList<int[]> next = new ArrayList<>();
        for(int[] a : rectangles) st.add(a);
        boolean pos = true;
        long res = 0;
        int[] cross;
        while(!st.isEmpty()){
            if(pos){
                for(int i=0;i<st.size();i++){
                    res += area(st.get(i));
                    for(int j=i+1;j<st.size();j++){
                        cross = cc(st,i,j);
                        if(cross!=null) next.add(cross);
                    }
                }
            }else{
                for(int i=0;i<st.size();i++){
                    res -= area(st.get(i));
                    for(int j=i+1;j<st.size();j++){
                        cross = cc(st,i,j);
                        if(cross!=null) next.add(cross);
                    }
                }
            }
            pos = !pos;
            st = next;
            next = new ArrayList<>();
        }
        return mod(res);
    }
    private int mod(long res){
        res = (res%1000000007l);
        if(res<0) res+=1000000007l;
        return (int)res;
    }
    private int[] cc(ArrayList<int[]> al, int i, int j){
        int[] ai = al.get(i);
        int[] aj = al.get(j);
        int lbx =  Math.max(ai[0],aj[0]);
        int rtx =  Math.min(ai[2],aj[2]);
        if(rtx<=lbx) return null;
        int lby =  Math.max(ai[1],aj[1]);
        int rty =  Math.min(ai[3],aj[3]);
        if(rty<=lby) return null;
        return new int[]{lbx,lby,rtx,rty};
    }
    private int area(int[] a){
        return (a[2]-a[0])*(a[3]-a[1]);
    }
    public static void main(String[] args){
        int res = new RectangleAreaII().rectangleArea(new int[][]{
                {0,0,2,2},
                {1,0,2,3},
                {1,0,3,1}
        });

    }
}
