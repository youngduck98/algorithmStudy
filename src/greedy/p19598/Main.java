package greedy.p19598;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Meeting{
    int start;
    int end;

    public Meeting(){
        this.start = 0;
        this.end = 0;
    }
    public Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }
}

class MeetEndComparator implements Comparator<Meeting> {
    @Override
    public int compare(Meeting m1, Meeting m2){
        return m1.end - m2.end;
    }
}

class MeetStartComparator implements Comparator<Meeting> {
    @Override
    public int compare(Meeting m1, Meeting m2){
        if(m1.start == m2.start)
            return m1.end - m2.end;
        return m1.start - m2.start;
    }
}
class Solution{
    PriorityQueue<Meeting> que = new PriorityQueue<>(new MeetEndComparator());

    public int sol(Meeting[] meetings){
        Arrays.sort(meetings, new MeetStartComparator());
        for(Meeting meeting: meetings){
            insert(meeting);
        }
        return que.size();
    }

    public void insert(Meeting meeting){
        if(que.isEmpty()) {
            que.add(meeting);
            return;
        }

        if(que.peek().end <= meeting.start){
            que.poll();
        }
        que.add(meeting);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[size];
        for(int i=0;i<size;i++){
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            meetings[i] = new Meeting(inputs[0], inputs[1]);
        }
        Solution sol = new Solution();
        System.out.println(sol.sol(meetings));
    }
}
