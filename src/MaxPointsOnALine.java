import graph.Point;

import java.util.HashMap;

public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        int len = points.length;
        if(len<=2) return len;
        int res=0;
        double slope;
        for(int i=0;i<len;i++){
            HashMap<Double,Integer> scnt = new HashMap<>();
            int vertical = 0, dup = 1,curres=0;
            for(int j=i+1;j<len;j++){
                int dx = points[j][0]-points[i][0];
                int dy = points[j][1]-points[i][1];
                if(dx==0&&dy==0) {
                    dup++;
                    continue;
                }else if(dx==0){
                    vertical++;
                    continue;
                }else{
                    int gcd = gcd(dx,dy);
                    dy/=gcd;
                    dx/=gcd;
                    slope = dy/(double)dx;
                    int cnt = scnt.getOrDefault(slope,0)+1;
                    scnt.put(slope,cnt);
                    curres = Math.max(cnt,curres);
                }
            }
            res = Math.max(res,curres+dup);
            res = Math.max(res,vertical+dup);
        }
        return res;
    }
    private int gcd(int dx, int dy){
        if(dy==0) return dx;
        return gcd(dy,dx%dy);
    }
    public static void main(String[] args){
       MaxPointsOnALine ma = new MaxPointsOnALine();
       int res = ma.maxPoints(new int[][]{
               {2,3},
               {3,3},
               {-5,3}
       });
    }
}
