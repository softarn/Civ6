public class PhysicalUnitType implements Comparable<PhysicalUnitType>{
  String name;
  int range;
  int maxHitpoints;
  int defence;
  int attack;
  int movementPoints;
  public PhysicalUnitType(String name, int attack, int defence, int movementPoints, int maxHitpoints, int range){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.movementPoints = movementPoints;
        this.maxHitpoints = maxHitpoints;
        this.range = range;
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

  /**
   * Used for comparing one unittype to another, 
   * to find out if you could win, draw or lose 
   * in a fight with the other unittype.
   *
   * @param other The opposing unittype.
   *
   * @return Negative for a loss, 0 for a tie and positive for a win.
   */
  public int compareTo(PhysicalUnitType other){
    return 0;
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
