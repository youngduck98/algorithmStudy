package greedy.p13164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution{
    int[] diff;

    public Solution(int[] statures){
        this.diff = new int[statures.length - 1];
        for(int i=1;i<statures.length;i++){
            diff[i-1] = statures[i] - statures[i-1];
        }
    }

    public int sol(int k){
        Arrays.sort(diff);
        int sum = 0;
        int size = diff.length - (k-1);
        for(int i=0;i<size;i++){
            sum += diff[i];
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = inputs[0];
        int k = inputs[1];
        int[] statures = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Solution sol = new Solution(statures);
        System.out.println(sol.sol(k));
    }
}
