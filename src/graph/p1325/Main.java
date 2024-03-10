package graph.p1325;

import java.io.*;
import java.util.*;

class Solution{
    Map<Integer, Set<Integer>> dict;
    int nodeSize;
    boolean[] visited;
    int[] arr;

    public Solution(Map<Integer, Set<Integer>> dict, int nodeSize){
        this.dict = dict;
        this.nodeSize = nodeSize;
        this.visited = new boolean[nodeSize+1];
        this.arr = new int[nodeSize+1];
    }

    public void calPart(int node){
        if(visited[node])
            return;
        visited[node] = true;
        arr[node]++;
        for(int linked: dict.get(node)){
            calPart(linked);
        }
    }

    public List<Integer> calAnswer(){
        int max = 0;
        List<Integer> maxArr = new ArrayList<>();
        for(int i=1;i<=nodeSize;i++){
            visited = new boolean[nodeSize + 1];
            calPart(i);
        }
        for(int i=1;i<=nodeSize;i++){
            if(max < arr[i])
                max = arr[i];
        }
        for(int i=1;i<=nodeSize;i++){
            if(max == arr[i])
                maxArr.add(i);
        }

        return maxArr;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int nodeSize = inputs[0];
        int linkSize = inputs[1];

        Map<Integer, Set<Integer>> dict = new HashMap<>();
        for(int i=1;i<=nodeSize;i++) {
            dict.put(i, new HashSet<>());
        }

        for(int i=0;i<linkSize;i++){
            inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            dict.get(inputs[0]).add(inputs[1]);
        }

        Solution sol = new Solution(dict, nodeSize);
        for(int i: sol.calAnswer()){
            bw.write(i + " ");
        }
        bw.flush();
        bw.close();
    }
}
