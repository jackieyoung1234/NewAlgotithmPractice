import static org.junit.Assert.assertEquals;
public class MyIndexPQ {
    int[] k2v;
    int[] k2i;
    int[] i2k;
    int ptr;
    public MyIndexPQ(){
        // x 0-99, y 0-99
        //hei, wid <=100, index =x*100+wid, max index number  = 100 * 100 = 10000
        //max = 99*100 + 99 = 100*100
        k2v = new int[10001];
        k2i = new int[10001];
        i2k = new int[10001];
        ptr = 1;//cur write index
    }
    private boolean smallerThan(int i, int j){
        return k2v[i2k[i]]<k2v[i2k[j]];
    }
    private void exch(int i, int j){
        int temp = i2k[i];
        i2k[i] = i2k[j];
        i2k[j] = temp;
        k2i[temp] = j;
        k2i[i2k[i]] = i;
    }
    private void swim(int i){
        int parent;
        while((parent=(i>>1))>0){
            if(smallerThan(i,parent)){
                exch(i,parent);
                i = parent;
            }else{
                break;
            }
        }
    }
    private void sink(int i){
        int child;
        while((child=(i<<1))<ptr){
            if(child+1<ptr&&smallerThan(child+1,child)) child++;
            if(smallerThan(child,i)){
                exch(child,i);
                i = child;
            }else{
                break;
            }
        }
    }
    public boolean isEmpty(){
        return ptr==1;
    }
    // key is x*100+wid
    public void put(int key, int val){
        k2v[++key]=val;
        i2k[ptr]=key;
        k2i[key]=ptr;
        swim(ptr++);
    }
    public void change(int key, int val){
        k2v[++key] = val;
        swim(k2i[key]);
        sink(k2i[key]);
    }
    public boolean contains(int key){
        return k2i[++key]!=0;
    }
    //pop the [key,val] with the minimum value
    public int[] poll(){
        int reskey = i2k[1];
        exch(1,--ptr);
        sink(1);
        k2i[reskey]=0;
        return new int[]{reskey-1,k2v[reskey]};
    }
    public static void main(String[] args){
        MyIndexPQ pq = new MyIndexPQ();
        pq.put(0,3);
        pq.put(2,7);
        pq.put(9,5);
        assertEquals(pq.contains(0),true);
        assertEquals(pq.contains(2),true);
        assertEquals(pq.contains(9),true);
        assertEquals(pq.contains(5),false);
        int[] pollres;
        assertEquals((pollres = pq.poll())[0],0);
        assertEquals(pollres[1],3);
        assertEquals(pq.contains(0),false);
        pq.put(0,6);
        assertEquals(pq.contains(0),true);
        assertEquals((pollres = pq.poll())[0],9);
        assertEquals(pollres[1],5);
        assertEquals(pq.contains(9),false);
        pq.change(2,3);
        assertEquals((pollres = pq.poll())[0],2);
        assertEquals(pollres[1],3);
        assertEquals(pq.contains(2),false);
        assertEquals((pollres = pq.poll())[0],0);
        assertEquals(pollres[1],6);
        assertEquals(pq.contains(0),false);
        assertEquals(pq.isEmpty(),true);
    }
}
