import java.util.List;
import java.util.TreeMap;
import java.util.Objects;
import java.time.LocalDate;

public class Statistics{
  private final List<ToDo> tdl;
  private int todosCompleted;
  private TreeMap<ToDo, Integer> tasksCompleted;
  private TreeMap<LocalDate, Double> dayPercentage;
  public Statistics(List<ToDo> tdl){
    this.tdl = Objects.requireNonNull(tdl);
    calcTodosAndTasksCompleted();
  }
  {
    tasksCompleted = new TreeMap<ToDo, Integer>();
    dayPercentage = new TreeMap<LocalDate, Double>();
  }
  private void calcTodosAndTasksCompleted(){
    int sumForTasks = 0;
    int sums[] = new int[3];
    int sumsCompleted[] = new int[3];
    for(ToDo otd : this.tdl){
      if(otd.isCompleted()) this.todosCompleted++;
      calcTasksCompleted(otd, sums, sumsCompleted);
      addSums(otd, sums, sumsCompleted);
      calcDayPercentage();
    }
  }

  private void calcDayPercentage(){

  }

  private void calcTasksCompleted(ToDo otd, int sums[], int sumsCompleted[]){
    int sumForTasks = 0;
    for(Task tsk : otd.getTaskList()){
      if(tsk.isCompleted()) sumForTasks++;
      addSums(tsk, sums, sumsCompleted);
    }
    this.tasksCompleted.put(otd, sumForTasks);
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

  public int getTodosCompleted(){
    return this.todosCompleted;
  }
  public int getTasksCompleted(ToDo td){
    return this.tasksCompleted.get(td);
  }
  public double getDayPercentage(LocalDate ld){
    return this.dayPercentage.get(ld);
  }
}
