import static org.junit.Assert.assertEquals;

public class Palindrome_Number {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int i = 1;
        while (x/i >= 10) {
            i *= 10;
        }
        while (true) {
            if(x/i != x%10)
                return false;
            x -= (x/i*i);
            x /= 10;
            i /= 100;
            if(i == 0)
                return true;
        }
    }
     public boolean IsPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }
    public static void main(String [] args){
        Palindrome_Number pN = new Palindrome_Number();
        assertEquals(pN.isPalindrome(131),true);
        assertEquals(pN.isPalindrome(1321),false);
        assertEquals(pN.isPalindrome(1331),true);
        assertEquals(pN.isPalindrome(1000),false);
        assertEquals(pN.isPalindrome(9999),true);
        assertEquals(pN.isPalindrome(0),true);
        assertEquals(pN.isPalindrome(1),true);
        assertEquals(pN.isPalindrome(101),true);
        assertEquals(pN.isPalindrome(1000021),false);
        assertEquals(pN.isPalindrome(21120),false);
        assertEquals(pN.isPalindrome(-1),false);


        assertEquals(pN.IsPalindrome(131),true);
        assertEquals(pN.IsPalindrome(1321),false);
        assertEquals(pN.IsPalindrome(1331),true);
        assertEquals(pN.IsPalindrome(1000),false);
        assertEquals(pN.IsPalindrome(9999),true);
        assertEquals(pN.IsPalindrome(0),true);
        assertEquals(pN.IsPalindrome(1),true);
        assertEquals(pN.IsPalindrome(101),true);
        assertEquals(pN.IsPalindrome(1000021),false);
        assertEquals(pN.IsPalindrome(21120),false);
        assertEquals(pN.IsPalindrome(-1),false);
    }
}
