package civ;

enum UnitState{ // implements State{
    UnitUnSelected,
    UnitSelected;

	private static PhysicalUnit selectedUnit;
	private static UnitState unitState;

	public static void setSelectedUnit(PhysicalUnit unit){
        if(unit != null){
            selectedUnit = unit;
            setState(UnitSelected);
        }
        else{
            selectedUnit = unit;
            setState(UnitUnSelected);
        }
    }

	public static PhysicalUnit getSelectedUnit(){
		return selectedUnit;
	}

	public static UnitState getState(){
		return unitState;
	}

	private static void setState(UnitState state){
		unitState = state;
		//setChanged();
		//notifyObservers();
	}
}
