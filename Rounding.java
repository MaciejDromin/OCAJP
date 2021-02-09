import java.util.TreeMap;
import java.lang.Math;
import java.math.RoundingMode;

public class Rounding{
  public static void main(String[] args){
    TreeMap<String, Integer> ar = new TreeMap<String, Integer>();
    ar.put("test", 1000000);
    ar.put("test2", 1800000);
    ar.put("test3", 2300000);
    ar.put("test4", 1454000);
    ar.forEach((k,v) -> {
      int s = 1000000;
      double d = v/(double)s;
      int ret = (int) (long) Math.round(d)*s;
      ar.replace(k, ret);
      System.out.println(ar.get(k));
    });
  }
}
