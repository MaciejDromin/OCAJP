import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileParser{
  FileParser(){}
  public boolean saveSession(List<ToDo> todoList){
    if(todoList.isEmpty()) return true;
    JSONObject main = new JSONObject();
    JSONArray todoArray = new JSONArray();
    for(ToDo td : todoList){
      JSONObject todo = new JSONObject(td.toMap());
      List<Task> taskList = td.getTaskList();
      if(!taskList.isEmpty()){
        JSONArray taskArray = new JSONArray();
        for(Task tsk : taskList){
          taskArray.put(tsk.toMap());
        }
        todo.put("Tasks", taskArray);
      }
      todoArray.put(todo);
    }
    main.put("Todos", todoArray);
    System.out.println(main.toString(4));
    try{
      PrintWriter pw = new PrintWriter("test.json");
      pw.write(main.toString(4));

      pw.flush();
      pw.close();
    }catch(FileNotFoundException e){
      return false;
    }

    return true;
  }
  public boolean openSession(){
    return true;
  }
}
