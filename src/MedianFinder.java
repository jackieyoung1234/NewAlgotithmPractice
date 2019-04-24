import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    int sizel;
    int sizer;
    double me;
    /** initialize your data structure here. */
    public MedianFinder() {
        right = new PriorityQueue<>();
        left = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return i2-i1;
            }
        } );
        sizel = 0;
        sizer = 0;
        me = 0.0;
    }

    public void addNum(int num) {
        if(num>me){
            right.offer(num);
            sizer++;
        }
        else{
            left.offer(num);
            sizel++;
        }
        int diff = sizer-sizel;
        if(diff<-1) {
            //poll from l,add to r
            right.offer(left.poll());
            sizer++;
            sizel--;
        }else if(diff>1){
            left.offer(right.poll());
            sizel++;
            sizer--;
        }
        if(diff==1){
            me = right.peek();
        }else if(diff==-1){
            me = left.peek();
        }else{
            me = (right.peek()+left.peek())/(double)2;
        }
    }

    public double findMedian() {
        return me;
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
