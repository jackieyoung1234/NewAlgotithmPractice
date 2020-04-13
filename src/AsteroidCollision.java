import java.util.Deque;
import java.util.LinkedList;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        //int[] dq = new int[asteroids.length];
        LinkedList<Integer >dq = new LinkedList<>();
        //int ptr = 0;//next write pos
        boolean flag = true;
        int abs = 0;
        for(int a : asteroids){
            if(a<0){
                abs =-a;
                while(!dq.isEmpty()){
                //while(ptr>0){
                    if(dq.getLast()>abs){
                    //if(dq[ptr-1]>abs){
                        flag = false;
                        break;
                    }else{
                        dq.removeLast();
                        //ptr--;
                    }
                }
            }
            if(flag) dq.addLast(a);
            //if(flag) dq[ptr++]=a;
            else flag = true;
        }
        int[] res = new int[dq.size()];
        abs = 0;
        while(!dq.isEmpty()){
           res[abs++] = dq.removeFirst();
        }
        return res;
        //return Arrays.copyOf(dq,ptr);
    }
    public static void main(String[] args){
       AsteroidCollision a = new AsteroidCollision();
       int[] res = a.asteroidCollision(new int[]{5,10,-5}) ;
    }
}
