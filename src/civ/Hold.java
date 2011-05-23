package civ;

import java.util.ArrayList;

public class Hold{
    private ArrayList<PhysicalUnit> units;
    private int limit;

    public Hold(){
        this(0);
    }
    /**
     *
     * @param limit Number of maximum units in hold, 0 for infinite.
     */
    public Hold(int limit){
        units = new ArrayList<PhysicalUnit>();
        this.limit = limit;
    }

    public boolean addUnit(PhysicalUnit unit){
        if(limit != 0 && units.size() >= limit){
            return false;
        }
        units.add(unit);
        return true;
    }

    public boolean delUnit(PhysicalUnit unit){
        if(units.contains(unit)){
            units.remove(unit);
            return true;
        }
        return false;
    }

    public ArrayList<PhysicalUnit> getUnits(){
        return units;
    }
}
