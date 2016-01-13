package org.santa.step5;

import org.santa.step5.Elf.ElfState;
import org.santa.step5.Reindeer.ReindeerState;

public class Santa implements Runnable {

	enum SantaState {
		SLEEPING, READY_FOR_CHRISTMAS, WOKEN_UP_BY_ELVES, WOKEN_UP_BY_REINDEER
	};

	private SantaState state;
	private Scenario scenario;

	public Santa(Scenario scenario) {
		this.scenario = scenario;
		this.state = SantaState.SLEEPING;
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Santa : " + state);
	}

	
	public void setState(SantaState Sstate){
		state = Sstate;
	}
	
	public SantaState getState()
	{
		return state;
	}
	@Override
	public void run() {
		if(Main.day >= 370 || state == SantaState.READY_FOR_CHRISTMAS)
			return;
		
		else
		while (true) {
			// wait a day...
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (state) {
			case SLEEPING: // if sleeping, continue to sleep
				break;
			case WOKEN_UP_BY_ELVES:{
				// FIXME: help the elves who are at the door and go back to
				// sleep
				
				try {
					scenario.santaSemaphore.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Santa got access to doorSemaphore");
				//Help each elf at the door
				if(!scenario.getDoor().isEmpty())
				for (Elf elf : scenario.getDoor())
				{
					
					elf.setState(ElfState.WORKING);
					System.out.println("\n" + elf.getIdentifier() + "Changing to Working");
				}

				scenario.getDoor().clear();
				
				//Put Santa to Sleep
				state = SantaState.SLEEPING;
				
				scenario.santaSemaphore.release();

				
			}
				break;
			case WOKEN_UP_BY_REINDEER:{
				// FIXME: assemble the reindeer to the sleigh then change state
				// to ready
				try {
					scenario.santaSemaphore.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Move each reindeer to Sleigh and clear the Shed
				for (Reindeer reindeer : scenario.getShed())
				{
					
					reindeer.setState(ReindeerState.AT_THE_SLEIGH);
					scenario.getSleigh().add(reindeer);
				}
				
				scenario.getShed().clear();
				
				this.state = SantaState.READY_FOR_CHRISTMAS;
				//Does not release semaphore because Santa is not going to take any more requests
				
			}
				break;
			case READY_FOR_CHRISTMAS: // nothing more to be done
				break;
			}
		}
	}

}
