import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostToHireWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        //sort the worker array based on ratio (wage / quality)
        double res = Double.MAX_VALUE;
        int sumq = 0;
        int len = quality.length;
        Worker[] wa = new Worker[len];
        for(int i=0;i<len;i++){
            wa[i] = new Worker(wage[i],quality[i]);
        }
        Arrays.sort(wa);
        PriorityQueue<Integer> maxq = new PriorityQueue<>(
                    (Integer i1, Integer i2)->{
                        return Integer.compare(i2,i1);
                    }
        );
        for(Worker w : wa){
            maxq.offer(w.quality);
            sumq+=w.quality;
            if(maxq.size()>K){
                sumq -= maxq.poll();
            }

            if(maxq.size()==K){
                res = Math.min(res, w.ratio * sumq);
            }
        }
        return res;
    }
    private class Worker implements Comparable<Worker>{
        int wage;
        int quality;
        double ratio;
        public Worker(int w, int q){
            this.wage = w;
            this.quality = q;
            this.ratio = w/(double)q;
        }
        @Override
        public int compareTo(Worker w2){
            return Double.compare(this.ratio, w2.ratio);
        }

    }

    public static void main(String[] args){
        double[][] dd = new double[][]{{1.0,2.0},{2.0,3.0}};
        Arrays.sort(dd, (d1,d2)->Double.compare(d1[0],d2[0]));
        int i = (int)98.0;
        System.out.print((char)i);
    }
}

