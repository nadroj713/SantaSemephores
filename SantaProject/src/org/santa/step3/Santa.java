package org.santa.step3;

import org.santa.step3.Elf.ElfState;

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
	@Override
	public void run() {
		if(Main.day >= 370)
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
				
				try{
					for (Elf elf : scenario.getDoor())
					{
						elf.setState(ElfState.WORKING);
						scenario.getDoor().remove(elf);
					}
				}catch(java.util.ConcurrentModificationException e){
					//Ignore I guess?
				}
				
				state = SantaState.SLEEPING;
				
				
			}
				break;
			case WOKEN_UP_BY_REINDEER:
				// FIXME: assemble the reindeer to the sleigh then change state
				// to ready
				break;
			case READY_FOR_CHRISTMAS: // nothing more to be done
				break;
			}
		}
	}

}
