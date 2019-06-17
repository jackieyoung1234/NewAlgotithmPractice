import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if(m==1||n==1) return 1;
        if(m<n){
           m= m^n;
           n= m^n;
           m= m^n;
        }
        m--;
        n--;
        int res = 1;
        for(int i=m+1,j=1;i<=m+n;i++,j++){
            res*=i;
            res/=j;
        }
        return res;
    }
    //(m+n)Cm
    //    (m+n)! /m!/n!
    public static void main(String[] args){
        assertEquals(new UniquePaths().uniquePaths(3,4),10);
    }
}
