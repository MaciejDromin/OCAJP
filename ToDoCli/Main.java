import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main{
  private List<ToDo> td;
  private String[] menuOptions, tskCreate;
  private BufferedReader br;
  private Validator v;
  private DateTimeFormatter dtf;
  {
    td = new ArrayList<ToDo>();
    menuOptions = new String[]{"[1] Add ToDo",
                                "[2] Print ToDos",
                                "[3] Finish ToDo",
                                "[4] Add Task to ToDo",
                                "[5] Finish Task"};
    tskCreate = new String[]{"Name: ",
                            "Predicted Date YYYY-MM-DD: ",
                            "Importance:%n\t[1]Low%n\t[2]Medium%n\t[3]High "};
    br = new BufferedReader(new InputStreamReader(System.in));
    v = new Validator();
    dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    boolean isValid = false;
    int stage = 1;
    ToDo todo = new ToDo();
    for(String s : tskCreate){
      do{
        System.out.printf(s);
        ui = getInput();
        isValid = v.validateAddTask(ui, stage);
        if(isValid){
          switch(stage){
            case 1:
              todo.setName(ui);
              break;
            case 2:
              todo.setPredictedDate(stringToDate(ui));
              break;
            case 3:
              todo.setImportance(intToImportance(parseUserInt(ui)));
              break;
          }
          stage++;
        }
      }while(!isValid);
    }
    td.add(todo);
  }
  private void finishTodo(int c){
    System.out.printf("When did you finished?%nToday: %n"+
                      "Date with correct format(YYYY-MM-DD): ");
    String ui = "";
    boolean isValid = false;
    do{
      ui = getInput();
      isValid = v.validateEndTask(ui);
    }while(!isValid);
    if(ui.equals("Today")) td.get(c).setComplitionDate(LocalDate.now());
    else td.get(c).setComplitionDate(stringToDate(ui));
  }
  private void chooseTodo(int c){
    String ui = "";
    boolean isValid = false;
    int choosedTodo = 0;
    do{
      ui = getInput();
      isValid = v.validateChooseTask(ui, (td.size()));
      choosedTodo = parseUserInt(ui);
    } while(!isValid);
    if(c==3) finishTodo(choosedTodo);
    else createTask(choosedTodo);
  }
  private void createTask(int c){
    String ui = "";
    boolean isValid = false;
    int stage = 1;
    Task task = new Task();
    for(String s : tskCreate){
      do{
        System.out.printf(s);
        ui = getInput();
        isValid = v.validateAddTask(ui, stage);
        if(isValid){
          switch(stage){
            case 1:
              task.setName(ui);
              break;
            case 2:
              task.setPredictedDate(stringToDate(ui));
              break;
            case 3:
              task.setImportance(intToImportance(parseUserInt(ui)));
              break;
          }
          stage++;
        }
      }while(!isValid);
    }
    td.get(c).addTask(task);
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
        printToDosWithTasks();
        break;
      case 3:
      case 4:
        printToDos();
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
  private void printToDosWithTasks(){
    for(ToDo todo : td){
      System.out.println(todo.toString());
      for(Task task : todo.getTaskList()){
        System.out.println("\t"+task.toString());
      }
    }
  }
  private void printToDos(){
    for(ToDo todo : td){
      System.out.println("["+td.indexOf(todo)+"] "+todo.toString());
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
  private LocalDate stringToDate(String s){
    return LocalDate.parse(s, dtf);
  }
  private Importance intToImportance(Integer i){
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
}
