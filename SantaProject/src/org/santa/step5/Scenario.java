package org.santa.step5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.santa.step5.Elf.ElfState;
import org.santa.step5.Santa.SantaState;

public class Scenario {
	
	
	public Semaphore elfSemaphore;
	public Semaphore santaSemaphore;
	public Semaphore reindeerSemaphore;
	public Semaphore doorSemaphore;
	public Semaphore mainSemaphore;
	private boolean elvesMoving;
	private Santa santa;
	private List<Elf> elves;
	private List<Elf> elvesInTrouble;
	private List<Elf> santasDoor;
	private List<Reindeer> reindeers;
	private List<Reindeer> shed;
	private List<Reindeer> sleigh;
	private boolean isDecember;

	/**
	 * Initialize the lists etc
	 */
	public Scenario() {
		isDecember = false;
		santa = null;
		elves = new ArrayList<>();
		santasDoor = new ArrayList<>();
		elvesInTrouble = new ArrayList<>();
		reindeers = new ArrayList<>();
		shed = new ArrayList<>();
		sleigh = new ArrayList<>();
		elfSemaphore = new Semaphore(1);
		santaSemaphore = new Semaphore(1);
		reindeerSemaphore = new Semaphore(1);
		doorSemaphore = new Semaphore(0);
		mainSemaphore = new Semaphore(0);
	}
	
	public boolean areElvesMoving(){
		return elvesMoving;
	}
	
	public void setElvesMoving(boolean val){
		this.elvesMoving = val;
	}
	public List<Elf> getElves() {
		return elves;
	}
	
	public List<Elf> getDoor() {
		return santasDoor;
	}
	
	public List<Elf> getTrouble() {
		return elvesInTrouble;
	}
	
	

	public List<Reindeer> getReindeers() {
		return reindeers;
	}

	public Santa getSanta() {
		return santa;
	}

	public boolean isDecember() {
		return isDecember;
	}

	public void setDecember(boolean isDecember) {
		this.isDecember = isDecember;
	}

	public void setSanta(Santa santa) {
		this.santa = santa;
	}
	
	public void addElfToDoor(Elf elf){
		this.santasDoor.add(elf);
	}
	
	public void addElvestoDoor(){
		for(Elf elf : elvesInTrouble)
		{
			if(elf.getState() == ElfState.TROUBLE)
				this.santasDoor.add(elf);
		}
	}
	
	public void signal3Elves(){
		doorSemaphore.release(3);
		santa.setState(SantaState.WOKEN_UP_BY_ELVES);
	}
	
	public List<Reindeer> getShed(){
		return shed;
	}

	public List<Reindeer> getSleigh() {
		return sleigh;
	}


}
