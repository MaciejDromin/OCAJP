import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ToDo extends Task{
  private List<Task> tl;
  {
    tl = new ArrayList<Task>();
  }
  public ToDo(Boolean completed, String name, LocalDate predictedDate,
              LocalDate complitionDate, Importance importance){
    super(completed, name, predictedDate, complitionDate, importance);
  }
  public ToDo(){super();}
  public void addTask(Task t){
    this.tl.add(t);
  }
  public List<Task> getTaskList(){
    return this.tl;
  }
  public void setTaskList(List<Task> tl){
    this.tl = tl;
  }
}
