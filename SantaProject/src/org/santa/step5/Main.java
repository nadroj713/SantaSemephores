package org.santa.step5;

import org.santa.step5.Santa.SantaState;


public class Main {

	public static int day;

	public static void main(String args[]) {
		Scenario scenario = new Scenario();
		// create the participants
		// Santa
		scenario.setSanta( new Santa(scenario) );
		Thread th = new Thread(scenario.getSanta());
		th.start();
		// The elves: in this case: 10
		for (int i = 0; i != 10; i++) {
			Elf elf = new Elf(i + 1, scenario);
			scenario.getElves().add(elf);
			th = new Thread(elf);
			th.start();
		}
		// The reindeer: in this case: 9
		for (int i = 0; i != 9; i++) {
			Reindeer reindeer = new Reindeer(i + 1, scenario);
			scenario.getReindeers().add(reindeer);
			th = new Thread(reindeer);
			th.start(); 
		}
		// now, start the passing of time
		for (day = 1; day < 500; day++) {
			
			if(scenario.getSanta().getState() == SantaState.READY_FOR_CHRISTMAS)
			{
				System.out.println("\n\n Christmas has been a success!");
				return;
			}
			
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// turn on December
			if (day > (365 - 31)) {
				scenario.setDecember(true);
			}
			
			
			// print out the state:
			System.out.println("***********  Day " + day
					+ " *************************");
		
			scenario.getSanta().report();
			for (Elf elf : scenario.getElves()) {
				elf.report();
			}
			
			//The next day, all three Elves will approach Santa
			if(scenario.getTrouble().size() >= 3)
				scenario.doorSemaphore.release(3);
			for (Reindeer reindeer : scenario.getReindeers()) {
				reindeer.report();
			}
			
			
		}
	}

}
