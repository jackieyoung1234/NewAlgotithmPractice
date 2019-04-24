import java.util.ArrayList;
import java.util.List;

public class pascalTriangle1 {
    public List<List<Integer>> generate(int numRows) {
       //corner case 0??
       List<List<Integer>> result = new ArrayList<>(numRows);
       int[] pre = new int[1];//num = level
       pre[0] = 1;
       for(int level=1;level<=numRows;level++){
            //level 1 - n
            int[] now = new int[level];//num = level;
            now[0]=1;
            int i=0;
            List<Integer> unit = new ArrayList<>(level);
            while(i<level-1){
            //{i = 0.....level
                now[i] = pre[i] + i==0?0:pre[i-1];
                unit.add(now[i++]);
            }
            unit.add(1);//last 1
            pre = now;
            result.add(unit);
       }
       return result;
    }
    public static void main(String[] args){
        List<List<Integer>> result = new pascalTriangle1().generate(5);
    }
}
