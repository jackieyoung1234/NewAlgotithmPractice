import java.util.Stack;

public class largestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> indexStack = new Stack<>();
        int max = 0,len = heights.length;
        for(int c = 0; c < len; c++){
            int cur = heights[c];
            if(indexStack.empty()||heights[indexStack.peek()]<=cur) indexStack.push(c);
            else{
                //empty ck
                while(!indexStack.empty()&&heights[indexStack.peek()]>cur){
                    int cc = heights[indexStack.pop()];
                    int left = indexStack.empty()?-1:indexStack.peek();
                    max = Math.max((c-left-1)*cc,max);
                }
            }
        }
        if(!indexStack.empty()){
            int index0 = indexStack.get(0);
            max = Math.max(max,heights[index0]*(indexStack.peek()-index0+1));
        }
        return max;
    }
    public static void main(String[] args){
       int result = new largestRectangleArea().largestRectangleArea(new int[]{2,1,5,6,2,3}) ;
    }
}
