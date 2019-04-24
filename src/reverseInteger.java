public class reverseInteger {
    public int reverse2(int x) {
        int result = 0;
        int temp = 0;
        int q = 0;
        while(x != 0){
            temp = result*10;
            if(result != temp/10)
                return 0;
            result = temp;
            q = x%10;
            temp += q;
            if(
                    (((~q>>>31) & (~result >>>31) & (temp >>>31)) == 1)
                            ||
                    (((q>>>31) & (result >>>31) & (~temp >>>31)) == 1)
                    )
                return 0;
            result = temp;
            x /= 10;
        }
        return result;
    }
    public int reverse(int x) {
        int temp,result = 0;
        while(x != 0){
            temp= result*10 + x%10;
            if(result != temp/10)
                return 0;
            result = temp;
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println((-20)%10);
        System.out.println((-14)/10);
        reverseInteger myRI = new reverseInteger();
        int result = myRI.reverse(120);
        result = myRI.reverse(1534236469);
    }
}

