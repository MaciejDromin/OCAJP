import java.time.LocalDate;

public class Task implements TasksInterface{
  private Boolean completed;
  private String name;
  private LocalDate predictedDate;
  private LocalDate complitionDate;
  private Importance importance;

  public Task(){}

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
}
