public class Tank{
  protected double armor;
  protected double hp;
  protected double damage;
  protected String name;
  {
    this.armor = 2.5;
    this.hp = 6*this.armor;
    this.damage = 10;
  }

  public Tank(){
  }

  public double getArmor(){
    return this.armor;
  }
  public void setArmor(double armor){
    this.armor = armor;
  }
  public double getHp(){
    return this.hp;
  }
  public void setHp(double hp){
    this.hp = hp;
  }
  public double getDamage(){
    return this.damage;
  }
  public void setDamage(double damage){
    this.damage = damage;
  }

  public void fireAt(Tank t){
    t.setHp(t.getHp()-this.damage);
  }
  public void throwSmoke(){
    System.out.println("Tank throws smoke bomb!");
  }
  public String getName(){
    return this.name;
  }
  public void setName(String name){
    this.name = name;
  }
  public String toString(){
    return this.name + ", " + this.armor + ", " + this.hp + ", " + this.damage;
  }
  public void makeNoise(){}
}
