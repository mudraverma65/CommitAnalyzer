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
        commitM.addCommit("pallavi", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B","C")));
        commitM.addCommit("alen", 12, "B-190",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("dhruv", 12, "B-190",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("rishi", 12, "B-199",new LinkedHashSet<>(Arrays.asList("C","B")));
        commitM.addCommit("muskan", 12, "B-190",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("B")));
        commitM.addCommit("ashwati", 12, "B-192",new LinkedHashSet<>(Arrays.asList("E","A")));
        commitM.addCommit("mudra", 12, "B-193",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("dhruv", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("mudra", 12, "B-192",new LinkedHashSet<>(Arrays.asList("B","C","D")));
        commitM.addCommit("muskan", 12, "B-195",new LinkedHashSet<>(Arrays.asList("E","A")));
        commitM.addCommit("rishi", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B","E")));
        commitM.addCommit("pallavi", 12, "B-192",new LinkedHashSet<>(Arrays.asList("D","E")));
        commitM.addCommit("milind", 12, "B-194",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("limysh", 12, "B-192",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("mudra", 12, "B-199",new LinkedHashSet<>(Arrays.asList("B","D")));

        commitM.addCommit("alen", 12, "F-192",new LinkedHashSet<>(Arrays.asList("A","B","C")));
        commitM.addCommit("mudra", 12, "F-190",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("alen", 12, "F-190",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("kaushal", 12, "F-199",new LinkedHashSet<>(Arrays.asList("C","B")));
        commitM.addCommit("anirudh", 12, "F-190",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("milind", 12, "F-192",new LinkedHashSet<>(Arrays.asList("B")));
        commitM.addCommit("limysh", 12, "F-192",new LinkedHashSet<>(Arrays.asList("E","A"))); // AE
        commitM.addCommit("ashwati", 12, "F-193",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("pallavi", 12, "F-192",new LinkedHashSet<>(Arrays.asList("A","D")));
        commitM.addCommit("mudra", 12, "F-192",new LinkedHashSet<>(Arrays.asList("B","C","D")));
        commitM.addCommit("kaushal", 12, "F-195",new LinkedHashSet<>(Arrays.asList("E","A"))); // AE
        commitM.addCommit("mudra", 12, "F-192",new LinkedHashSet<>(Arrays.asList("D","E")));
        commitM.addCommit("kaushal", 12, "F-194",new LinkedHashSet<>(Arrays.asList("D","C")));
        commitM.addCommit("mudra", 12, "F-192",new LinkedHashSet<>(Arrays.asList("A","B")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("B","D")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));
        commitM.addCommit("rishi", 12, "F-199",new LinkedHashSet<>(Arrays.asList("A")));


        commitM.componentMinimum(2);
        Set<Set<String>> s3 = commitM.softwareComponents();
        System.out.println(s3);
        commitM.repetionInBugs(3);

        commitM.broadFeatures(2);
        commitM.experts(5);
        commitM.busyClasses(2);
        // commitM.getVertices();
    }
}
