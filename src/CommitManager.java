import java.util.*;

public class CommitManager {

    List<Commit> allC = new ArrayList<Commit>();
    int startTime, endTime;
    public CommitManager(){

    }

    void addCommit( String developer, int commitTime, String Task, Set<String> commitFiles
    ) throws IllegalArgumentException{
        Commit newCommit = new Commit(developer, commitTime, Task, commitFiles);
        allC.add(newCommit);
        GraphM.getVertices(allC);
    }

//    void getVertices(){
//        Set<String> vertices = new LinkedHashSet<>();
//        for(Commit currentC : allC){
//            Iterator<String> files = currentC.commitFiles.iterator();
//            while(files.hasNext()){
//                vertices.add(files.next());
//            }
//
//        }
//        System.out.println(vertices);
//    }


    boolean setTimeWindow(int startTime, int endTime) {
        boolean val = true;
        try{
            while(endTime>startTime){
                this.startTime = startTime;
                this.endTime = endTime;
            }
        } catch (Exception e) {
            val = false;
        }
        return val;
    }

    void clearTimeWindow(){
        startTime = 0;
        endTime = 0;
    }
}
