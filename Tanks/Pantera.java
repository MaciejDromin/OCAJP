public class Pantera extends Tank{

  public Pantera(double armor, double hp, double damage, String name){
    this.armor = armor;
    this.hp = hp*this.armor;
    this.damage = damage;
    this.name = name;
  }

  public void makeNoise(){
    System.out.println("Prtrtrtrt");
  }
}
