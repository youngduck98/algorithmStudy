package graph.p16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Pos{
    int r;
    int c;
    public Pos(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pos pos) {
            return this.r == pos.r && this.c == pos.c;
        }
        return false;
    }
}

class Solution{
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    int[][] map;
    int[][] visited;
    int min;
    int max;

    public Solution(int[][] map, int min, int max) {
        this.map = map;
        this.visited = new int[map.length][map[0].length];
        this.min = min;
        this.max = max;
    }

    public boolean inRange(int a, int b){
        int value = Math.abs(a - b);
        return value >= min && value <= max;
    }

    public boolean inMap(int r, int c){
        return r>=0 && r<map.length && c>=0 && c<map[0].length;
    }

    public void BFS(int row, int col, int uid){
        LinkedList<Pos> que = new LinkedList<>();

        visited[row][col] = uid;
        que.add(new Pos(row, col));

        while (!que.isEmpty()){
            Pos nowPos = que.removeFirst();
            for(int i=0;i<4;i++){
                int newR = nowPos.r + dr[i];
                int newC = nowPos.c + dc[i];
                if(inMap(newR, newC) && visited[newR][newC] == 0
                        && inRange(map[nowPos.r][nowPos.c], map[newR][newC])){
                    visited[newR][newC] = uid;
                    que.add(new Pos(newR, newC));
                }
            }
        }
    }

    public int sol(){
        boolean cont = true;
        int uid;
        int count = 0;
        while(cont) {
            cont = false;
            for(int r=0;r<map.length;r++){
                Arrays.fill(visited[r], 0);
            }
            uid = 1;
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map[0].length; c++) {
                    if (visited[r][c] != 0)
                        continue;
                    BFS(r, c, uid++);
                }
            }
            int[] sums = new int[uid];
            int[] counts = new int[uid];

            for(int r=0;r<map.length;r++){
                for(int c=0;c<map[0].length;c++){
                    sums[visited[r][c]] += map[r][c];
                    counts[visited[r][c]]++;
                    if(sums[visited[r][c]]/counts[visited[r][c]] != map[r][c])
                        cont = true;
                }
            }

            for(int r=0;r<map.length;r++){
                for(int c=0;c<map[0].length;c++){
                    map[r][c] = sums[visited[r][c]]/counts[visited[r][c]];
                }
            }

            if(cont)
                count++;
        }
        return count;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine()
                .split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = inputs[0];
        int min = inputs[1];
        int max = inputs[2];
        int[][] map = new int[size][size];

        for(int r=0;r<size;r++){
            inputs = Arrays.stream(br.readLine()
                    .split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int c=0;c<size;c++){
                map[r][c] = inputs[c];
            }
        }

        Solution sol = new Solution(map, min, max);
        System.out.println(sol.sol());
    }
}
