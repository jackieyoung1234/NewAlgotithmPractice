import java.util.Arrays;
import java.util.List;

public class ZeroingArray{
    /*
     * https://leetcode.com/discuss/interview-question/378330/AWS-or-OA-2019-or-Deleting-Arrays
     */
	public int[] deleteArrays(int[] nums, int[] queries) {
	    	//edge case : nums.len = 0
         	if(nums.length==0)	return nums;
	    	int qlen = queries.length;
	    	Arrays.sort(queries);
	    	int ptr = qlen;//cur top pos, aka, cur read pos 
		int cur;
	    	for(int i=qlen-1;i>=0;i--){
			cur = queries[i];
			//while(stack.notEmpty()&&cur>=stack.peek()){
			while(ptr!=qlen&&nums[cur]>=nums[queries[ptr]]){
			    //stack.pop();
			    ++ptr;
			}
			//stack.push(cur);
			queries[--ptr] = cur;
		}	
		cur = queries[qlen-1];
		for(int j=qlen-1,i=nums.length-1;i>=0&&j>=ptr;i--){
		    while(i<=cur){
				if(--j>=ptr){
				    cur = queries[j];
				}else{
				    return nums;
				}
		    }
		    if(nums[i]<nums[cur]){
			nums[i]	= 0;
		    }
		}
		return nums;
	}
	public static void main(String[] args){
	    ZeroingArray z = new ZeroingArray();
	    int[] res = z.deleteArrays(new int[]{4,3,4,3,2},new int[]{3,2});
	    res = z.deleteArrays(new int[]{4,4,3,3,100,100,7,2},new int[]{7,5,2,0,0});
	    List<Integer> ff = Arrays.asList(1,2);
	}
}
