import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.time.format.DateTimeFormatter;

public class Task implements TasksInterface, Comparable<Task>{
  private Boolean completed;
  private String name;
  private LocalDate predictedDate;
  private LocalDate complitionDate;
  private Importance importance;

  public Task(){
    this.name = "";
    this.completed = false;
    this.predictedDate = LocalDate.now();
    this.complitionDate = LocalDate.now();
    this.importance = Importance.LOW;
  }

  public Task(String name, LocalDate predictedDate, Importance importance){
    this(false, name, predictedDate, LocalDate.now(), importance);
  }

  public Task(Boolean completed, String name, LocalDate predictedDate,
              LocalDate complitionDate, Importance importance){
    this.completed = completed;
    this.name = name;
    this.predictedDate = predictedDate;
    this.complitionDate = complitionDate;
    this.importance = importance;
  }

  public Boolean isCompleted(){
    return this.completed;
  }
  public void setCompleted(Boolean completed){
    this.completed = completed;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }
  public void setPredictedDate(LocalDate predictedDate){
    this.predictedDate = predictedDate;
  }
  public LocalDate getPredictedDate(){
    return this.predictedDate;
  }
  public void setComplitionDate(LocalDate complitionDate){
    this.complitionDate = complitionDate;
    this.completed = true;
  }
  public LocalDate getComplitionDate(){
    return this.complitionDate;
  }
  public void setImportance(Importance importance){
    this.importance = importance;
  }
  public Importance getImportance(){
    return this.importance;
  }
  public String toString(){
    return this.name + ", " + this.importance + ", " + this.predictedDate + ", " +
            this.complitionDate + ", " + this.completed;
  }

  public Map toMap(){
    Map<String, String> m = new TreeMap<String, String>();
    m.put("name", this.name);
    m.put("predictedDate", this.predictedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    m.put("importance", this.importance.name());
    m.put("complitionDate", this.complitionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    m.put("isCompleted", String.valueOf(this.completed));
    return m;
  }

  public int compareTo(Task o){
    int result = (this.name.equals(o.getName()))==true?0:-1;
    if(result == 0){
      result = (this.predictedDate.equals(o.getPredictedDate()))==true?0:-1;
    }
    if(result == 0){
      result = (o.getImportance()==this.importance)?0:-1;
    }
    return result;
  }
}
