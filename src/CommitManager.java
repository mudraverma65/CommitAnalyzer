import java.util.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;


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
    }

   Set<Set<String>> softwareComponents(){
        List<Commit> currentWindow = g1.getcommit(allC,startTime,endTime);
        List<String> allvertices = g1.getVertex(currentWindow);
        Set<String> individualSet = new LinkedHashSet<>();
        matrix = g1.createGraph(currentWindow);
        for(int i = 0; i<matrix.length; i++){
                for(int j = 0; j<matrix.length; j++){
                    if(matrix[i][j] >= threshold){
                        if(individualSet.contains(allvertices.get(i))) {
                            individualSet.add(allvertices.get(j));
                        }
                        else {
                            if(individualSet.isEmpty() == true){
                                individualSet.add(allvertices.get(i));
                            }
                            else{
                                components.add(individualSet);
                                individualSet = new LinkedHashSet<>();
                                individualSet.add(allvertices.get(i));
                            }
                        }
                    }
                    else if(j == matrix.length-1 && individualSet.contains(allvertices.get(i))==false){
                            components.add(individualSet);
                            individualSet = new LinkedHashSet<>();
                            individualSet.add(allvertices.get(i));
                        }
                    else if(i ==matrix.length-1 && j == matrix.length-1){
                        components.add(individualSet);
                        individualSet = new LinkedHashSet<>();
                        individualSet.add(allvertices.get(i));
                    }
                }
            }
        if(!individualSet.isEmpty()){
            components.add((individualSet));
        }
        return components;
    }

   boolean componentMinimum(int threshold){
        if(threshold>0){
            this.threshold = threshold;
            return true;
        }
        else{
            return false;
        }
   }

   Set<String> repetionInBugs(int threshold) {
       List<Commit> currentWindow = g1.getcommit(allC,startTime,endTime);
        Set<String> bugFiles = new LinkedHashSet<>();
       Map<String, Integer> fileFrequency = new LinkedHashMap<>();
       Map<String, Map<String, Integer>> bugFile = new LinkedHashMap<>();
       for (Commit currentC : currentWindow) {
           String TaskInitial = String.valueOf(currentC.Task.charAt(0));
           if(TaskInitial.equals("B")){
               Iterator<String> it1 = currentC.commitFiles.iterator();
               while (it1.hasNext()) {

                   String currentFile = it1.next();
                   if(bugFile.containsKey(currentC.Task)){
                       fileFrequency = new LinkedHashMap<>();
                       fileFrequency = bugFile.get(currentC.Task);
                       if(fileFrequency.containsKey(currentFile)){
                           int frequency = fileFrequency.get(currentFile);
                           fileFrequency.put(currentFile,frequency +1);
                       }
                       else{
                           fileFrequency.put(currentFile,1);
                       }
                   }
                   else{
                       fileFrequency = new LinkedHashMap<>();
                       fileFrequency.put(currentFile,1);
                       bugFile.put(currentC.Task, fileFrequency);
                   }
               }
           }

           for(String bug: bugFile.keySet()){
               Map<String, Integer> currentFiles = bugFile.get(bug);
               int maxF = 0;
               for(Integer fileF: currentFiles.values()){
                   if(maxF<fileF){
                       maxF = fileF;
                   }
               }
               if(maxF>=threshold){
                   bugFiles.add(bug);
               }
           }
       }
       System.out.println(bugFile);
       System.out.println(bugFiles);
       return bugFiles;
    }

    Set<String> broadFeatures(int threshold){
        List<Commit> currentWindow = g1.getcommit(allC,startTime,endTime);
        Set<String> broadFeatures = new LinkedHashSet<>();
        Map<String, Set<String>> file = new LinkedHashMap<>();
        for (Commit currentC : currentWindow) {
            String TaskInitial = String.valueOf(currentC.Task.charAt(0));
            if(TaskInitial.equals("F")){
                if(file.containsKey(currentC.Task)){
                    Set<String> currentFile = file.get(currentC.Task);
                    currentFile.addAll(currentC.commitFiles);
                    file.put(currentC.Task, currentFile);
                }
                else{
                    file.put(currentC.Task, currentC.commitFiles);
                }
            }
        }

        Set<Set<String>> currentC = components;;

        for( Map.Entry<String, Set<String>> currentmap : file.entrySet()){
            int count=0;
            String currentKey = currentmap.getKey();
            Set<String> currentValue = currentmap.getValue();
            for(Set<String> currentcomponent: currentC){
                currentValue.retainAll(currentcomponent);
                if(currentValue.size()>0){
                    count++;
                }
            }
            if(count==threshold){
                broadFeatures.add(currentmap.getKey());
            }
        }
        System.out.println(file);
        System.out.println(broadFeatures);
        return broadFeatures;
    }

    Set<String>  experts (int threshold){
        List<Commit> currentWindow = g1.getcommit(allC,startTime,endTime);
        Set<String> experts = new LinkedHashSet<>();
        Map<String, Set<String>> file = new LinkedHashMap<>();
        for (Commit currentC : currentWindow) {
            if(file.containsKey(currentC.developer)){
                    Set<String> currentFile = file.get(currentC.developer);
                    currentFile.addAll(currentC.commitFiles);
                    file.put(currentC.developer, currentFile);
                }
                else{
                    file.put(currentC.developer, currentC.commitFiles);
                }
            }
        Set<Set<String>> currentC = components;
        for( Map.Entry<String, Set<String>> currentmap : file.entrySet()){
            int count=0;
            String currentKey = currentmap.getKey();
            Set<String> currentValue = currentmap.getValue();
            for(Set<String> currentcomponent: currentC){
                //Set<String> currentFiles = currentValue;
                currentValue.retainAll(currentcomponent);
                if(currentValue.size()>0){
                    count++;
                }
            }
            if(count==threshold){
                experts.add(currentmap.getKey());
            }
        }
        System.out.println(file);

        System.out.println(experts);
        return experts;

    }

    List<String> busyClasses (int limit){
        List<String> busyClasses = new ArrayList<>();
        List<Commit> currentWindow = g1.getcommit(allC,startTime,endTime);

        Map<String, Integer> fileFrequency = new HashMap<>();
        for (Commit currentC : currentWindow) {
            Iterator<String> it1 = currentC.commitFiles.iterator();
            while (it1.hasNext()) {
                String currentFile = it1.next();
                if(fileFrequency.containsKey(currentFile)){
                    int freq = fileFrequency.get(currentFile);
                    fileFrequency.put(currentFile,freq+1);
                }
                else{
                    fileFrequency.put(currentFile,1);
                }
            }
        }
        //https://www.javacodegeeks.com/2017/09/java-8-sorting-hashmap-values-ascending-descending-order.html#:~:text=In%20order%20to%20sort%20in,reverseOrder()%20or%20Comparator.
        Map<String,Integer>sortedFiles = fileFrequency
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        int count=0;
        List<String> valuesH = new ArrayList<>();

        Iterator<Map.Entry<String,Integer>> iterator = sortedFiles.entrySet().iterator();
        while (iterator.hasNext() && count<limit) {
           // Integer counter = iterator.next().getValue();
            String currentFile = iterator.next().getKey();
            valuesH.add(currentFile);
            busyClasses.add(currentFile);
            count++;
        }

        if(busyClasses.isEmpty()==false) {
            String lastFile = busyClasses.get(busyClasses.size() - 1);
            int frequency = sortedFiles.get(lastFile);
            for (Map.Entry<String, Integer> entry : sortedFiles.entrySet()) {
                if (entry.getValue() == frequency) {
                    if (!busyClasses.contains(entry.getKey())) {
                        busyClasses.add(entry.getKey());
                    }
                }
            }
        }
        return busyClasses;
    }


    boolean setTimeWindow(int startTime, int endTime) {
        boolean val = true;
        try{
            if(endTime>startTime){
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
