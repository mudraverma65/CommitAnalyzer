import java.util.Set;

public class Commit {
    String developer;
    int commitTime;
    String Task;
    Set<String> commitFiles;

    Commit(String developer, int commitTime, String Task, Set<String> commitFiles){
        this. developer = developer;
        this. commitTime = commitTime;
        this.Task = Task;
        this.commitFiles = commitFiles;
    }
}
