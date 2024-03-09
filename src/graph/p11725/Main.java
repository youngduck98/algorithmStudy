package graph.p11725;

import java.util.*;
import java.io.*;
import java.lang.*;



class Solution{
    Map<Integer, List<Integer>> graph;
    int[] answer;

    public Solution(int size){
        graph = new HashMap<>();
        for(int i=1;i<=size;i++){
            graph.put(i, new ArrayList<>());
        }
        answer = new int[size+1];
    }

    public void addLink(int a, int b){
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    public void calAnswer(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        while(!list.isEmpty()){
            int now = list.removeFirst();
            for(int k: graph.get(now)){
                if(answer[k] == 0){
                    answer[k] = now;
                    list.add(k);
                }
            }
        }
    }

    public void printAnswer(BufferedWriter bw) throws IOException {
        for(int i=2;i<answer.length;i++){
            bw.write(answer[i] + "\n");
        }
    }
}
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());
        Solution sol = new Solution(size);
        for(int i=0;i<size-1;i++){
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sol.addLink(inputs[0], inputs[1]);
        }
        sol.calAnswer();
        sol.printAnswer(bw);
        bw.flush();
        bw.close();
    }
}
