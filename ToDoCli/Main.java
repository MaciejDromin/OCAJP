import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;

public class Main{
  private List<ToDo> td;
  private final String[] menuOptions, tskCreate;
  private BufferedReader br;
  private Validator v;
  {
    td = new ArrayList<ToDo>();
    menuOptions = new String[]{"[1] Add ToDo",
                                "[2] Print ToDos",
                                "[3] Finish ToDo",
                                "[4] Add Task to ToDo",
                                "[5] Finish Task",
                                "[6] Remove ToDo",
                                "[7] Remove Task",
                                "[8] Statistics",
                                "[x] Exit"};
    tskCreate = new String[]{"Name: ",
                            "Predicted Date YYYY-MM-DD: ",
                            "Importance:%n\t[1]Low%n\t[2]Medium%n\t[3]High "};
    br = new BufferedReader(new InputStreamReader(System.in));
    v = new Validator();
    //fp = new FileParser();
    /*for(int i=0;i<5;i++){
      ToDo ntd = new ToDo();
      Task ttt = new Task();
      ntd.setName("Test");
      ntd.setPredictedDate(LocalDate.now());
      ntd.setImportance(Importance.LOW);
      for(int n=0;n<5;n++){
        ttt.setName("Test");
        ttt.setPredictedDate(LocalDate.now());
        ttt.setImportance(Importance.LOW);
        ntd.addTask(ttt);
      }
      td.add(ntd);
    }*/
  }
  public static void main(String... args){
    Main m = new Main();
    m.createLoop();
  }
  private void createLoop(){
    FileParser.openSession(td);
    String userChoiceS = "";
    int userChoice = 0;
    printMenu();
    while(!(userChoiceS = getInput()).isEmpty()){
      if(userChoiceS.equals("x")) break;
      userChoice = InputParser.parseUserInt(userChoiceS);
      if(userChoice!=0) chooseOption(userChoice);
      printMenu();
    }
    FileParser.saveSession(td);
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
              todo.setPredictedDate(InputParser.stringToDate(ui));
              break;
            case 3:
              todo.setImportance(InputParser.intToImportance(InputParser.parseUserInt(ui)));
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
    else td.get(c).setComplitionDate(InputParser.stringToDate(ui));
  }
  private void chooseTodo(int c){
    String ui = "";
    boolean isValid = false;
    int choosedTodo = 0;
    do{
      ui = getInput();
      isValid = v.validateChooseTask(ui, (td.size()));
      choosedTodo = InputParser.parseUserInt(ui);
    } while(!isValid);
    if(c==3) finishTodo(choosedTodo);
    else if(c==4) createTask(choosedTodo);
    else if(c==6) removeTodo(choosedTodo);
    else if(c==7){
      if(printTasks(td.get(choosedTodo))) chooseTask(td.get(choosedTodo), c);
    }
    else{
      if(printTasks(td.get(choosedTodo))) chooseTask(td.get(choosedTodo), c);
      else System.out.println("There are no Tasks in this Todo, add some.");
    }
  }
  private void chooseTask(ToDo iTodo, int c){
    String ui = "";
    boolean isValid = false;
    int choosedTask = 0;
    do{
      ui = getInput();
      isValid = v.validateChooseTask(ui, (iTodo.getTaskList().size()));
      choosedTask = InputParser.parseUserInt(ui);
    } while(!isValid);
    if(c==7) removeTask(iTodo, choosedTask);
    else finishTask(iTodo, choosedTask);
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
              task.setPredictedDate(InputParser.stringToDate(ui));
              break;
            case 3:
              task.setImportance(InputParser.intToImportance(InputParser.parseUserInt(ui)));
              break;
          }
          stage++;
        }
      }while(!isValid);
    }
    td.get(c).addTask(task);
  }
  private void finishTask(ToDo iTodo, int c){
    System.out.printf("When did you finished?%nToday: %n"+
                      "Date with correct format(YYYY-MM-DD): ");
    String ui = "";
    boolean isValid = false;
    do{
      ui = getInput();
      isValid = v.validateEndTask(ui);
    }while(!isValid);
    if(ui.equals("Today")) iTodo.getTaskList().get(c).setComplitionDate(LocalDate.now());
    else iTodo.getTaskList().get(c).setComplitionDate(InputParser.stringToDate(ui));
  }
  private void removeTodo(int c){
    td.remove(c);
  }
  private void removeTask(ToDo iTodo, int c){
    iTodo.getTaskList().remove(c);
  }
  private void chooseOption(int choice){
    switch(choice){
      case 1:
        createTodo();
        break;
      case 2:
        if(!printToDosWithTasks()) System.out.println("There are no todos, add some.");
        break;
      case 3:
      case 4:
          if(printToDos()) chooseTodo(choice);
          else System.out.println("There are no todos, add some.");
        break;
      case 5:
        if(printToDosWithTasks()) chooseTodo(choice);
        else System.out.println("There are no todos, add some.");
        break;
      case 6:
        if(printToDos()) chooseTodo(choice);
        else System.out.println("There are no todos, add some.");
        break;
      case 7:
        if(printToDosWithTasks()) chooseTodo(choice);
        else System.out.println("There are no todos, add some.");
        break;
      case 8:
        printStatistics();
        break;
    }
  }
  private void printMenu(){
    System.out.println("What do you want to do?");
    for(String s : menuOptions) System.out.println("\t"+s);
  }
  private boolean printToDosWithTasks(){
    if(td.isEmpty()) return false;
    for(ToDo todo : td){
      System.out.println("["+td.indexOf(todo)+"] "+todo.toString());
      for(Task task : todo.getTaskList()){
        System.out.println("\t"+task.toString());
      }
    }
    return true;
  }
  private boolean printToDos(){
    if(td.isEmpty()) return false;
    for(ToDo todo : td){
      System.out.println("["+td.indexOf(todo)+"] "+todo.toString());
    }
    return true;
  }
  private boolean printTasks(ToDo iT){
    List<Task> tl = iT.getTaskList();
    if(tl.isEmpty()) return false;
    for(Task t : tl){
      System.out.println("["+tl.indexOf(t)+"] "+t.toString());
    }
    return true;
  }
  private void printStatistics(){
    Statistics st = new Statistics(td);
    String formatForStatistics = "n of ToDos.: %d \t n of ToDos Completed: %d \t ToDos Completed Perc.: %, .2f%% %n" +
                                  "n of Tasks nr.: %d \t n of Tasks Completed: %d \t Tasks Completed Perc.: %, .2f%% %n";
    System.out.printf(formatForStatistics, st.getTodos(), st.getTodosCompleted(), st.getPercentage()*100,
                                            st.getTasks(), st.getTasksCompleted(), st.getTskPercentage()*100);
    System.out.println("------------------------");
    TreeMap<LocalDate, Point> temp = st.getTodosPerDay();
    TreeMap<LocalDate, Point> temp2 = st.getTodosComplPerDay();
    perDayLoop(temp, temp2, "ToDos", st);
    System.out.println("------------------------");
    temp = st.getTasksPerDay();
    temp2 = st.getTasksComplPerDay();
    perDayLoop(temp, temp2, "Tasks", st);
    st = null;
  }
  private void perDayLoop(TreeMap<LocalDate, Point> t1, TreeMap<LocalDate, Point> t2, String m, Statistics st){
    for(Map.Entry<LocalDate, Point> e : t1.entrySet()){
      Point p = e.getValue();
      System.out.printf("%1$td %1$tB,  %1$tY%n", e.getKey());
      System.out.printf("n of %s: %d \t n of %s Completed: %d \t Perc.: %, .2f%% %n", m, p.getCount(), m, t2.get(e.getKey())!=null?t2.get(e.getKey()).getCount():0, st.calcDayPerc(p, t2.get(e.getKey())!=null?t2.get(e.getKey()):0)*100);
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
}
