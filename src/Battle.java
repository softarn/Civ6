<<<<<<< HEAD
import java.util.Random;
=======
package src;
>>>>>>> b181f8331d237fa17415f4ca2fd0d4942618b42b

public class Battle{

	public static int doBattle(PhysicalUnitType u1, PhysicalUnitType u2, TerrainType t1) {
		
		int winnerId=0;
		
		// Get variables from object u1 (attacking) and U2 (defending) 
		String name1=u1.getName();
		String name2=u2.getName();
		
		int defence1 = u1. getDefence(); 
		int defence2 = u2.getDefence();
		
		int attack1 = u1.getAttack();
		int attack2 = u2.getAttack();
		
		int range1 = u1.getRange();
		int range2=u2.getRange();
		
		int movementPoints1=u1.getMovementPoints();
		int movementPoints2=u2.getMovementPoints();
		
		int maxHitPoints1 = u1.getMaxHitpoints();
		int maxHitPoints2 = u1.getMaxHitpoints();
		
		int manPower1 = u1.getManpower();
		int manPower2 = u2.getManpower();
		
		
		int attackBonus=t1.getAttackBonus();
		
		
		// Attacking object losing manpower randomNumber2 and defending losing randomNumber1 / ProgMeistro JesperB
		Random randomizer = new Random();
		int randomCharge = randomizer.nextInt(8)+1;
		
		for (int i = 0; i <= randomCharge; i++) { 
			int randomNumber1=randomizer.nextInt(attack1*maxHitPoints1*attackBonus);
			int randomNumber2= randomizer.nextInt(defence1*maxHitPoints2*attackBonus);
		
			manPower1-=randomNumber2;
			manPower2-=randomNumber1;
			
			System.out.println(manPower1);
			System.out.println(manPower2);
			
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
	
	public static void main(String [] args) {
		
		PhysicalUnitType p1 = new PhysicalUnitType("Marcus", 2,1,1,1,2,100);
		PhysicalUnitType p2 = new PhysicalUnitType("Jesper", 2,2,1,3,2,100);	
		
		
		TerrainType t2 = TerrainType.getInstance(3);
		System.out.println(doBattle(p1,p2,t2));
		
		
	}
	
}












<<<<<<< HEAD
=======
}
>>>>>>> b181f8331d237fa17415f4ca2fd0d4942618b42b
