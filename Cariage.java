import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;

interface ArrayFunctions{
  int runMap(TreeMap<Integer, ArrayList<Integer>> map);
}

public class Cariage{
  public static void main(String[] args){
    TreeMap<Integer, ArrayList<Integer>> m = new TreeMap<Integer, ArrayList<Integer>>();
    ArrayList<Integer> a1 = new ArrayList<Integer>();
    ArrayList<Integer> a2 = new ArrayList<Integer>();
    ArrayList<Integer> a3 = new ArrayList<Integer>();
    a1.addAll(Arrays.asList(3,5,4,2));
    a2.addAll(Arrays.asList(50, 20, 80, 90, 100, 60, 30, 50, 80, 60));
    a3.addAll(Arrays.asList(35, 23, 40, 21, 38));
    m.put(20, a1);
    m.put(1000, a2);
    m.put(200, a3);
    m.forEach( (k,v) -> {
      int r = k/v.size();
      int ret = calcAv(v, r);
      System.out.println(ret);
    });
  }
  public static int calcAv(ArrayList<Integer> a, int s){
    for(int v : a){
      if(v<=s/2){
        return a.indexOf(v);
      }
    }
    return -1;
  }
}
