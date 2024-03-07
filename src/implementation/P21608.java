package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class StudentInfo{
    int id;
    Set<Integer> friends;

    public StudentInfo(){
        id = 0;
    }

    public StudentInfo(int[] info){
        id = info[0];
        friends = new HashSet<>();
        for(int i=1;i<=4;i++){
            friends.add(info[i]);
        }
    }
}

class Pos implements Comparable<Pos>{
    int r;
    int c;

    public Pos(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Pos o) {
        if(this.r == o.r && this.c == o.c)
            return 0;
        if(this.r > o.r || (this.r == o.r && this.c > o.c))
            return 1;
        return -1;
    }
}

class GameMap{
    int[][] map;
    Map<Integer, StudentInfo> dict;
    int num;

    public GameMap(int size){
        map = new int[size][size];
        dict = new HashMap<>();
        num = 0;
    }

    public void setStudentToMap(StudentInfo info){
        if(map.length * map.length <= num)
            return;
        num++;
        Pos pos = checkOne(info);
        map[pos.r][pos.c] = info.id;
        dict.put(info.id, info);
    }

    public int checkFriends(int r, int c, StudentInfo info){
        int count = 0;
        if(r > 0 && info.friends.contains(map[r-1][c]))
            count++;
        if(r<map.length-1 && info.friends.contains(map[r+1][c]))
            count++;
        if(c > 0 && info.friends.contains(map[r][c-1]))
            count++;
        if(c<map.length-1 && info.friends.contains(map[r][c+1]))
            count++;
       return count;
    }

    public Pos checkOne(StudentInfo info){
        List<Pos> passOne = new ArrayList<>();
        int max = 0;

        for(int r=0;r<map.length;r++){
            for(int c=0;c<map[0].length;c++){
                if(map[r][c] != 0)
                    continue;
                int count = checkFriends(r, c, info);
                if(max < count) {
                    passOne.clear();
                    max = count;
                }
                if(max == count)
                    passOne.add(new Pos(r, c));
            }
        }

        if(passOne.size() == 1)
            return passOne.get(0);
        else {
            return checkTwo(passOne, info);
        }
    }

    public int checkEmpty(Pos pos){
        int count = 0;
        if(pos.r > 0 && map[pos.r-1][pos.c] == 0)
            count++;
        if(pos.r < map.length-1 && map[pos.r+1][pos.c] == 0)
            count++;
        if(pos.c > 0 && map[pos.r][pos.c-1] == 0)
            count++;
        if(pos.c < map.length-1 && map[pos.r][pos.c+1] == 0)
            count++;
        return count;
    }

    public Pos checkTwo(List<Pos> passOne, StudentInfo info){
        List<Pos> passTwo = new ArrayList<>();
        int max = 0;
        for(Pos pos: passOne){
            int count = checkEmpty(pos);
            if(count > max){
                passTwo.clear();
                max = count;
            }
            if(count == max){
                passTwo.add(pos);
            }
        }
        if(passTwo.size() == 1)
            return passTwo.get(0);
        else
            return checkThree(passTwo, info);
    }

    public Pos checkThree(List<Pos> passTwo, StudentInfo info){
        Collections.sort(passTwo);
        return passTwo.get(0);
    }

    public int calGood(){
        int ret = 0;
        for(int r=0;r<map.length;r++){
            for(int c=0;c<map.length;c++){
                int friends = checkFriends(r, c, dict.get(map[r][c]));
                if(friends == 0)
                    continue;
                ret += (int) Math.pow(10, friends-1);
            }
        }
        return ret;
    }
}
public class P21608 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        GameMap map = new GameMap(size);
        size = size * size;
        List<StudentInfo> list = new ArrayList<>();

        for(int index=0;index<size;index++){
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(new StudentInfo(inputs));
        }

        for(int i=0;i<list.size();i++){
            StudentInfo now = list.get(i);
            map.setStudentToMap(now);
        }

        System.out.println(map.calGood());
    }
}
