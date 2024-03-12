package graph.p16918;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution{
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    List<int[][]> routine;
    int sizeR;
    int sizeC;

    public Solution(int[][] origin){
        routine = new ArrayList<>();
        sizeR = origin.length;
        sizeC = origin[0].length;
        int[][] map = new int[sizeR][sizeC];
        for(int r=0;r<sizeR;r++){
            Arrays.fill(map[r], 1);
        }
        routine.add(map);
        routine.add(origin);
        routine.add(new int[sizeR][sizeC]);
        routine.add(new int[sizeR][sizeC]);
    }

    public void boom(int[][] map, int r, int c){
        map[r][c] = 0;
        for(int i=0;i<4;i++){
            int newR = r+dr[i];
            int newC = c+dc[i];
            boolean avail = newR>=0 && newR < sizeR && newC>=0 && newC < sizeC;
            if(avail)
                map[newR][newC] = 0;
        }
    }

    public void cal(int k, int[][] origin){
        int[][] map = new int[sizeR][sizeC];
        for(int r=0;r<sizeR;r++){
            Arrays.fill(map[r], 1);
        }
        for(int r=0;r<sizeR;r++){
            for(int c=0;c<sizeC;c++){
                if(origin[r][c] == 1)
                    boom(map, r, c);
            }
        }
        routine.set(k, map);
    }

    public char convert(int k){
        if(k == 0)
            return '.';
        return 'O';
    }

    public char[][] convertMap(int[][] map){
        char[][] ret = new char[map.length][map[0].length];
        for(int r=0;r<sizeR;r++){
            for(int c=0;c<sizeC;c++){
                ret[r][c] = convert(map[r][c]);
            }
        }
        return ret;
    }

    public char[][] answer(int n){
        if(n == 1 || n == 0)
            return convertMap(routine.get(1));
        if(n % 2 == 0)
            return convertMap(routine.get(0));
        cal(2, routine.get(1));
        if(n % 4 == 3)
            return convertMap(routine.get(2));
        cal(3, routine.get(2));
        return convertMap(routine.get(3));
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int sizeR = Integer.parseInt(inputs[0]);
        int sizeC = Integer.parseInt(inputs[1]);
        int second = Integer.parseInt(inputs[2]);
        int[][] origin = new int[sizeR][sizeC];

        for (int r = 0; r < sizeR; r++) {
            inputs = br.readLine().split("");
            for (int c = 0; c < sizeC; c++) {
                if (inputs[c].equals("."))
                    origin[r][c] = 0;
                else
                    origin[r][c] = 1;
            }
        }

        Solution sol = new Solution(origin);
        char[][] ans = sol.answer(second);
        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans[0].length; c++) {
                bw.write(ans[r][c]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
