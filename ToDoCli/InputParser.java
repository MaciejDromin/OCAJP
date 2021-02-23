import java.lang.AssertionError;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputParser{
  private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private InputParser(){
    throw new AssertionError();
  }
  public static LocalDate stringToDate(String s){
    return LocalDate.parse(s, dtf);
  }
  public static Importance intToImportance(Integer i){
    Importance imp = null;
    switch(i){
      case 1:
        imp = Importance.LOW;
        break;
      case 2:
        imp = Importance.MEDIUM;
        break;
      case 3:
        imp = Importance.HIGH;
        break;
    }
    return imp;
  }
  public static Importance stringToImportance(String s){
    return Importance.valueOf(s);
  }
  public static Integer parseUserInt(String s){
    try{
      return Integer.parseInt(s);
    } catch(NumberFormatException e){
      System.out.println("Please enter a digit specified in []");
      return 0;
    }
  }
}
