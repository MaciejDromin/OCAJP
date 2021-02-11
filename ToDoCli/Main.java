import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
  private Set<ToDo> td;
  private String[] menuOptions, tskCreate;
  {
    td = new HashSet<ToDo>();
    menuOptions = new String[]{"[1] Add ToDo",
                                "[2] Print ToDos",
                                "[3] Finish ToDo",
                                "[4] Add Task to ToDo",
                                "[5] Finish Task"};
    tdCreate = new String[]{"Name: ",
                            "PredictedDate: ",
                            "Importance: "};
  }
  public static void main(String... args){
    Main m = new Main();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    m.createLoop(br);
  }
  private void createLoop(BufferedReader br){
    String userChoiceS = "";
    int userChoice = 0;
    printMenu();
    try{
      while(!(userChoiceS = br.readLine()).isEmpty()){
        userChoice = Integer.valueOf(userChoiceS);
        chooseOption(userChoice);
        printMenu();
      }
    } catch(IOException e){}
  }
  private void createTodo(){
    //TODO: Create body
    System.out.println("Test1");
  }
  private void finishTodo(){
    //TODO: Create body
    System.out.println("Test2");
  }
  private void chooseTodo(){
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
        chooseTodo();
        break;
      case 4:
        chooseTodo();
        break;
      case 5:
        finishTask();
        break;
    }
  }
  private void printMenu(){
    System.out.println("What do you want to do?");
    for(String s : menuOptions) System.out.println(s);
  }
  private void printToDos(){
    for(ToDo todo : td){
      System.out.println(todo.toString());
      for(Task task : todo.getTaskList()){
        System.out.println(task.toString());
      }
    }
  }
}
