import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindDuplicateFilesInSystem {
    public static void main(String[] args){
        FindDuplicateFilesInSystem f = new FindDuplicateFilesInSystem();
        List<List<String>> ss = f.findDuplicate(new String[]{
                "root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"

        });
    }
    public List<List<String>> findDuplicate(String[] paths) {
        //content -> list of path+"/"+filename
        HashMap<String, List<String>> hm = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        int[] ptr = new int[1];
        String path=null,fn=null,content=null;
        for(String e: paths){
            path = get(e,ptr,' ');
            while(ptr[0]<e.length()){
                fn =  get(e,ptr,'(');
                content = get(e,ptr,')');
                if(!hm.containsKey(content)){
                    hm.put(content,new ArrayList<String>());
                }
                hm.get(content).add(path+"/"+fn);
                ptr[0]++;
            }
            ptr[0]=0;
        }
        for(List<String> l : hm.values()){
            if(l.size()>1) res.add(l);
        }
        return res;
    }
    private String get(String s, int[] ptr,char target){
        StringBuilder res = new StringBuilder();
        char c = '\u0000';
        while((c=s.charAt(ptr[0]++))!=target){
            res.append(c);
        }
        return res.toString();
    }
}
