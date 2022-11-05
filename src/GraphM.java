import java.util.*;

public class GraphM {
    int vertices;
    int matrix[][];


    public GraphM(int vertices){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }
    public void addPath(int vertical, int horizontal){
        matrix[vertical][horizontal] = matrix[vertical][horizontal]++;
        matrix[horizontal][vertical] = matrix[horizontal][vertical]++;
    }

    void createGraph(List<Commit> allCommits1){
        Set<String> vertexAll = new LinkedHashSet<>();
        vertexAll = getVertex(allCommits1);
        int verticesAll = vertexAll.size();
        int matrix[][] = new int[verticesAll][verticesAll];


    }

    static void getVertices(List<Commit> allCommits){
        Set<String> vertices = new LinkedHashSet<>();
        for(Commit currentC : allCommits){
            Iterator<String> files = currentC.commitFiles.iterator();
            while(files.hasNext()){
                vertices.add(files.next());
            }

        }
        //System.out.println(vertices);
    }

    Set<String> getVertex(List<Commit> allCommits){
        Set<String> vertices = new LinkedHashSet<>();
        for(Commit currentC : allCommits){
            Iterator<String> files = currentC.commitFiles.iterator();
            while(files.hasNext()){
                vertices.add(files.next());
            }
        }
        return vertices;
    }

}
