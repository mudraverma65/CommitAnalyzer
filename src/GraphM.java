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

    public void addPath(int vertical, int horizontal){
        matrix[vertical][horizontal] = matrix[vertical][horizontal]++;
        matrix[horizontal][vertical] = matrix[horizontal][vertical]++;
    }

    int[][] createGraph(List<Commit> allCommits1){
        Set<String> vertexA = new LinkedHashSet<>();

        //vertexA = getVertex(allCommits1);
        //List<String> vertexAll = new ArrayList<>(vertexA);
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
        System.out.println("Graph: (Adjacency Matrix)");
        for (int i = 0; i < verticesAll; i++) {
            for (int j = 0; j <verticesAll ; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
        return matrix;
        //System.out.println(matrix);
    }

    void createPaths(List<Commit> allCommits2){
        for(Commit current : allCommits2){
            Iterator<String> files = current.commitFiles.iterator();
            while(files.hasNext()){

            }
        }
    }



    static void getVertices(List<Commit> allCommits){
        Set<String> vertices = new LinkedHashSet<>();
        for(Commit currentC : allCommits){
            Iterator<String> files = currentC.commitFiles.iterator();
            while(files.hasNext()){
                vertices.add(files.next());
            }

        }
        System.out.println(vertices);
    }

//    Set<String> getVertex(List<Commit> allCommits){
//        Set<String> vertices = new LinkedHashSet<>();
//        //String vertices = new String();
//        //List<String> vertices = new ArrayList<>();
//        for(Commit currentC : allCommits){
//            Iterator<String> files = currentC.commitFiles.iterator();
//            while(files.hasNext()){
//                vertices.add(files.next());
//            }
//        }
//        return vertices;
//    }

    List<String> getVertex(List<Commit> allCommits){
        Set<String> vertices = new LinkedHashSet<>();
        //String vertices = new String();
        //List<String> vertices = new ArrayList<>();
        for(Commit currentC : allCommits){
            Iterator<String> files = currentC.commitFiles.iterator();
            while(files.hasNext()){
                vertices.add(files.next());
            }
        }
        List<String> vertexA = new ArrayList<>(vertices);
        return vertexA;
    }

    List<Commit> getcommits(List<Commit> allCommits,int startTime, int endTime){
        List<Commit> currentCommits = new ArrayList<>();
        for(Commit commitWindow: allCommits){
            while(commitWindow.commitTime>startTime && commitWindow.commitTime<endTime){
                currentCommits.add(commitWindow);
            }
        }
        return currentCommits;
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

}
