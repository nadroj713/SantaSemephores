package org.santa.step3;

import java.util.ArrayList;
import java.util.List;

import org.santa.step3.Elf.ElfState;

public class Scenario {
	
	private boolean elvesMoving;
	private Santa santa;
	private List<Elf> elves;
	private List<Elf> elvesInTrouble;
	private List<Elf> santasDoor;
	private List<Reindeer> reindeers;
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

}
