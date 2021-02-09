import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Game{
  private static int nOfTanks;
  private static List<Tank> tanks = new ArrayList<Tank>();
  static{
    nOfTanks = 3;
  }
  public static void main(String[] args){
    Game g = new Game();
    g.createPlayers(g);
    g.gameLoop(g);
  }
  public static void gameLoop(Game g){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int choice=0;
    char c;
    while(g.tanks.size()>1){
      for(Tank t : tanks) System.out.println(t.toString());
      System.out.println("At what tank do you want to fire?");
      try{
        choice = Integer.parseInt(br.readLine());
      } catch(IOException e){
        System.out.println(e);
      }
      g.tanks.get(0).fireAt(g.tanks.get(choice));
      if(g.tanks.get(choice).getHp()<=0){
        g.tanks.remove(choice);
      }
    }
  }
  public static void createPlayers(Game g){
    for(int i=0;i<nOfTanks;i++){
      g.tanks.add(new Pantera(2.8,7.8,16.1,"Pantera "+i));
      g.tanks.get(i).makeNoise();
    }
  }
}
