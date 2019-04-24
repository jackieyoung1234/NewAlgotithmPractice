package Tree;

public class FenwickTree {
    private int[] tree;
    public FenwickTree(int size){
        tree = new int[++size];
    }
    /**
     * @param index:  index is index in the original array
     *                e.g. [1,2,4] getsum(1) = 3
     */
    public int getSum(int index){
        int i = ++index, res = 0;
        if(i>tree.length-1) return res;
        while(i>0){
            res+=tree[i];
            i-=(i&(-i));
        }
        return res;
    }
    public void update(int index){
        int i = ++index;
        while(i<tree.length){
            tree[i]++;
            i+=(i&(-i));
        }
    }
}
