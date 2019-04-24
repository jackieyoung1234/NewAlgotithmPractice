import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class parenthesesValid {
    public boolean isValid_Version1(String s) {
        if(s.length()%2 !=0)
            return false;
        char[] scArr = s.toCharArray();
        char[] charL = new char[]{'(','[','{'};
        char[] charR = new char[]{')',']','}'};
        Stack<Character> ss = new Stack<>();
        int index = 0;
        while(index < s.length()){
            char temp = scArr[index++];
            int match = temp == charR[0]?0:temp == charR[1]?1:temp == charR[2]?2:-1;
            if(match >=0){
                if(ss.isEmpty()) return false;
                char m = ss.pop();
                if( m != charL[match]) return false;
            }else{
                ss.push(temp);
            }
        }
        if(ss.isEmpty())
            return true;
        return false;
    }
    public boolean isValid_Version2(String s) {
        if(s.length()%2 !=0)
            return false;
        char[] scArr = s.toCharArray();
        char[] charL = new char[]{'(','[','{'};
        HashMap<Character,Integer> hh = new HashMap<Character, Integer>(){
            {
                put(')',0);
                put(']',1);
                put('}',2);
            }
        };
        Stack<Character> ss = new Stack<>();
        int index = 0;
        while(index < s.length()){
            char temp = scArr[index++];
            int match = hh.getOrDefault(temp,-1);
            if(match >=0){
                if(ss.isEmpty()) return false;
                char m = ss.pop();
                if( m != charL[match]) return false;
                if( m != charL[match]) return false;
                if( m != charL[match]) return false;
            }else{
                ss.push(temp);
            }
        }
        if(ss.isEmpty())
            return true;
        return false;
    }
    public boolean isValid(String s) {
        Stack<Character>  ss = new Stack<>();
        for(char t : s.toCharArray()){
            if(t == '{') ss.push('}');
            else if(t == '[') ss.push(']');
            else if(t == '(') ss.push(')');
            else if(ss.isEmpty() || t != ss.pop()) return false;
        }
        return ss.isEmpty();
    }
    public static void main(String[] args){
        boolean result = new parenthesesValid().isValid("()");
        result = new parenthesesValid().isValid("()[]");
        result = new parenthesesValid().isValid("()[]{}");
        result = new parenthesesValid().isValid("([{}])");
        result = new parenthesesValid().isValid("(]");
        result = new parenthesesValid().isValid("{}(]{}");
    }
}
