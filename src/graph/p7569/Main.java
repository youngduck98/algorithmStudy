package graph.p7569;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

class Pos{
    int h;
    int r;
    int c;

    public Pos(int h, int r, int c){
        this.h = h;
        this.r = r;
        this.c = c;
    }
}

class Solution{
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int[] dr = {0, 1, 0, -1, 0, 0};
    static int[] dc = {1, 0, -1, 0, 0, 0};

    int[][][] map;
    int sizeR;
    int sizeC;
    int sizeH;

    public Solution(int[][][] map){
        this.map = map;
        sizeH = map.length;
        sizeR = map[0].length;
        sizeC = map[0][0].length;
    }

    private boolean validPosition(int h, int r, int c){
        return h>=0 && h<sizeH && r>=0 && r<sizeR && c>=0 && c<sizeC;
    }

    public int sol(){
        boolean[][][] visited = new boolean[sizeH][sizeR][sizeC];
        int[][][] count = new int[sizeH][sizeR][sizeC];
        LinkedList<Pos> que = new LinkedList<>();
        int appleCount = sizeR*sizeH*sizeC;

        for(int h=0;h<sizeH;h++){
            for(int r=0;r<sizeR;r++){
                for(int c=0;c<sizeC;c++){
                    if(map[h][r][c] == -1) {
                        visited[h][r][c] = true;
                        appleCount--;
                        continue;
                    }
                    if(map[h][r][c] == 1) {
                        que.addLast(new Pos(h, r, c));
                        visited[h][r][c] = true;
                    }
                }
            }
        }

        int maxCount = 0;

        while(!que.isEmpty()){
            Pos nowPos = que.removeFirst();
            appleCount--;
            for(int i=0;i<6;i++){
                int newH= nowPos.h + dh[i];
                int newR = nowPos.r + dr[i];
                int newC = nowPos.c + dc[i];
                if(!validPosition(newH, newR, newC) || visited[newH][newR][newC])
                    continue;
                visited[newH][newR][newC] = true;
                count[newH][newR][newC] = count[nowPos.h][nowPos.r][nowPos.c]+1;
                if(maxCount < count[newH][newR][newC]){
                    maxCount = count[newH][newR][newC];
                }
                que.add(new Pos(newH, newR, newC));
            }
        }

        if(appleCount > 0)
            return -1;
        return maxCount;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sizeC = inputs[0];
        int sizeR = inputs[1];
        int sizeH = inputs[2];

        int[][][] map = new int[sizeH][sizeR][sizeC];

        for(int h=0;h<sizeH;h++){
            for(int r=0;r<sizeR;r++){
                inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int c=0;c<sizeC;c++){
                    map[h][r][c] = inputs[c];
                }
            }
        }

        Solution sol = new Solution(map);
        System.out.println(sol.sol());
    }
}
