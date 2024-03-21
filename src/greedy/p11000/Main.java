package greedy.p11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Course implements Comparable<Course>{
    int start;
    int end;
    public Course(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Course o) {
        if(this.start == o.start)
            return this.end - o.end;
        return this.start - o.start;
    }
}

class Solution{
    PriorityQueue<Integer> endPriorQue = new PriorityQueue<>();
    public int sol(List<Course> courses){
        Collections.sort(courses);
        for(Course course: courses){
            insert(course);
        }
        return endPriorQue.size();
    }

    public void insert(Course course){
        if(endPriorQue.isEmpty()){
            endPriorQue.add(course.end);
            return;
        }
        if(endPriorQue.peek() <= course.start)
            endPriorQue.poll();
        endPriorQue.offer(course.end);
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Course> courses = new ArrayList<>();
        int size = Integer.parseInt(br.readLine());
        for (int i = 0; i < size; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            courses.add(new Course(inputs[0], inputs[1]));
        }
        System.out.println(sol.sol(courses));
    }
}
