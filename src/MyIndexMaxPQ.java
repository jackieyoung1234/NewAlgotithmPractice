import edu.princeton.cs.algs4.IndexMaxPQ;

public class MyIndexMaxPQ {
    int[] i2v;
    int[] r2i;
    int[] i2r;
    int ptr;
    public MyIndexMaxPQ(int s){
        ptr = 1;
        i2v=new int[s+1];
        r2i=new int[s+1];
        i2r=new int[s+1];
    }
    //index is index in nums[],
    //but insert(0, nums[0]) -> i2v[1]=nums[0]
    //but insert(3, nums[3]) -> i2v[3%k+1]=nums[3]
    public void insert(int i, int v){
        i2v[i]=v;
        i2r[i]=ptr;
        r2i[ptr]=i;
        swim(ptr++);
    }
    //index is index in nums[],
    //but change(3, nums[3]) -> i2v[3$size+1]=nums[0]
    //but change(5, nums[5]) -> i2v[5%size + 1]=nums[5]
    public void change(int i, int v){
        int r = i2r[i];
        i2v[i] = v;
        swim(r);
        sink(r);
    }
    private int parent(int r){
        return r>>1;
    }
    private int lchr(int r){
        return r<<1;
    }
    //swim and sink only change r2i and i2r
    private boolean largerThan(int r1, int r2){
        return i2v[r2i[r1]]>i2v[r2i[r2]];
    }
    private void swim(int r){
        int par =0;
        while(r>1){
            if(largerThan(r,par=parent(r))){
                exch(r,par);
                r=par;
            }else{
                break;
            }
        }
    }
    private void sink(int r){
        int chr =0;
        while((chr=lchr(r))<ptr){
            if(chr+1<ptr&&largerThan(chr+1,chr)) chr++;
            if(largerThan(chr,r)){
                exch(chr,r);
                r=chr;
            }else{
                break;
            }
        }
    }
    private void exch(int r1, int r2){
        int i1 = r2i[r1];
        r2i[r1]=r2i[r2];
        r2i[r2]=i1;
        i2r[i1]=r2;
        i2r[r2i[r1]]=r1;
    }
    public static void main(String[] args){
        IndexMaxPQ<Integer>  p = new IndexMaxPQ<>(2);
        p.insert(1,1);
        p.insert(2,1);
        p.insert(3,1);
    }
}
