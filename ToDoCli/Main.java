import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
  private Set<ToDo> td;
  {
    td = new HashSet<ToDo>();
  }
  public static void main(String... args){
    Main m = new Main();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    m.createLoop(br);
  }
  private void createLoop(BufferedReader br){
    try{
      while(!br.readLine().isEmpty()){
        printMenu();
      }
    } catch(IOException e){}
  }
  private void printMenu(){
    System.out.println("What do you want to do?");
    System.out.println("[1] Add ToDo");
    System.out.println("[2] Print ToDos");
    System.out.println("[3] Finish ToDo");
    System.out.println("[4] Add Task to ToDo");
    System.out.println("[5] Finish Task");
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
