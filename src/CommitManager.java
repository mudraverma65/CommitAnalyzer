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
        List<Commit> currentWindow = g1.getcommits(allC,startTime,endTime);
        g1.createGraph(currentWindow);
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
                    else if(j == matrix.length-1 && individualSet.contains(allvertices.get(i))==false){
                            components.add(individualSet);
                            individualSet = new LinkedHashSet<>();
                            individualSet.add(allvertices.get(i));

//                            if(individualSet.contains(allvertices.get(i))==false){
//                                components.add(individualSet);
//                                individualSet = new LinkedHashSet<>();
//                            }

//                            individualSet.add(allvertices.get(i));
//                            individualSet.retainAll(allvertices);
//                            if(individualSet.size()==1){
//                                components.add(individualSet);
//                                individualSet = new LinkedHashSet<>();
//                            }
                        }
                    else if(i ==matrix.length-1 && j == matrix.length-1){
                        components.add(individualSet);
                        individualSet = new LinkedHashSet<>();
                        individualSet.add(allvertices.get(i));
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
                        //}

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
        if(!individualSet.isEmpty()){
            components.add((individualSet));
        }
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

   Set<String> repetionInBugs(int threshold) {
        Set<String> bugFiles = new LinkedHashSet<>();
       Map<String, Integer> fileFrequency = new LinkedHashMap<>();
       //Map<String, List<String>> bugFile = new LinkedHashMap<>();
       Map<String, Map<String, Integer>> bugFile = new LinkedHashMap<>();
       for (Commit currentC : allC) {
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
               //bugFile.put(currentC.Task, fileFrequency);
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
//               List<String> files = new ArrayList<>();
//                   Iterator<String> it1 = currentC.commitFiles.iterator();
//                   while (it1.hasNext()) {
//                       files.add(it1.next());
//                   }
//                   bugFile.put(currentC.Task, files);
//               }
//
//           for (Map.Entry current : bugFile.entrySet()){
//               List<String> files = (List<String>) current.getValue();
//               if (currentC.Task.equals(current)) {
//                   Iterator<String> it1 = currentC.commitFiles.iterator();
//                   while (it1.hasNext()) {
//                       files.add(it1.next());
//                   }
//                   bugFile.put(currentC.Task, files);
//               }
//           }
//


//           if (bugFile.isEmpty()) {
//               if (TaskInital.equals("B")) {
//                   List<String> files = new ArrayList<>();
//                   Iterator<String> it1 = currentC.commitFiles.iterator();
//                   while (it1.hasNext()) {
//                       files.add(it1.next());
//                   }
//                   bugFile.put(currentC.Task, files);
//               }
//           } else {
//               for (Map.Entry current : bugFile.entrySet()) {
//                   List<String> files = (List<String>) current.getValue();
//                   if (currentC.Task.equals(current)) {
//                       Iterator<String> it1 = currentC.commitFiles.iterator();
//                       while (it1.hasNext()) {
//                           files.add(it1.next());
//                       }
//                       bugFile.put(currentC.Task, files);
//                   }
//               }
//           }

       }
       System.out.println(bugFile);
       System.out.println(bugFiles);
       return bugFiles;

    }

    Set<String> broadFeatures(int threshold){
        Set<String> broadFeatures = new LinkedHashSet<>();
        Map<String, Set<String>> file = new LinkedHashMap<>();
        for (Commit currentC : allC) {
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
                //Set<String> currentFiles = currentValue;
                currentValue.retainAll(currentcomponent);
                if(currentValue.size()>0){
                    count++;
                }
            }
            if(count==threshold){
                broadFeatures.add(currentmap.getKey());
            }
//            for(Set<String> currentF: file.values()){
//                currentF.retainAll(currentcomponent);
//                if(currentF.size()>0){
//                    count++;
//                }
//            }
        }
        System.out.println(file);
        System.out.println(broadFeatures);
        return broadFeatures;
    }

    Set<String>  experts (int threshold){
        Set<String> experts = new LinkedHashSet<>();
        Map<String, Set<String>> file = new LinkedHashMap<>();
        for (Commit currentC : allC) {
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
//            for(Set<String> currentF: file.values()){
//                currentF.retainAll(currentcomponent);
//                if(currentF.size()>0){
//                    count++;
//                }
//            }
        }
        System.out.println(file);

        System.out.println(experts);
        return experts;

    }

    List<String> busyClasses (int limit){
        List<String> busyClasses = new ArrayList<>();

        Map<String, Integer> fileFrequency = new HashMap<>();
        for (Commit currentC : allC) {
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
        List<Integer> valuesH = new ArrayList<>();

//        for(Map.Entry<String, Integer> entry: sortedFiles.entrySet()) {
//            while (count < limit) {
//                valuesH.add(entry.getValue());
//                count++;
//            }
//        }
        Iterator<Map.Entry<String,Integer>> iterator = sortedFiles.entrySet().iterator();
        while (iterator.hasNext() && count<limit) {
            Integer currentFile = iterator.next().getValue();
            valuesH.add(currentFile);
            count++;
//            if(count==limit-1){
//                currentFile.equals(iterator.next().getKey()){
//                    valuesH.add(Integer.valueOf(currentFile));
//                }
//            }
        }
        System.out.println(valuesH);
//        while(count<limit){
//            for(Integer currentValue: sortedFiles.values()){
//                valuesH.add(currentValue);
//                count++;
//            }
//            for(Map.Entry<String, Integer> entry: sortedFiles.entrySet()) {
//                valuesH.add(entry.getValue());
//                count++;
//            }


//        for(Map.Entry<String, Integer> entry: sortedFiles.entrySet()) {
//            String currentFile = entry.getKey();
//            Integer fileCount = entry.getValue();
//            for(Integer currentValue: valuesH){
//                if(currentValue==fileCount){
//                    busyClasses.add(currentFile);
//                }
//            }
//        }

//        for(Integer currentValue: valuesH){
//            for(Map.Entry<String, Integer> entry: sortedFiles.entrySet()){
//                String currentFile = entry.getKey();
//                Integer fileCount = entry.getValue();
//                if(currentValue == fileCount){
//                    busyClasses.add(currentFile);
//                }
//                if(busyClasses.size() == limit){
//                    busyClasses.get(limit-1).equals(entry.getKey());
//                    busyClasses.add(currentFile);
//                }
//
//            }
//        }

        for(Integer currentValue: valuesH) {
            String currentFile = null;
            //sortedFiles.containsValue(currentValue);
            for (Map.Entry<String, Integer> entry : sortedFiles.entrySet()) {
                if (Objects.equals(currentValue, entry.getValue())) {
                    currentFile = entry.getKey();
                    busyClasses.add(currentFile);
                }

            }
        }


//            Iterator<Map.Entry<String , Integer>> currentFile = sortedFiles.entrySet().iterator();
//            while(currentFile.hasNext()){
//                Map.Entry map = currentFile.next();
//                busyClasses.add(map.getKey(currentValue));
//            }
//        }

//        for(Integer currentValue: valuesH){
//            for(String key : getKeys(fileFrequency,currentValue))
//            String currentFile = fileFrequency.getKeys;
//            busyClasses.add(fileFrequency.get)
//        }




        System.out.println(fileFrequency);
        System.out.println(sortedFiles);
        System.out.println(busyClasses);

        return busyClasses;


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
