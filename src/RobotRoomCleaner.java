import Interval.Position;

import java.util.HashSet;

public class RobotRoomCleaner {

   public static void main(String[] args){
      Position ii = new Position(0,1);
      HashSet<Position> hh = new HashSet<>();
      hh.add(ii);
      ii = new Position(0,1);
      boolean res =hh.contains(ii);
      //int[] ii = new int[]{0,1};
      //HashSet<int[]> hh = new HashSet<>();
      //hh.add(ii);
      //ii = new int[]{0,1};
      //boolean res =hh.contains(ii);
      //res = ii == new int[]{0,1};
      //res = ii.equals(new int[]{0,1});
      //res = ii.hashCode() == new int[]{0,1}.hashCode();
      //int iihas = ii.hashCode();
      //int iiihas = new int[]{0,1}.hashCode();
      //String ss = ""+1+0;
      //int sshas = ss.hashCode();
      //int ssshas = "10".hashCode();
   }
}
