import java.util.LinkedList;
import java.util.Stack;

public class Braces {
    public boolean braces(String value){
       Stack<Character> sk = new Stack<>();
       char cur = '\u0000';
       for(int i=0;i<value.length();i++){
          cur = value.charAt(i);
          if(cur=='('||cur=='['||cur=='{'){
             sk.push(cur);
          }else{
             if (sk.isEmpty()||cur!=sk.pop())
                 return false;
          }
       }
       return sk.isEmpty();
    }
}
