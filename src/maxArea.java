import static org.junit.jupiter.api.Assertions.assertEquals;

public class maxArea {
    public int solution(int[] height){
        int l = 0, h = height.length-1;
        int result = 0;
        int temp;
        while(l < h) {
            int comp = Integer.compare(height[l],height[h]);
            if(comp<0) {
                result = Integer.max(result,height[l]*(h-l));
                l++;
            }
            else if(comp>0) {
                result = Integer.max(result,height[h]*(h-l));
                h--;
            }
            else{
                result = Integer.max(result,height[l]*(h-l));
                l++;
                h--;
            }
        }
        return result;
    }
    public static void main(String[] args){
        assertEquals(new maxArea().solution(new int[]{1,1}),1);

    }
}
