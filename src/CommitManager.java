import java.util.*;

public class CommitManager {

    List<Commit> allC = new ArrayList<Commit>();
    int startTime, endTime;

    int matrix[][];
    GraphM g1 = new GraphM();
    int threshold;
    Set<Set<String>> components = new LinkedHashSet<>();
    public CommitManager(){

    }

    void addCommit( String developer, int commitTime, String Task, Set<String> commitFiles
    ) throws IllegalArgumentException{
        Commit newCommit = new Commit(developer, commitTime, Task, commitFiles);
        allC.add(newCommit);
        //g1.createGraph(allC);
        //g1.getVertices(allC);

    }

   Set<Set<String>> softwareComponents(){
        List<String> allvertices = g1.getVertex(allC);
        Set<String> individualSet = new LinkedHashSet<>();
        //individualSet.add(allvertices.get(0)); //not rewuired
        matrix = g1.createGraph(allC);
        for(int i = 0; i<matrix.length; i++){
            //if(individualSet.contains(allvertices.get(i))){ //check placement
                for(int j = 0; j<matrix.length; j++){
                    if(matrix[i][j] >= threshold){
                        if(individualSet.contains(allvertices.get(i))) {
                            individualSet.add(allvertices.get(j));
                        }
                        else {
                            if(individualSet.isEmpty() == true){
                               // components.add(individualSet);
                                // individualSet = new LinkedHashSet<>();
                                individualSet.add(allvertices.get(i));
                            }
                            else{
                                components.add(individualSet);
                                individualSet = new LinkedHashSet<>();
                                individualSet.add(allvertices.get(i));
                            }
                        }
                    }
                    else {
                        if(j == matrix.length-1){
                            individualSet.add(allvertices.get(i));
                            //individualSet.retainAll(allvertices);
                            if(individualSet.size()==1){
                                components.add(individualSet);
                                individualSet = new LinkedHashSet<>();
                            }
                        }

//                        if(individualSet.isEmpty()==true){
//                            individualSet.add(allvertices.get(i));
//                            if(!components.contains(individualSet)) {
//                                components.add(individualSet);
//                                individualSet = new LinkedHashSet<>();
//                            }
//                        }



//                            for(Set<String> s1: components){
//                                if(!s1.contains(individualSet)){
//                                    components.add(individualSet);
//                                    individualSet = new LinkedHashSet<>();
//                                }
//                            }
                        }

//                        if(individualSet.contains(allvertices.get(j))==false){
//
//                        }
                    //}
//                    else{
//                        individualSet.add(allvertices.get(j));
//                        if(individualSet.isEmpty()){
//                            individualSet.add(allvertices.get(i));
//                        }
////                        while(components.isEmpty()==false){
////                            for (Set<String> s1: components) {
////                                if(s1.contains(allvertices.get(i))==false){
////                                    individualSet.add(allvertices.get(i));
////                                    components.add(individualSet);
////                                    individualSet = new LinkedHashSet<>();
////                                }
////                            }
////                        }
////
////                        if(individualSet.contains(allvertices.get(i))==false){
////
////                        }
//                        individualSet.add(allvertices.get(j));
//                        components.add(individualSet);
//                        individualSet = new LinkedHashSet<>();
//                    }
                }
            }
        //components.add((individualSet));
        return components;
    }
        //components.add(individualSet);

   boolean componentMinimum(int threshold){
        if(threshold>0){
            this.threshold = threshold;
            return true;
        }
        else{
            return false;
        }
   }

   Set<String> repetionInBugs(int threshold){
       Set<String> ind = new LinkedHashSet<>();
       return ind;
    }


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
