import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

public class FileParser{
  FileParser(){}
  public boolean saveSession(List<ToDo> todoList){
    if(todoList.isEmpty()) return true;
    JSONObject main = new JSONObject();
    JSONArray todoArray = new JSONArray();
    for(ToDo td : todoList){
      JSONObject todo = new JSONObject(td.toMap());
      List<Task> taskList = td.getTaskList();
      //if(!taskList.isEmpty()){
        JSONArray taskArray = new JSONArray();
        for(Task tsk : taskList){
          taskArray.put(tsk.toMap());
        }
        todo.put("Tasks", taskArray);
      //}
      todoArray.put(todo);
    }
    main.put("Todos", todoArray);
    /*System.out.println(main.toString(4));*/
    try(PrintWriter pw = new PrintWriter("test.json")){
      //PrintWriter pw = new PrintWriter("test.json");
      pw.write(main.toString(4));

      pw.flush();
      //pw.close();
    }catch(FileNotFoundException e){
      return false;
    }
    return true;
  }
  public boolean openSession(List<ToDo> todoList){
    InputStream is = FileParser.class.getResourceAsStream("/test.json");
    if(is==null){
      return false;
    }
    JSONTokener tk = new JSONTokener(is);
    JSONObject obj = new JSONObject(tk);
    JSONArray tdArray = (JSONArray)obj.get("Todos");
    for(Object o : tdArray){
      ToDo todo = new ToDo();
      JSONObject tdObj = (JSONObject) o;
      todo.setName(tdObj.getString("name"));
      todo.setPredictedDate(InputParser.stringToDate(tdObj.getString("predictedDate")));
      todo.setImportance(InputParser.stringToImportance(tdObj.getString("importance")));
      todo.setComplitionDate(InputParser.stringToDate(tdObj.getString("complitionDate")));
      todo.setCompleted(Boolean.valueOf(tdObj.getString("isCompleted")));
      JSONArray tasksArray = (JSONArray)tdObj.get("Tasks");
      if(!tasksArray.isEmpty()){
        List<Task> tArray = new ArrayList<Task>();
        for(Object tO : tasksArray){
          Task tsk = new Task();
          JSONObject tskObj = (JSONObject) o;
          tsk.setName(tskObj.getString("name"));
          tsk.setPredictedDate(InputParser.stringToDate(tdObj.getString("predictedDate")));
          tsk.setImportance(InputParser.stringToImportance(tdObj.getString("importance")));
          tsk.setComplitionDate(InputParser.stringToDate(tdObj.getString("complitionDate")));
          tsk.setCompleted(Boolean.valueOf(tdObj.getString("isCompleted")));
          tArray.add(tsk);
        }
        todo.setTaskList(tArray);
      }
      todoList.add(todo);
    }
    return true;
  }
}
