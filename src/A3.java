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
        commitM.addCommit("mudra", 10, "B-190",new LinkedHashSet<>(Arrays.asList("A","B","C","D")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B","C")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("C","B")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("B")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("E","A")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("B","C","D")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("E","A")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B","E")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("D","E")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.componentMinimum(5);
        Set<Set<String>> s3 = commitM.softwareComponents();
        System.out.println(s3);
        // commitM.getVertices();
    }
}
