import java.util.random;

public class Battle{

	public boolean doBattle(PhysicalUnit u1, PhysicalUnit u2){
		return true;
	}
	
	public mainBattle (PhysicalUnitType u1, PhysicalUnitType u2) {
		
		// Get variables from object u1 (attacking) and U2 (defending) 
		String name1=u1.getName();
		String name2=u2.getName();
		
		int defence1 = u1. getDefence(); 
		int defence2 = u2.getDefence();
		
		int attack1 = u1.getAttack();
		int attack2 = u2.getAttack();
		
		int range1 = u1.getRage();
		int range2=u2.getRange();
		
		int movementPoints1=u1.getMovementPoints();
		int movementPoints2=u2.getMovementPoints();
		
		int maxHitPoints1 = u1.getMaxHitPoints();
		int maxHitPoints2 = u1.getMaxHitPoints();
		
		int manPower1 = u1.getManPower();
		int manPower2 = u1.getManPower();
		
		int attackBonus=TerrainType.getAttackBonus();
		
		
		// Attacking object losing manpower randomNumber2 and defending losing randomNumber1 / ProgMeistro JesperB
		Random randomizer = new Random();
		int randomCharge = randomizer.nextInt(8)+1;
		
		for (int i<=randomCharge; i++; ) { 
			int randomNumber1=randomizer.nextInt(attack1*maxHitPoints*attackBonus);
			int randomNumber2= randomizer.nextInt(defence1*maxHitPoints*attackBonus);
		
			manPower1-=randomNumber2;
			manPower2-=randomNumber1;
			
			if (manPower1<=0) {
				System.out.println("You are dead MF!");
			else if (manPower2<=0)
				System.out.println("YOU are dead MF!");
			}
						
		}// for
		
		
		
	}
	
}












