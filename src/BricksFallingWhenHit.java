public class BricksFallingWhenHit {
    private class UF{
        int[] pa;
        int[] size;
        public UF(int s){
            //[0] is never used, every pa is ini to 0, which is invalid
            //every size is ini to 0, which is also invalid
            // so i need set pa every time for '1'
            // and ini size to 1 for every '1'
           pa = new int[++s];
            size = new int[s];
        }
        //i is original index
        private void set(int i){
            if(pa[++i]==0){
                pa[i] = i;
                size[i] = 1;
            }
        }
        //connect all the '1' connected to top row x y, set pa and size
        //i is original index
        private void doUnion(int x,int y, int[][] grid ){
            int i = x*grid[0].length+y+1;
            //int i = y+1;
            dfs(x,y,i,grid);
        }
        private int dfs(int x, int y, int parentIndex, int[][] grid){
            int i = x*grid[0].length+y+1;
            if(grid[x][y]==1&&pa[i]==0){//not visited and is '1'
               pa[i]=parentIndex;
               size[i] = 1;
            }else{
               return 0;
            }
            //u d l r
            if(x>0){
               size[i]+=dfs(x-1,y,parentIndex,grid);
            }
            if(x<grid.length-1){
               size[i]+=dfs(x+1,y,parentIndex,grid);
            }
            if(y>0){
               size[i]+=dfs(x,y-1,parentIndex,grid);
            }
            if(y<grid[0].length-1){
               size[i]+=dfs(x,y+1,parentIndex,grid);
            }
            return size[i];
        }
        public int getTotalFirstRowSize(int colLength){
            int res = 0;
           for(int i=1;i<=colLength;i++){
              if(pa[i]==i){
                  res+=size[i];
              }
           }
            return res;
        }
        private int findParent(int i){
           int parent = pa[i];
            while(parent!=i){
               pa[i] = pa[parent];
               i = parent;
               parent = pa[i];
            }
            return i;
        }
        //i1, i2 are ++1 index
        //guaranteed both are '1', thus pa[i1] pa[i2] is not 0
        public void connect(int i1, int i2, int colLength){
            int pa1 = findParent(i1);
            int pa2 = findParent(i2);
            if(i1==i2) return;
            else{
               if((pa1<=colLength&&pa2<=colLength)
                  ||
                  (pa1>colLength&&pa2>colLength)
                 ){//both are first row or neither is first row
                  if(size[pa1]<=size[pa2]){
                     pa[pa1] = pa2;
                     size[pa2] += size[pa1];
                  } else{
                     pa[pa2] = pa1;
                     size[pa1] += size[pa2];
                  }
               }else if(pa2<=colLength){ // pa2 is first row{
                     pa[pa1] = pa2;
                     size[pa2] += size[pa1];
               }else{
                     pa[pa2] = pa1;
                     size[pa1] += size[pa2];
               }
            }
        }
    }
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m*n);//?? m*n+1??
        for(int[] hit: hits){
           grid[hit[0]][hit[1]]--;
        }
        //do union find for all top level 1
        for(int i=0;i<m;i++){
            for(int ii=0;ii<n;ii++){
               if(grid[i][ii]==1){
                  uf.doUnion(i,ii,grid);
               }
            }
        }
        int totalcnt = uf.getTotalFirstRowSize(n),next =0;
        int[] res = new int[hits.length];
        for(int i=hits.length-1;i>=0;i--){
           int x = hits[i][0], y = hits[i][1], i1=x*n+y+1;
            if(++grid[x][y]==1){
                //grid[x][y]=1;
                uf.set(x*n+y);
                if(x>0&&grid[x-1][y]==1){//up
                    //i1, i2 are ++1 index
                    //guaranteed both are '1', thus pa[i1] pa[i2] is not 0
                    //connect(int i1, int i2, int colLength){
                    uf.connect(i1,i1-n,n);
                }
                if(x<m-1&&grid[x+1][y]==1){//down
                    //i1, i2 are ++1 index
                    //guaranteed both are '1', thus pa[i1] pa[i2] is not 0
                    //connect(int i1, int i2, int colLength){
                    uf.connect(i1,i1+n,n);
                }
                if(y>0&&grid[x][y-1]==1){//left
                    //i1, i2 are ++1 index
                    //guaranteed both are '1', thus pa[i1] pa[i2] is not 0
                    //connect(int i1, int i2, int colLength){
                    uf.connect(i1,i1-1,n);
                }
                if(y<n-1&&grid[x][y+1]==1){//right
                    //i1, i2 are ++1 index
                    //guaranteed both are '1', thus pa[i1] pa[i2] is not 0
                    //connect(int i1, int i2, int colLength){
                    uf.connect(i1,i1+1,n);
                }
                next = uf.getTotalFirstRowSize(n);
                res[i]=Math.max(0,next-totalcnt-1);
                totalcnt=next;
            }
        }
        return res;
    }
    public static void main(String[] args){
        BricksFallingWhenHit bf = new BricksFallingWhenHit();
        int[] res = bf.hitBricks(
               new int[][]{
                       {0,1,1,1,1},{1,1,1,1,0},{1,1,1,1,0},{0,0,1,1,0},{0,0,1,0,0},
                       {0,0,1,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}
               },
                new int[][]{
                        {6,0},{1,0},{4,3},{1,2},{7,1},{6,3},{5,2},{5,1},{2,4},{4,4},{7,3}
                }
        );
    }
}
