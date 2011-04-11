public class PhysicalUnitType implements Comparable<PhysicalUnitType>{
  private String name;
  private int range;
  private int maxHitpoints;
  private int defence;
  private int attack;
  private int movementPoints;
  private int manpower;
	
  public PhysicalUnitType(String name, int attack, int defence, int movementPoints, int maxHitpoints, int range, int manpower){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.movementPoints = movementPoints;
        this.maxHitpoints = maxHitpoints;
        this.range = range;
	this.manpower = manpower;  
  }

  public String getName(){
    return name;
  }

  public int getAttack(){
    return attack;
  }

  public int getDefence(){
    return defence;
  }

  public int getMovementPoints(){
    return movementPoints;
  }

  public int getMaxHitpoints(){
    return maxHitpoints;
  }

  public int getRange(){
    return range;
  }
  
  public int getManpower() {
	  return manpower;
  }

  /**
   * Used for comparing one unittype to another, 
   * to check if it's stronger than the other.
   *
   * @param other The other unittype.
   *
   * @return 
   */
  public int compareTo(PhysicalUnitType other){
    int attacking = this.attack - other.defence;
    int defending = other.attack - this.defence;
    return attacking - defending;
  }

  /**
   * If the unittype names are equal the unittypes are the same
   * this method tests that connection.
   *
   * @param other Another PhysicalUnitType to compare to.
   *
   * @return True if this name is equal to other's name
   */
  public boolean equals(Object other){
    if(other instanceof PhysicalUnitType){
      return this.name.equals(((PhysicalUnitType)other).name);
    }
    return false;
  }
}
