package org.santa.step2;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

	private Santa santa;
	private List<Elf> elves;
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
		reindeers = new ArrayList<>();
	}

	public List<Elf> getElves() {
		return elves;
	}
	
	public List<Elf> getDoor() {
		return santasDoor;
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

}
