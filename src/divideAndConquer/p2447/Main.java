package divideAndConquer.p2447;

import java.io.*;

class Solution{
    boolean[][] preDraw;
    int size;

    public Solution(int size){
        this.size = size;
        preDraw = new boolean[size][size];
        drawCheck(size, 0, 0);
    }

    public void draw(BufferedWriter bw) throws IOException{
        for(int r=0;r<size;r++){
            for(int c=0;c<size;c++){
                if(preDraw[r][c])
                    bw.write("*");
                else
                    bw.write(" ");
            }
            bw.write("\n");
        }
    }

    public void drawCheck(int size, int startR, int startC){
        if(size == 3){
            for(int r=0;r<3;r++){
                for(int c=0;c<3;c++){
                    if(r==1 && c==1)
                        continue;
                    preDraw[startR + r][startC + c] = true;
                }
            }
            return;
        }

        int corrValue = size/3;

        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                if(r==1 && c==1)
                    continue;
                drawCheck(size/3, startR + corrValue*r,
                        startC + corrValue*c);
            }
        }
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Solution sol = new Solution(size);
        sol.draw(bw);
        bw.flush();
        bw.close();
    }
}
