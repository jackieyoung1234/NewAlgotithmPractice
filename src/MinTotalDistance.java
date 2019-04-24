import java.util.LinkedList;
import java.util.List;

public class MinTotalDistance {
    public int minTotalDistance(int[][] grid) {
        //?? edge case x len ==0, minDis=??
        int xlen = grid.length;
        int ylen = grid[0].length;
        int num=0,xsum=0,ysum=0;
        List<int[]> location = new LinkedList<>();
        for(int i=0;i<xlen;i++){
            for(int j=0;j<ylen;j++){
                if(grid[i][j]==1){
                    xsum+=i;
                    ysum+=j;
                    num++;
                    location.add(new int[]{i,j});
                }
            }
        }
        int meetx=xsum/num;
        int  meetx2=0;
        if(xsum%num!=0) meetx2=meetx+1;
        int meety=ysum/num;
        int  meety2=0;
        if(ysum%num!=0) meety2=meety+1;
        int xtotal1=0,xtotal2=0,ytotal1=0,ytotal2=0;
        for(int[] l : location){
            xtotal1 +=Math.abs(l[0]-meetx);
            ytotal1 +=Math.abs(l[1]-meety);
            if(meetx2!=0)
                xtotal2 +=Math.abs(l[0]-meetx2);
            if(meety2!=0)
                ytotal2 +=Math.abs(l[1]-meety2);
        }
        if(meetx2!=0)
            xtotal1 = Math.min(xtotal1,xtotal2);
        if(meety2!=0)
            ytotal1 = Math.min(ytotal1,ytotal2);
        return xtotal1+ytotal1;
    }
    public static void main(String[] args){
        MinTotalDistance mt = new MinTotalDistance();
        int res = mt.minTotalDistance(
                new int[][]{
                        {1,0,0,0,1},
                        {0,0,0,0,0},
                        {0,0,1,0,0}
                }
        );
        res = mt.minTotalDistance(
                new int[][]{
                        {1,0,0,0,1},
                        {0,0,0,0,0},
                        {0,0,0,1,0}
                }
        );
        res = mt.minTotalDistance(
                new int[][]{
                        {0,1},
                        {0,1},
                        {0,1},
                        {1,1},
                        {0,0},
                        {0,1},
                        {0,0},
                        {0,0},
                        {0,0},
                        {0,0},
                        {1,0},
                        {1,0},
                        {0,0},
                        {0,0},
                        {1,1},
                        {0,0}
                }
        );
    }
}
