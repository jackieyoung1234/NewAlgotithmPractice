import static org.junit.Assert.assertEquals;

public class BS_ArrayHolesFind{
	public int find(int[] arr, int k){
	    int l = 0, r = arr.length-1,mid = 0,leftHNum = 0;
	    if(arr==null||l>=r)
	    	throw new RuntimeException("intput array is invalid");
	    int totalHNums = arr[r]+l-arr[l]-r;
	    if(totalHNums<k) throw new RuntimeException("not enough holes");
 
	    while(l<r-1){
		mid = l + (r-l)/2;
		leftHNum = arr[mid]+l-arr[l]-mid;
		if(k>leftHNum){
		    k-=leftHNum;
		    l=mid;
		}else{
		    r=mid;
		}
	    }	
	    return arr[l]+k;
	}
	//case 1 
	//3 4 5     8   10     13   15        20    k = 4
	//l         m                          r
	//          l                          r      k = 2
	//                     m
	//               m     r
	//               l pass
	
	//l m        r                                         k =2
	//  l m      r
	//    l
    public static void main(String[] args){
	    BS_ArrayHolesFind ba = new BS_ArrayHolesFind();
	    assertEquals(ba.find(
	    		new int[]{3,4,5,     8,  10,  13,   15,   20},
				1
		),
		6);
		assertEquals(ba.find(
				new int[]{3,4,5,     8,  10,  13,   15,   20},
				2
				),
				7);
		assertEquals(ba.find(
				new int[]{3,4,5,     8,  10,  13,   15,   20},
				5
				),
				12);
		assertEquals(ba.find(
				new int[]{3,4,5,     8,  10,  13,   15,   20},
				6
				),
				14);
		assertEquals(ba.find(
				new int[]{3,4,5,     8,  10,  13,   15,   20},
				8
				),
				17);

	}
}
