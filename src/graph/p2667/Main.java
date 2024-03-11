package graph.p2667;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution{
    int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public Solution(int[][] map){
        this.map = map;
    }

    public int DFS(int r, int c){
        if(map[r][c] == 0)
            return 0;

        map[r][c] = 0;
        int count = 1;
        for(int i=0;i<4;i++){
            int newR = r + dr[i];
            int newC = c + dc[i];
            boolean avail = newR >= 0 && newR < map.length
                    && newC >= 0 && newC < map.length && map[newR][newC] == 1;
            if(avail){
                count += DFS(newR, newC);
            }
        }
        return count;
    }

    public List<Integer> cal(){
        List<Integer> answer = new ArrayList<>();
        for(int r=0;r< map.length;r++){
            for(int c=0;c<map[0].length;c++){
                if(map[r][c] == 1){
                    answer.add(DFS(r, c));
                }
            }
        }
        Collections.sort(answer);
        return answer;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        int[][] map = new int[size][size];

        for(int r=0;r<size;r++){
            String[] inputs = br.readLine().split("");
            for(int c=0;c<size;c++){
                map[r][c]= Integer.parseInt(inputs[c]);
            }
        }
        Solution sol = new Solution(map);
        List<Integer> answer = sol.cal();
        bw.write(answer.size() + "\n");
        for(int k: answer){
            bw.write(k + "\n");
        }
        bw.flush();
        bw.close();
    }
}
