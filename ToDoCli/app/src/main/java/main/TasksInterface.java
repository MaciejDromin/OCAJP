import java.time.LocalDate;

public interface TasksInterface{
  public Boolean isCompleted();
  public void setName(String name);
  public String getName();
  public void setPredictedDate(LocalDate predictedDate);
  public LocalDate getPredictedDate();
  public void setComplitionDate(LocalDate complitionDate);
  public LocalDate getComplitionDate();
  public void setImportance(Importance importance);
  public Importance getImportance();
  public String toString();
}
