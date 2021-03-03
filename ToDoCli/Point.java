public class Point implements Cloneable{
  private int count;
  private int wage;
  public Point(int count, int wage){
    this.count = count;
    this.wage = wage;
  }
  public int getCount(){
    return this.count;
  }
  public int getWage(){
    return this.wage;
  }
  private void incrementWage(int wage){
    this.wage += wage;
    count++;
  }
  public String toString(){
    return this.count + " " + this.wage;
  }
  public Point clone(int wage){
    try{
      Point p = (Point)super.clone();
      p.incrementWage(wage);
      return p;
    } catch(CloneNotSupportedException e){
      throw new AssertionError();
    }
  }
}
