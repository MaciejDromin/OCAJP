import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;

public class ToDo extends Task{
  private Set<Task> tl;
  {
    tl = new HashSet<Task>();
  }
  public ToDo(Boolean completed, String name, LocalDate predictedDate,
              LocalDate complitionDate, Importance importance){
    super(completed, name, predictedDate, complitionDate, importance);
  }
  public ToDo(){super();}
  public void addTask(Task t){
    this.tl.add(t);
  }
  public Set<Task> getTaskList(){
    return this.tl;
  }
}
