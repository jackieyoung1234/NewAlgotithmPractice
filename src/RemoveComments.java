import java.util.LinkedList;
import java.util.List;

public class RemoveComments {
    public List<String> removeComments(String[] source) {
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean flag1 = false;//,flag2 = false;
        int len = 0;
        for(int i=0;i<source.length;i++){
            len = source[i].length();
            for(int j=0;j<len;j++){
                //  '//'
                if(j<len-1&&!flag1&&source[i].charAt(j)=='/'&&source[i].charAt(j+1)=='/'){
                    //flag2 = true;
                    break;
                    //  '/*'
                }else if(j<len-1&&!flag1&&source[i].charAt(j)=='/'&&source[i].charAt(j+1)=='*'){
                    flag1 = true;
                    j++;
                }
                else if(j<len-1&&flag1&&source[i].charAt(j)=='*'&&source[i].charAt(j+1)=='/'){
                    flag1  =false;
                    j++;
                }
                else if(!flag1){
                    sb.append(source[i].charAt(j));
                }
            }
            if(sb.length()!=0&&!flag1){
                res.add(sb.toString());
                //if(flag2) flag2 = false;
                sb=new StringBuilder();
            }
        }
        return res;
    }
    public static void main(String[] args){
       RemoveComments r = new RemoveComments();
       String[] source = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
       List<String> res = r.removeComments(source);
    }
}
