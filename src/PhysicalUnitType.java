public enum PhysicalUnitType {
    Musketeer("Musketeer", 100, 2, 2, 3, 2, 1); 

    private String name;
    private int maxManpower;
    private int defence;
    private int attack;
    private int hitPoint;
    private int range;
    private int movementPoint;

    private final String imgFile;

    private PhysicalUnitType(String name, 
            int maxManpower,
            int attack,
            int defence,
            int hitPoint,
            int range,
            int movementPoints){
        this.name = name;
        this.maxManpower = maxManpower;
        this.attack = attack;
        this.defence = defence;
        this.hitPoint = hitPoint;
        this.range = range;
        this.movementPoint = movementPoint;

        imgFile = name + ".png"; 
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

    public int getMovementPoint(){
        return movementPoint;
    }

    public int getMaxManpower(){
        return maxManpower;
    }

    public int getRange(){
        return range;
    }

    public String getUnitImage(){
        return imgFile;
    }

    /**
     * Used for comparing one unittype to another, 
     * to check if it's stronger than the other.
     *
     * @param other The other unittype.
     *
     * @return 
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
    public boolean equals(Object other){
        if(other instanceof PhysicalUnitType){
            return this.name.equals(((PhysicalUnitType)other).name);
        }
        return false;
    }
     */
}
