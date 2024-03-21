package lowestCommonAncestor.p3584;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution{
    Map<Integer, List<Integer>> cache;
    Map<Integer, Integer> depth;

    public Solution(Map<Integer, Integer> tree){
        cache = new HashMap<>();
        for(int k: tree.keySet()){
            cache.put(k, new ArrayList<>());
            cache.get(k).add(tree.get(k));
        }
    }
}
public class Main {
    public static void main(String[] args){

    }
}
