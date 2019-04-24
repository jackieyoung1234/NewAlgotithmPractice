import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class simplifyPath {
    public String simplifyPath(String path) {
        String[] dir = path.split("/");
        String[] stack = new String[dir.length];
        int ptr = 0;
        for(int i = 0; i < dir.length; i++){
            if(dir[i].equals(".") || dir[i].equals("")){
                continue;
            }else if(dir[i].equals("..")){
                if(ptr > 0) ptr--;
            }else{
                stack[ptr] = dir[i];
                ptr++;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < ptr; i++){
            result.append("/");
            result.append(stack[i]);
        }
        return result.length() == 0 ? "/" : result.toString();
    }
}
