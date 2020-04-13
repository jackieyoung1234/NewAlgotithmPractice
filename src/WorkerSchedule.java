public class WorkerSchedule{
    public int schedule(int[] tasks){
        if(tasks.length==0) return 0;
        int[] sum = new int[4];
        sum[3] = Integer.MAX_VALUE;
        backTrack(sum,tasks,0);
        return sum[3];
    }
    private void backTrack(int[] sum, int[] a, int i){
        if(i==a.length){
           sum[3] = Math.min(sum[3],Math.max(sum[0],
                   Math.max(sum[1],sum[2])));
           return;
        }
        sum[0]+=a[i];
        if(sum[0]<sum[3]){
            backTrack(sum,a,i+1);
        }
        sum[0]-=a[i];
        sum[1]+=a[i];
        if(sum[1]<sum[3]){
            backTrack(sum,a,i+1);
        }
        sum[1]-=a[i];
        sum[2]+=a[i];
        if(sum[2]<sum[3]){
            backTrack(sum,a,i+1);
        }
        sum[2]-=a[i];
    }
    public static void main(String[] args){
        WorkerSchedule ws = new WorkerSchedule();
        int res = ws.schedule(
                new int[]{5,15,20,15,30}
        );
    }
}