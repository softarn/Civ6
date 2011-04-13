package src;

import java.util.Random;

public class Battle{
	public static int doBattle(PhysicalUnit u1, PhysicalUnit u2, TerrainType t1, TerrainType t2) {
		
		int winnerId=0;
		
		// Get variables from object u1 (attacking) and U2 (defending) 
        PhysicalUnitType ut1 = u1.getType();
        PhysicalUnitType ut2 = u2.getType();
		
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
		
		int hitPoints1 = ut1.getHitPoints();
		int hitPoints2 = ut1.getHitPoints();
		
		int manPower1 = u1.getManPower();
		int manPower2 = u2.getManPower();
		
		
		int attackBonus=t1.getAttackBonus();
		int defenceBonus=t2.getDefenceBonus();
		
		
		// Attacking object losing manpower randomNumber2 and defending losing randomNumber1 / ProgMeistro JesperB
		Random randomizer = new Random();
		int randomCharge = randomizer.nextInt(8)+1;
		
		for (int i = 0; i <= randomCharge; i++) { 
			int randomNumber1=randomizer.nextInt(attack1*hitPoints1*attackBonus);
			int randomNumber2= randomizer.nextInt(defence2*hitPoints2*defenceBonus);
		
			manPower1-=randomNumber2;
			manPower2-=randomNumber1;
			
            u1.setManPower(manPower1);
            u2.setManPower(manPower2);
			
			if (manPower1<1 || manPower2<1){
				break;
			}
			else if (manPower1<=0) {
				winnerId=1;
				break;
			}
			else if (manPower2<=0){
				winnerId=-1;
				break;
			}
			
			
		}// form
		
		return winnerId;
		
	}
}



