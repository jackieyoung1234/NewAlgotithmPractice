public class FindRedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
        int[] pa = new int[len+1];
        for(int i=1;i<len+1;i++){
            pa[i]=i;
        }
        int i = 0;
        for(;i<len;i++){
            int pa1 = findPa(edges[i][0],pa);
            int pa2 = findPa(edges[i][1],pa);
            //check  if v1 v2 have same parent
            if(pa1!=pa2){
                //recursively set all pa's route node to pa1
                set(edges[i][1],pa1,pa);
            }else{
                break;
            }
        }
        return edges[i];
    }
    private int findPa(int v, int[] pa){
        while(pa[v]!=v)
            v=pa[v];
        return v;
    }
    private void set(int v, int pa, int[] a){
        int temp = 0;
        while(a[v]!=v&&a[v]!=pa){
            temp = v;
            v = a[v];
            a[temp] = pa;
        }
        a[v] = pa;
    }
    public static void main(String[] args){
       FindRedundantConnection frc = new FindRedundantConnection();
       int[] res = frc.findRedundantConnection(new int[][]{
              {9,10},
              {5,8},
              {2,6},
              {1,5},
              {3,8},
              {4,9},
              {8,10},
              {4,10},
              {6,8},
              {7,9}
       });
    }
}
