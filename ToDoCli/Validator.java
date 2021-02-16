import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator{
  Pattern p;
  Matcher m;
  {

  }
  public Validator(){}

  public boolean validateAddTask(String iS, int stage){
    boolean ret = false;
    switch(stage){
      case 1:
        ret = isStringU35(iS);
        if(!ret) System.out.println("Your string was too long or too short");
        break;
      case 2:
        ret = isDate(iS);
        if(!ret) System.out.println("Your date has an invalid format");
        break;
      case 3:
        ret = isOneNumber(iS);
        if(!ret) System.out.println("You entered invalid digit");
        break;
    }
    return ret;
  }

  public boolean isDate(String s){
    if(s.isEmpty()) return false;
    p = Pattern.compile("^\\d{4}-[0-1]{1}\\d{1}-\\d{2}$");
    m = p.matcher(s);
    return m.matches();
  }

  public boolean isOneNumber(String s){
    if(s.isEmpty()) return false;
    p = Pattern.compile("^\\d{1}$");
    m = p.matcher(s);
    return m.matches();
  }

  public boolean isStringU35(String s){
    if(s.isEmpty()) return false;
    p = Pattern.compile("^.{1,35}$");
    m = p.matcher(s);
    return m.matches();
  }

  public boolean isImportanceE(String s){
    if(s.isEmpty()) return false;
    p = Pattern.compile("^[1-3]{1}$");
    m = p.matcher(s);
    return m.matches();
  }

  public boolean validateChooseTask(String ui, int size){
    if(ui.isEmpty()) return false;
    String patt = "^";
    for(int x=0;x<size;x++){
      patt = patt.concat("["+x+"]{1}+");
    }
    patt = patt.substring(0, patt.length()-1);
    patt = patt.concat("$");
    p = Pattern.compile(patt);
    m = p.matcher(ui);
    return m.matches();
  }

  public boolean validateEndTask(String ui){
    boolean ret = false;
    if(ui.isEmpty()) return ret;
    p = Pattern.compile("^Today$");
    m = p.matcher(ui);
    ret = m.matches();
    if(!ret) return isDate(ui);
    return ret;
  }
}
