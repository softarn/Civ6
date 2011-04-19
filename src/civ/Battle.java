package civ;

import java.util.Random;
import static civ.State.UnitState.UnitUnSelected;

public class Battle{
    private static State state = State.getInstance();
	public static int doBattle(PhysicalUnit u1, PhysicalUnit u2, Tile t1, Tile t2) {
		
		int winnerId=0;
		
		// Get variables from object u1 (attacking) and U2 (defending) 
        PhysicalUnitType ut1 = u1.getType();
        PhysicalUnitType ut2 = u2.getType();
        TerrainType tt1 = t1.getTerrain();
        TerrainType tt2 = t2.getTerrain();
		
		String name1=ut1.getName();
		String name2=ut2.getName();

		int defence1 = ut1.getDefence(); 
		int defence2 = ut2.getDefence();
		
		int attack1 = ut1.getAttack();
		int attack2 = ut2.getAttack();
		
		int range1 = ut1.getRange();
		int range2 = ut2.getRange();
		
		int movementPoint1=u1.getCurrentMovementPoint();
		int movementPoint2=u2.getCurrentMovementPoint();
		
		int manPower1 = u1.getManPower();
		int manPower2 = u2.getManPower();
		
		
		int attackBonus=tt1.getAttackBonus();
		int defenceBonus=tt2.getDefenceBonus();
		
		
		// Attacking object losing manpower randomNumber2 and defending losing randomNumber1 / ProgMeistro JesperB
		Random randomizer = new Random();
		int randomCharge = randomizer.nextInt(8)+1;
		
		for (int i = 0; i <= randomCharge; i++) { 
			int randomNumber1=randomizer.nextInt((int)(attack1*(1+attackBonus*0.01)));
			int randomNumber2= randomizer.nextInt((int)(defence2*(1+defenceBonus*0.01)));
		
			manPower1-=randomNumber2;
			manPower2-=randomNumber1;
			
            System.out.println(manPower1);
            System.out.println(manPower2);
            u1.setManPower(manPower1);
            u2.setManPower(manPower2);
			
			if (manPower1<1 && manPower2<1){
                state.setUnitState(UnitUnSelected);
                t1.setUnit(null);
                t2.setUnit(null);
				break;
			}
			else if (manPower1<=0) {
                t1.setUnit(null);
                state.setUnitState(UnitUnSelected);
				winnerId=1;
				break;
			}
			else if (manPower2<=0){
                t2.setUnit(null);
				winnerId=-1;
				break;
			}
			
			
		}// form
		
		return winnerId;
		
	}
}



