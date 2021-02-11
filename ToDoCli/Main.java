import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
  private Set<ToDo> td;
  private String[] menuOptions, tskCreate;
  private BufferedReader br;
  {
    td = new HashSet<ToDo>();
    menuOptions = new String[]{"[1] Add ToDo",
                                "[2] Print ToDos",
                                "[3] Finish ToDo",
                                "[4] Add Task to ToDo",
                                "[5] Finish Task"};
    tskCreate = new String[]{"Name: ",
                            "Predicted Date YYYY-MM-DD: ",
                            "Importance:%n\t[1]Low%n\t[2]Medium%n[3]\tHigh "};
    br = new BufferedReader(new InputStreamReader(System.in));
  }
  public static void main(String... args){
    Main m = new Main();
    m.createLoop();
  }
  private void createLoop(){
    String userChoiceS = "";
    int userChoice = 0;
    printMenu();
    while(!(userChoiceS = getInput()).isEmpty()){
      userChoice = parseUserInt(userChoiceS);
      if(userChoice!=0) chooseOption(userChoice);
      printMenu();
    }
  }
  private void createTodo(){
    String ui = "";
    Boolean isValid = false;
    for(String s : tskCreate){
      System.out.printf(s);
      do{
        ui = getInput();
        isValid = validateInput(ui);
      }while(!isValid);
    }
  }
  private void finishTodo(){
    //TODO: Create body
    System.out.println("Test2");
  }
  private void chooseTodo(int c){
    //TODO: Create body
    System.out.println("Test3");
  }
  private void createTask(){
    //TODO: Create body
  }
  private void finishTask(){
    //TODO: Create body
  }
  private void chooseOption(int choice){
    switch(choice){
      case 1:
        createTodo();
        break;
      case 2:
        printToDos();
        break;
      case 3:
      case 4:
        chooseTodo(choice);
        break;
      case 5:
        finishTask();
        break;
    }
  }
  private void printMenu(){
    System.out.println("What do you want to do?");
    for(String s : menuOptions) System.out.println("\t"+s);
  }
  private void printToDos(){
    for(ToDo todo : td){
      System.out.println(todo.toString());
      for(Task task : todo.getTaskList()){
        System.out.println(task.toString());
      }
    }
  }
  private Integer parseUserInt(String s){
    try{
      return Integer.parseInt(s);
    } catch(NumberFormatException e){
      System.out.println("Please enter a digit specified in []");
      return 0;
    }
  }
  private String getInput(){
    try{
      return br.readLine();
    }catch(IOException e){
      System.out.println("Sorry, there must have been an error");
      return "";
    }
  }
  private Boolean validateInput(String s){
    if(s.isEmpty()) return false;
    Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    Matcher m = p.matcher(s);
    return m.matches();
  }
}
