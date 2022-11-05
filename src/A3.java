import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class A3 {
    public static void main(String[] args){
        CommitManager commitM = new CommitManager();
//        Set<String> s1 = new LinkedHashSet<String>();
//        s1.add("A");
//        s1.add("B");
//        s1.add("C");
        commitM.addCommit("mudra", 10, "B-190",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("D","F","G")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("Q","F","G")));
        //commitM.getVertices();
    }
}
