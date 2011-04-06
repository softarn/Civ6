public class PhysicalUnit{
  private static int count = 0;
  private int idNumber;
  private int hitpoints;
  private int currentMovementPoints;
  //private City city = null;

  private PhysicalUnitType type;

  public PhysicalUnit(PhysicalUnitType type){
    this.idNumber = ++count;
    this.hitpoints = type.getMaxHitpoints();
    this.currentMovementPoints = type.getMovementPoints();
    this.type = type;
  }

  public boolean isInCity(){
    return false;
  }

  public PhysicalUnitType getType(){
    return type;
  }

  public int getHitpoints(){
    return hitpoints;
  }

  public int getCurrentMovementPoints(){
    return currentMovementPoints;
  }

  public int getID(){
    return idNumber;
  }

  public boolean useMovementPoints(int points){
    return false;
  }
}
