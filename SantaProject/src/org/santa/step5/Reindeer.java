package org.santa.step5;

import java.util.Random;

import org.santa.step5.Santa.SantaState;

public class Reindeer implements Runnable {

	public enum ReindeerState {
		AT_BEACH, AT_WARMING_SHED, AT_THE_SLEIGH
	};

	private ReindeerState state;
	private Scenario scenario;
	private Random rand = new Random();

	/**
	 * The identifier associated with the reindeer
	 */
	private int identifier;

	public Reindeer(int number, Scenario scenario) {
		this.identifier = number;
		this.scenario = scenario;
		this.state = ReindeerState.AT_BEACH;
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Reindeer " + identifier + " : " + state);
	};
	
	public void setState(ReindeerState RState){
		state = RState;
	}

	@Override
	public void run() {
		if(Main.day >= 370 || scenario.getSanta().getState() == SantaState.READY_FOR_CHRISTMAS)
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
			// see what we need to do:
			switch (state) {
			case AT_BEACH: { // if it is December, the reindeer might think
								// about returning from the beach
				if (scenario.isDecember()) {
					if (rand.nextDouble() < 0.1) {
						state = ReindeerState.AT_WARMING_SHED;
						
						try {
							scenario.reindeerSemaphore.acquire();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						scenario.getShed().add(this);
						scenario.reindeerSemaphore.release();
						
						
						
						
						//If you're the last reindeer, wake Santa
						if(scenario.getShed().size() == 9)
						{
							try {
								scenario.santaSemaphore.acquire();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							scenario.getSanta().setState(SantaState.WOKEN_UP_BY_REINDEER);
							scenario.santaSemaphore.release();
						}
					}
				}
				break;
			}
			case AT_WARMING_SHED:
				
				//Waking Santa taken care of by last Reindeer to enter shed
					
				break;
			case AT_THE_SLEIGH:
				// keep pulling
				break;
			}
		}
	}

}
