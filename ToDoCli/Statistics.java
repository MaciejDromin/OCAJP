import java.util.List;
import java.util.TreeMap;
import java.util.Objects;
import java.time.LocalDate;
import java.util.ArrayList;

public class Statistics{
  private final List<ToDo> tdl;
  private int todosCompleted;
  private TreeMap<ToDo, Integer> tasksCompleted;
  private int todos;
  private int tasks;
  private int sumTasks;
  private TreeMap<LocalDate, Point> todosComplPerDay;
  private TreeMap<LocalDate, Point> todosPerDay;
  private TreeMap<LocalDate, Point> tasksComplPerDay;
  private TreeMap<LocalDate, Point> tasksPerDay;
  private TreeMap<LocalDate, Double> dayPercentage;
  private double percentage, tskPercentage;

  public Statistics(List<ToDo> tdl){
    this.tdl = Objects.requireNonNull(tdl);
    calcTodosAndTasksCompleted();
  }
  {
    tasksCompleted = new TreeMap<ToDo, Integer>();
    dayPercentage = new TreeMap<LocalDate, Double>();
    todosComplPerDay = new TreeMap<LocalDate, Point>();
    tasksComplPerDay = new TreeMap<LocalDate, Point>();
    todosPerDay = new TreeMap<LocalDate, Point>();
    tasksPerDay = new TreeMap<LocalDate, Point>();
  }
  private void calcTodosAndTasksCompleted(){
    int sumForTasks = 0;
    int[] sumsTd, sumsTsk, sumsTdCompl, sumsTskCompl;
    sumsTd = new int[3];
    sumsTsk = new int[3];
    sumsTdCompl = new int[3];
    sumsTskCompl = new int[3];
    for(ToDo otd : tdl){
      if(otd.isCompleted()){
          todosCompleted++;
      }
      calcDayCompletion(todosPerDay, todosComplPerDay, otd);
      todos++;
      calcTasksCompleted(otd, sumsTsk, sumsTskCompl);
      addSums(otd, sumsTd, sumsTdCompl);
    }
    calcDayPercentage();
    calcAllPercentage(sumsTd, sumsTsk, sumsTdCompl, sumsTskCompl);
  }

  private void calcDayCompletion(TreeMap<LocalDate, Point> inProgress,
                                  TreeMap<LocalDate, Point> completed,
                                  Task t){
    if(!inProgress.containsKey(t.getPredictedDate())){
      if(t.isCompleted()){
        if(!completed.containsKey(t.getComplitionDate())){
          if(t.getPredictedDate().equals(t.getComplitionDate())){
            completed.put(t.getComplitionDate(), new Point(1, calcWage(t)));
          }
        } else {
          if(t.getPredictedDate().equals(t.getComplitionDate())) completed.replace(t.getComplitionDate(), completed.get(t.getComplitionDate()).clone(calcWage(t)));
        }
      }
      inProgress.put(t.getPredictedDate(), new Point(1, calcWage(t)));
    } else {
      if(t.isCompleted()){
        if(!completed.containsKey(t.getComplitionDate())){
          if(t.getPredictedDate().equals(t.getComplitionDate())){
            completed.put(t.getComplitionDate(), new Point(1, calcWage(t)));
          }
        } else {
          if(t.getPredictedDate().equals(t.getComplitionDate())) completed.replace(t.getComplitionDate(), completed.get(t.getComplitionDate()).clone(calcWage(t)));
        }
      }
      inProgress.replace(t.getPredictedDate(), inProgress.get(t.getPredictedDate()).clone(calcWage(t)));
    }
  }

  private void calcDayPercentage(){

  }

  private void calcAllPercentage(int sumsTd[], int sumsTsk[], int sumsTdCompl[], int sumsTskCompl[]){
    percentage = ((double)(sumsTdCompl[0]+sumsTdCompl[1]+sumsTdCompl[2]))/(sumsTd[0]+sumsTd[1]+sumsTd[2]);
    tskPercentage = ((double)(sumsTskCompl[0]+sumsTskCompl[1]+sumsTskCompl[2]))/(sumsTsk[0]+sumsTsk[1]+sumsTsk[2]);
  }

  private void calcTasksCompleted(ToDo otd, int sums[], int sumsCompleted[]){
    int sumForTasks = 0;
    for(Task tsk : otd.getTaskList()){
      if(tsk.isCompleted()){
          sumForTasks++;
          sumTasks++;
      }
      calcDayCompletion(tasksPerDay, tasksComplPerDay, tsk);
      tasks++;
      addSums(tsk, sums, sumsCompleted);
    }
    tasksCompleted.put(otd, sumForTasks);
    sumForTasks = 0;
  }

  private void addSums(Task tsk, int sums[], int sumsCompleted[]){
    switch(tsk.getImportance()){
      case LOW:
        if(tsk.isCompleted()) sumsCompleted[0]++;
        sums[0]++;
        break;
      case MEDIUM:
        if(tsk.isCompleted()) sumsCompleted[1]+=3;
        sums[1]+=3;
        break;
      case HIGH:
        if(tsk.isCompleted()) sumsCompleted[2]+=5;
        sums[2]+=5;
        break;
    }
  }

  private int calcWage(Task tsk){
    switch(tsk.getImportance()){
      case LOW:
        return 1;
      case MEDIUM:
        return 3;
      case HIGH:
        return 5;
    }
    return 0;
  }

  public double calcDayPerc(Point p1, Object o){
    if(!(o instanceof Point)) return 0.0;
    Point p2 = (Point)o;
    return (double)p2.getWage()/p1.getWage();
  }

  public int getTodosCompleted(){
    return this.todosCompleted;
  }
  public int getTasksCompletedPerTodo(ToDo td){
    return this.tasksCompleted.get(td);
  }
  public int getTasksCompleted(){
    return this.sumTasks;
  }
  public int getTodos(){
    return this.todos;
  }
  public int getTasks(){
    return this.tasks;
  }
  public double getDayPercentage(LocalDate ld){
    return this.dayPercentage.get(ld);
  }
  public double getPercentage(){
    return this.percentage;
  }
  public double getTskPercentage(){
    return this.tskPercentage;
  }
  public TreeMap<LocalDate, Point> getTodosComplPerDay(){
    return this.todosComplPerDay;
  }
  public TreeMap<LocalDate, Point> getTasksComplPerDay(){
      return this.tasksComplPerDay;
  }
  public TreeMap<LocalDate, Point> getTodosPerDay(){
    return this.todosPerDay;
  }
  public TreeMap<LocalDate, Point> getTasksPerDay(){
    return this.tasksPerDay;
  }
}
