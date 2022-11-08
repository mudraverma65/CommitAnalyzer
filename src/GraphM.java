import java.util.*;

public class GraphM {

    Map<String, String> edge = new LinkedHashMap<>();
    int vertices;
    int matrix[][];



    public GraphM(int vertices){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }

    public GraphM() {

    }

    int[][] createGraph(List<Commit> allCommits1){
        Set<String> vertexA = new LinkedHashSet<>();
        List<String> vertexAll = getVertex(allCommits1);
        int verticesAll = vertexAll.size();
        int matrix[][] = new int[verticesAll][verticesAll];
        for(int i =0; i<verticesAll; i++) {
            for (int j = 0; j < verticesAll; j++) {
                for (Commit current : allCommits1) {
                    if (current.commitFiles.contains(vertexAll.get(i)) && current.commitFiles.contains(vertexAll.get(j))) {
                        matrix[i][j] = matrix[i][j]+ 1;
                    }
                }
            }
        }
        return matrix;
    }
    List<String> getVertex(List<Commit> allCommits){
        Set<String> vertices = new LinkedHashSet<>();
        for(Commit currentC : allCommits){
            Iterator<String> files = currentC.commitFiles.iterator();
            while(files.hasNext()){
                vertices.add(files.next());
            }
        }
        List<String> vertexA = new ArrayList<>(vertices);
        return vertexA;
    }

    List<Commit> getcommit(List<Commit> allCommits,int startTime, int endTime){
        List<Commit> currentCommits = new ArrayList<>();
        if(endTime!=0){
            for(Commit commitWindow: allCommits){
                if(commitWindow.commitTime>=startTime && commitWindow.commitTime<=endTime){
                    currentCommits.add(commitWindow);
                }
            }
        } else if (endTime == 0) {
            return allCommits;
        }
        return currentCommits;
    }

}
