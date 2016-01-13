package org.santa.step3;

import java.util.Random;

import org.santa.step3.Santa.SantaState;

public class Elf implements Runnable {

	enum ElfState {
		WORKING, TROUBLE, AT_SANTAS_DOOR
	};

	private ElfState state;
	/**
	 * The number associated with the Elf
	 */
	private int identifier;
	private Random rand = new Random();
	private Scenario scenario;

	public Elf(int identifier, Scenario scenario) {
		this.identifier = identifier;
		this.scenario = scenario;
		this.state = ElfState.WORKING;
	}

	public ElfState getState() {
		return state;
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Elf " + identifier + " : " + state);
	}

	@Override
	public void run() {
		
		if(Main.day >= 370)
			return;
		
		else	
		while (true) {
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into
				// trouble.
				if (rand.nextDouble() < 0.01) {
					state = ElfState.TROUBLE;
					if( scenario.getTrouble().contains(this) == false)
					scenario.getTrouble().add(this);
				}
				break;
			}
			case TROUBLE:{
				// FIXME: if possible, move to Santa's door
				//Some if statement that checks if santa's door has < 3 elves
				
				//Is it ok to move?
				if(scenario.areElvesMoving() == true)
				{
					//move if you havent already
					if(scenario.getDoor().contains(this) == false)
					{
						state = ElfState.AT_SANTAS_DOOR;
						scenario.getTrouble().remove(this);
						scenario.getDoor().add(this);
					}
					
					//If you're the last one to move, signal that moving has ended
					if(scenario.getTrouble().size() == 0)
						scenario.setElvesMoving(false);
					
				}
				
				//Should it be time to move?
				else if(scenario.getTrouble().size() >= 3)
				{
					scenario.setElvesMoving(true);
				}
					
				
				
			}	
				break;
			case AT_SANTAS_DOOR:{
				// FIXME: if feasible, wake up Santa
				scenario.getSanta().setState(SantaState.WOKEN_UP_BY_ELVES);
				
			}
				break;
			}
		}
	}

	/**
	 * Santa might call this function to fix the trouble
	 * 
	 * @param state
	 */
	public void setState(ElfState state) {
		this.state = state;
	}
	

}
