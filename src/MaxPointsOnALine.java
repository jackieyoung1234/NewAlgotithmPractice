import graph.Point;

import java.util.HashMap;

public class MaxPointsOnALine {
    private class Slope{
        int xv;
        int yv;
        String str;
        //x means delta x, y means delta y
        //x==0 && y == 0: overlap is guaranteed not to appear
        public Slope(int x, int y){
            int gcd = getgcd(x,y);
            this.xv = x/gcd;
            this.yv = y/gcd;
            str = new StringBuilder().append(xv).append('.').append(yv).toString();
        }

        @Override
        public int hashCode(){
            return  str.hashCode();
        }
        @Override
        public boolean equals(Object sl2){
            Slope s2 = (Slope) sl2;
            return this.xv==s2.xv && this.yv==s2.yv;
        }
    }
    private static int getgcd(int x, int y){
            if(y==0) return x;
            return getgcd(y,x%y);
    }
    public int maxPoints(Point[] points) {
        int len = points.length;
        if(len<=1) return len;
        int dx = 0,  dy = 0, dup = 0, maxcnt2n = 0, cntthis=0 ;
        Slope cs = null;
        HashMap<Slope, Integer> hm = new HashMap<>();
        int res = 0;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                dx = points[j].x - points[i].x;
                dy = points[j].y - points[i].y;
                if(dx == 0 && dy == 0){
                    dup++;
                    continue;
                }
                cs = new Slope(dx,dy);
                cntthis = hm.getOrDefault(cs,0)+1;
                maxcnt2n = Math.max(maxcnt2n,cntthis);
                hm.put(cs,cntthis);
            }
            res = Math.max(res,maxcnt2n+dup+1);
            maxcnt2n = 0;
            dup = 0;
            hm.clear();
        }
        return res;
    }
    public static void main(String[] args){
       Point p1 = new Point(1,1);
       Point p2 = new Point(2,2);
       Point p3 = new Point(3,3);
       Point[] pa = new Point[]{p1,p2,p3};
       MaxPointsOnALine ma = new MaxPointsOnALine();
       int res = ma.maxPoints(pa);
    }
}
