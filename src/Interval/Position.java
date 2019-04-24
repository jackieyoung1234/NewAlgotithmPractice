package Interval;

public class Position {
       int x;
       int y;
       String repre;
       public Position(int x, int y){
          this.x = x;
          this.y = y;
          repre = ""+x+y;
       }
       @Override
       public boolean equals(Object pos2){
           Position p2 = (Position) pos2;
           return this.x == p2.x && this.y == p2.y;
       }
       @Override
       public int hashCode(){
           return repre.hashCode();
       }
}
